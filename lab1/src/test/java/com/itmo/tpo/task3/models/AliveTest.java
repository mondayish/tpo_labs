package com.itmo.tpo.task3.models;

import com.itmo.tpo.task3.enums.Condition;
import com.itmo.tpo.task3.enums.Planet;
import com.itmo.tpo.task3.models.impl.Person;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AliveTest {

    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final String CRLF = "\r\n";

    private Person person1;


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
    }

    @DisplayName("Check telling alive")
    @Test
    public void tellTest() {
        String expectedOut = person1.getName() + " говорит: \"hello\"" + CRLF;
        person1.tell("hello");
        assertEquals(expectedOut, OUT_CONTENT.toString());
    }

    @DisplayName("Check resting alive")
    @Test
    public void restTest() {
        String expectedOut = person1.getName() + " отдыхает. Место: " + person1.getPlace().getRussian() + CRLF;
        person1.rest();
        assertTrue(OUT_CONTENT.toString().contains(expectedOut));
    }

    @DisplayName("Check sleeping alive")
    @Test
    public void sleepTest() {
        String expectedOut = person1.getName() + " спит. Место: " + person1.getPlace().getRussian() + CRLF;
        person1.sleep();
        assertTrue(OUT_CONTENT.toString().contains(expectedOut));
    }

    @DisplayName("Check stand up alive")
    @Test
    public void standTest() {
        String expectedOut = person1.getName() + " встаёт" + CRLF;
        person1.stand();
        assertTrue(OUT_CONTENT.toString().contains(expectedOut));

        expectedOut = "Person " + person1.getName() + " не может встать, т.к. сидит на животном";
        person1.setCondition(Condition.SIT_ON_ANIMAL);
        person1.stand();
        assertTrue(OUT_CONTENT.toString().contains(expectedOut));
    }

    // todo tests for other methods?
}