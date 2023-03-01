package com.itmo.tpo.task3.models.impl;

import com.itmo.tpo.task3.enums.Color;
import com.itmo.tpo.task3.enums.Condition;
import com.itmo.tpo.task3.enums.Planet;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PersonArriveTest {

    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final String CRLF = "\r\n";

    private Person person1;
    private Animal animal1;
    private Person.Arrive arrive1;


    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
    }

    @BeforeEach
    public void init() {
        OUT_CONTENT.reset();
        person1 = new Person("Снифф", Planet.MARS, 10, 2);
        animal1 = new Animal("Пантера", Planet.MARS, 10, Color.BLAKE, 3);
        arrive1 = new Person.Arrive(animal1, Planet.MARS, Planet.EARTH, person1);
    }

    @DisplayName("Check setting animal for arrive")
    @Test
    public void setAnimalWithPowerTest() {
        arrive1.setAnimalWithPower(animal1);
        assertEquals(animal1, arrive1.getAnimalWithPower());

        String expectedOut = animal1.getName() + " встаёт" + CRLF;
        animal1.setCondition(Condition.REST);
        arrive1.setAnimalWithPower(animal1);
        assertTrue(OUT_CONTENT.toString().contains(expectedOut));
        assertEquals(Condition.STAND, animal1.getCondition());

        expectedOut = "Animal " + animal1.getName() + " слишком слабо для перевозки пассажиров, берегите животных!!!" + CRLF;
        animal1.setPower(0);
        arrive1.setAnimalWithPower(animal1);
        assertTrue(OUT_CONTENT.toString().contains(expectedOut));
    }

    @DisplayName("Check setting passengers for arrive")
    @Test
    public void setPassengersTest() {
        String expectedOut = person1.getName() + " садиться на " + animal1.getName() + CRLF;
        Person[] inputPassengers = new Person[]{person1};
        Person[] expectedPassengers = new Person[]{person1};
        person1.setCondition(Condition.NOTHING);
        arrive1.setPassengers(inputPassengers);
        assertArrayEquals(expectedPassengers, arrive1.getPassengers());
        assertTrue(OUT_CONTENT.toString().contains(expectedOut));

        Person person2 = new Person("Чел2", Planet.MOON, 10, 2);
        inputPassengers = new Person[] {person1, person2};
        person1.setCondition(Condition.NOTHING);
        arrive1.setPassengers(inputPassengers);
        assertArrayEquals(expectedPassengers, arrive1.getPassengers());

        expectedOut = "Многовато пассажиров, все не влезут на " + animal1.getName() + CRLF;
        expectedPassengers = new Person[]{};
        animal1.setPower(0);
        person1.setCondition(Condition.NOTHING);
        arrive1.setPassengers(inputPassengers);
        assertArrayEquals(expectedPassengers, arrive1.getPassengers());
        assertTrue(OUT_CONTENT.toString().contains(expectedOut));
    }
}
