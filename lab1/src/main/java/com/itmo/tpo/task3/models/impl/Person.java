package com.itmo.tpo.task3.models.impl;

import com.itmo.tpo.task3.enums.BagType;
import com.itmo.tpo.task3.enums.Color;
import com.itmo.tpo.task3.enums.Condition;
import com.itmo.tpo.task3.enums.Planet;
import com.itmo.tpo.task3.exceptions.ArriveException;
import com.itmo.tpo.task3.exceptions.PlanetOnSamePlanetException;
import com.itmo.tpo.task3.models.Alive;
import com.itmo.tpo.task3.models.Philosophy;
import com.itmo.tpo.task3.models.Thing;
import lombok.SneakyThrows;

import java.util.Objects;

public class Person extends Alive implements Philosophy {

    private final static int MAX_ATTENTION = 5;
    private final static int MIN_ATTENTION = 0;
    private final static int VALUE_FOR_NOTICE = 100;

    private int attention;
    private String[] clothes;
    private boolean dressed;

    public Person(String name, Planet place, int energy, int attention, String... clothes) {
        super(name, place, energy);
        setAttention(attention);
        setClothes(clothes);
    }

    public Person(String name) {
        super(name, Planet.EARTH, (int) (Math.random() * MAX_ENERGY));
        setAttention(MIN_ATTENTION);
    }

    public int getAttention() {
        return attention;
    }

    public void setAttention(int attention) {
        if (attention < MIN_ATTENTION || attention > MAX_ATTENTION)
            this.attention = (int) (Math.random() * MAX_ATTENTION);
        else this.attention = attention;
    }

    public String[] getClothes() {
        return clothes;
    }

    // в этом методе используем приведение типов
    public void setClothes(String... clothes) {
        Object[] objects = deleteDoubleThings(clothes);
        String[] cloth = new String[objects.length];
        for (int i = 0; i < objects.length; i++) {
            cloth[i] = (String) objects[i];
        }
        this.clothes = cloth;
    }

    // метод удаляет в массиве повторяющиеся элементы
    private static Object[] deleteDoubleThings(Object[] objects) {
        if (objects == null) return null;
        boolean[] deleteIndex = new boolean[objects.length];
        for (int i = 0; i < objects.length; i++) {
            for (int j = i + 1; j < objects.length; j++) {
                if (objects[i].equals(objects[j])) {
                    deleteIndex[i] = true;
                    break;
                }
            }
        }
        return createNewArrayOfObjects(objects, deleteIndex);
    }

    // метод для создания нового массива по старому и массиву индексов, которые нужно удалить
    private static Object[] createNewArrayOfObjects(Object[] objectsForDelete, boolean[] deleteIndex) {
        int size = 0;
        for (boolean i : deleteIndex) if (!i) size++;
        Object[] normalObjects = new Object[size];
        int count = 0;
        for (int i = 0; i < objectsForDelete.length; i++) {
            if (!deleteIndex[i]) {
                normalObjects[count++] = objectsForDelete[i];
            }
        }
        return normalObjects;
    }

