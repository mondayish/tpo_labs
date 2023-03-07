package com.itmo.tpo.task3.model;

import lombok.Data;

@Data
public class Person implements Movable, Alive{

    private String surname;
    // todo национальность?))
    private Person[] group;
    // todo нужен ли отдельный класс группы?
    private Environment location;

    @Override
    public void usePassage(Passage passage){
        // todo использует проход для перемещения между локациями
    }

    public void hear(){
        // todo слышит звуки из окружения
    }

    public void lookAround(){
        // todo видит объекты в текущем окружении
    }

    public void think(Object object){
        // todo мнение о предмете/проходе/локации/человеке
        // todo нормально ли пробрасывать object?
    }

    @Override
    public void speak(){
        // todo что-то говорит, генерирует звук в окружение
    }

    public void speakTo(Person person){
        // todo говорит что-то кому-то, проверка что люди в одной группе
    }

    public void follow(Person person){
        // todo следует за членом команды
    }

}
