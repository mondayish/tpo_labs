package com.itmo.tpo.task3;

import com.itmo.tpo.task3.enums.BagType;
import com.itmo.tpo.task3.enums.Color;
import com.itmo.tpo.task3.enums.Planet;
import com.itmo.tpo.task3.exceptions.ArriveException;
import com.itmo.tpo.task3.exceptions.PlanetOnSamePlanetException;
import com.itmo.tpo.task3.models.impl.Animal;
import com.itmo.tpo.task3.models.impl.GemStone;
import com.itmo.tpo.task3.models.impl.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DomainModelTest {

    private Animal animal;
    private Person person;
    private GemStone gemStone;


    @BeforeEach
    void init(){
        animal = new Animal("Пантера", Planet.MOON, 2, Color.BLAKE, 2);
        person = new Person("Король", Planet.MOON, 2, 2, "Пижама");
    }

    @Test
    @DisplayName("Test impossible arrive values")
    void testArriveException(){
        Throwable exception = assertThrows(ArriveException.class, () -> new Person.Arrive(animal, Planet.MOON, Planet.MOON, person));
        assertEquals("Путешествие не имеет смысла, на животных можно перемещаться только между планетами: Планета отправления == Планета прибытия(Луна). ArriveException!!!",
                exception.getMessage());
    }

    @Test
    @DisplayName("Test impossible arrive values")
    void testPlanetOnSamePlanetExceptionException(){
        Throwable exception = assertThrows(PlanetOnSamePlanetException.class, () -> person. new Bag(BagType.LADY_BAG, Color.PINK, Planet.MOON));
        assertEquals("Нельзя положить в сумку планету, на которой находится эта сумка: Луна находиться на самой себе!!!",
                exception.getMessage());
    }
}