    // метод для удаления из первого массива элементов второго
    private Object[] deleteSameThings(Object[] objectsForDelete, Object[] objectsWhichDelete) {
        if (objectsForDelete == null) return null;
        if (objectsWhichDelete == null) return objectsForDelete;
        boolean[] deleteIndex = new boolean[objectsForDelete.length];
        for (int i = 0; i < objectsForDelete.length; i++) {
            for (Object o : objectsWhichDelete) {
                if (objectsForDelete[i].equals(o)) {
                    deleteIndex[i] = true;
                    break;
                }
            }
        }
        return createNewArrayOfObjects(objectsForDelete, deleteIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return attention == person.attention && Objects.equals(getName(), person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(attention, getName());
    }

    @Override
    public String toString() {
        String dress = transfer(clothes, "нет одежды");
        String dressedStr = dressed ? "одет" : "не одет";
        return "Person " + getName() + " находиться на " + getPlace().getRussian() + ". Состояние: " + getCondition().getRussian()
                + ", " + dressedStr + ". Одежда: " + dress + ". Энергия: " + getEnergy();
    }

    public void sigh() {
        if (getEnergy() < 3) System.out.println(getName() + " вздохнул");
        else System.out.println(getName() + " ещё полон энергии");
    }

    // персонаж заметит камень, только когда тот сверкает, у персонажа достаточно внимания, у камня достаточный размер, расстояние между их планетами достаточно мало
    @Override
    public void notice(GemStone gemStone) {
        if (gemStone.getSpark() && attention * gemStone.getSize() /
                (Math.abs(this.getPlace().getNumberToSun() - gemStone.getPlace().getNumberToSun())) > VALUE_FOR_NOTICE) {
            System.out.println(getName() + " заметил, что " + gemStone.getName() + " сверкает");
            setCondition(Condition.NOTICE);
        } else System.out.println(getName() + " ничего не замечает");
    }

    @Override
    public void think(Thing obj) {
        System.out.println(getName() + " думает о " + obj.getName());
        setCondition(Condition.THINK);
    }

    // метод для перечисления элементов массива в строке, если массив пуст возвращает параметр ifNull
    public String transfer(Object[] array, String ifNull) {
        if (array != null && array.length > 0) {
            String result = "";
            for (Object i : array) {
                result = result + i.toString() + ", ";
            }
            result = result.substring(0, result.length() - 2);
            return result;
        } else return ifNull;
    }

    // персонаж одевается
    public void getDressed() {
        if (clothes != null) {
            if (!dressed) {
                String result = getName() + " надевает ";
                System.out.println(result + transfer(clothes, ""));
                dressed = true;
            } else {
                System.out.println(getName() + " раздевается");
                dressed = false;
            }
        } else {
            this.tell("Мне нечего надеть");
        }
    }


    // вложенный класс сумки
    public class Bag {
        private BagType type;
        private Object[] content;
        private Color color;
        private boolean open;

        public Bag(BagType type, Color color, Object... content) {
            this.type = type;
            this.color = color;
            setContent(content);
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        // exception если засунем планету, которая есть планета чемодана
        public void setContent(Object... content) {
            for (Object i : content) {
                if (i.equals(getPlace())) throw new PlanetOnSamePlanetException(getPlace());
            }
            this.content = deleteDoubleThings(content);
        }

        public BagType getType() {
            return type;
        }

        public Object[] getContent() {
            return content;
        }

        // метод для удаления из сумки хлама
        public void deleteContent(Object... excessContent) {
            content = deleteSameThings(content, excessContent);
            System.out.println("Сумка: " + type.getRussian() + ", Владелец: " + getName() + ", Из сумки было выкинуто: " + transfer(excessContent, " ничего"));
        }

        // метод закрывает/открывает сумку и выводит содержимое
        public void openClosed() {
            if (open) {
                System.out.println(getName() + " закрыл свой " + type.getRussian());
                open = false;
            } else {
                String in = transfer(content, "ничего");
                System.out.println(getName() + " открыл свой " + type.getRussian() + ", а внутри: " + in);
                open = true;
            }
        }

        @Override
        public String toString() {
            return "Bag " + type.getRussian() + ", владелец " + getName() + ", цвет " + color + ", содержимое: " + transfer(content, "ничего");
        }
    }

    // статический вложенный класс для путешествий между планетами
    public static class Arrive {
        private Animal animalWithPower;    // чтобы животное могло перевозить Person у него должна быть положительная power(power==кол-во пассажиров)
        private Person[] passengers;         // не более 3 пассажиров
        private Planet from;
        private Planet to;

        @SneakyThrows
        public Arrive(Animal animalWithPower, Planet from, Planet to, Person... passengers) {
            // если планета from совпадает с to или если животное находится вообще на другой планете, то нет смысла в путешествии
            if (from == to || from != animalWithPower.getPlace()) {
                throw new ArriveException(animalWithPower, from, to);
            }
            this.from = from;
            this.to = to;
            setAnimalWithPower(animalWithPower);
            setPassengers(passengers);
        }

        public Animal getAnimalWithPower() {
            return animalWithPower;
        }

        public Person[] getPassengers() {
            return passengers;
        }

        public Planet getFrom() {
            return from;
        }

        public Planet getTo() {
            return to;
        }

        public void go() {
            if (animalWithPower != null) {
                //здесь проверяем опасность путешествия и отменяем его в случае высокого коэффициента опасности
                double coefficient1 = from.getLevelOfDanger();
                double coefficient2 = to.getLevelOfDanger();
                if (coefficient1 >= 2) {
                    System.out.println(analysisOfDanger(coefficient1, from));
                    return;
                }
                if (coefficient2 >= 2) {
                    System.out.println(analysisOfDanger(coefficient2, to));
                    return;
                }
                System.out.println(analysisOfDanger(coefficient1, from));
                System.out.println(analysisOfDanger(coefficient2, to));
                String strPassengers = "";
                for (Person person : passengers) {
                    strPassengers += person.getName() + ", ";
                }
                System.out.println(animalWithPower.getName() + " с пассажирами: " + strPassengers
                        + "перемещаются с планеты " + getFrom().getRussian() + " на планету " + getTo().getRussian());
                animalWithPower.setCondition(Condition.IN_ARRIVE);   //здесь вычитается энергия
                animalWithPower.setCondition(Condition.NOTHING);
                animalWithPower.setPlace(to);
                //перемещаем всех пассажиров и устанвливаем состояние ничегонеделания
                for (Person person : passengers) {
                    person.setPlace(to);
                    person.setCondition(Condition.NOTHING);
                }
                System.out.println("Все пассажиры слезли с животного: " + animalWithPower.getName());
                animalWithPower = null;
            } else {
                System.out.println("Путешествие невозможно!!!");
            }
        }

        // метод для анализа коэффициента опасности
        public String analysisOfDanger(double dangerCoefficient, Planet planet) {
            if (dangerCoefficient >= 2)
                return "Лететь категорически нельзя, это опасно: Вокруг " + planet.getRussian() + " слишком много космических тел!!!";
            else if (dangerCoefficient >= 1)
                return "Лететь можно, но это не совсем безопасно: Вокруг " + planet.getRussian() + " достаточно космических тел.";
            else
                return "Лететь можно, пассажиры в полной безопасности: Вокруг " + planet.getRussian() + " мало космических тел.";
        }

        // всевозможные проверки на силу и состояние
        public void setAnimalWithPower(Animal animalWithPower) {
            if (animalWithPower.getPower() == 0) {
                System.out.println("Animal " + animalWithPower.getName() + " слишком слабо для перевозки пассажиров, берегите животных!!!");
                return;
            }
            if (animalWithPower.getCondition() == Condition.REST || animalWithPower.getCondition() == Condition.SLEEP) {
                animalWithPower.stand();
            }
            this.animalWithPower = animalWithPower;
        }

        public void setPassengers(Person[] passengers) {
            final int POWER_FOR_ONE_PASSENGER = 2;    //силы на одного пассажира
            final int POWER_FOR_GOTO = 2;             //силы на перемещение между планетами
            if (animalWithPower != null && passengers != null) {
                //удаляем пассажиров, которые пытаются залезть дважды и приводим нормально типы
                Object[] objects = deleteDoubleThings(passengers);
                Person[] normalPassengers = new Person[objects.length];
                for (int i = 0; i < objects.length; i++) {
                    normalPassengers[i] = (Person) objects[i];
                }
                normalPassengers = deletePersonFromOtherPlanetsOrOnAnimal(normalPassengers);  // убираем из пассажиров людей с других планет или уже сидящих на животном
                // делаем так, чтобы животное не убили перегрузкой пассажиров
                int size;
                if (normalPassengers.length > animalWithPower.getPower()) {
                    size = animalWithPower.getPower();
                    System.out.println("Многовато пассажиров, все не влезут на " + animalWithPower.getName());
                } else size = normalPassengers.length;
                this.passengers = new Person[size];
                // все пассажиры садятся
                for (int i = 0; i < size; i++) {
                    this.passengers[i] = normalPassengers[i];
                    System.out.println(this.passengers[i].getName() + " садиться на " + animalWithPower.getName());
                    this.passengers[i].setCondition(Condition.SIT_ON_ANIMAL);
                }
                // делаем проверку на энергию животного
                if (animalWithPower.getEnergy() < POWER_FOR_GOTO + POWER_FOR_ONE_PASSENGER * this.passengers.length) {
                    System.out.println("Animal " + animalWithPower.getName() + " недостаточно энергии для такого путешествия, пусть отдыхает...");
                    this.passengers = null;
                    System.out.println("Все пассажиры слезли с животного: " + animalWithPower.getName());
                }
            }
        }

        // удаляет пассажиров с другой планеты(отличающейся от from), а также уже сидящих на других животных
        private Person[] deletePersonFromOtherPlanetsOrOnAnimal(Person[] people) {
            boolean[] deleteIndex = new boolean[people.length];
            for (int i = 0; i < people.length; i++) {
                if (people[i].getPlace() != from || people[i].getCondition() == Condition.SIT_ON_ANIMAL) {
                    deleteIndex[i] = true;
                }
            }
            Object[] objects = createNewArrayOfObjects(people, deleteIndex);
            Person[] normalPassengers = new Person[objects.length];
            for (int i = 0; i < objects.length; i++) {
                normalPassengers[i] = (Person) objects[i];
            }
            return normalPassengers;
        }
    }
}
