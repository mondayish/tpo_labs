package com.itmo.tpo.task3.models.impl;

import com.itmo.tpo.task3.enums.BagType;
import com.itmo.tpo.task3.enums.Color;
import com.itmo.tpo.task3.enums.Planet;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PersonBagTest {

    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final String CRLF = "\r\n";

    private Person person1;
    private Person.Bag bag1;


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
        bag1 = person1. new Bag(BagType.CASE, Color.BLAKE);
    }

    @DisplayName("Check setting content for Bag")
    @Test
    public void setContentTest() {
        Object[] uniqueContent = {"майка", "ушанка", "валенки"};
        Object[] nonUniqueContent = {"майка", "майка", "ушанка", "ушанка", "валенки"};
        bag1.setContent(uniqueContent);
        assertArrayEquals(uniqueContent, bag1.getContent());
        bag1.setContent(nonUniqueContent);
        assertArrayEquals(uniqueContent, bag1.getContent());
    }

    @DisplayName("Check deleting content from Bag")
    @Test
    public void deleteContentTest() {
        Object[] inputContent = {"майка", "ушанка", "валенки"};
        Object[] contentToDelete = {"майка", "валенки"};
        Object[] expectedContent = {"ушанка"};
        String expectedOutput = "Сумка: " + bag1.getType().getRussian() + ", Владелец: " + person1.getName() + ", Из сумки было выкинуто: майка, валенки"  + CRLF;
        bag1.setContent(inputContent);
        bag1.deleteContent(contentToDelete);
        assertArrayEquals(expectedContent, bag1.getContent());
        assertEquals(expectedOutput, OUT_CONTENT.toString());
    }

    @DisplayName("Check open/close Bag")
    @Test
    public void openClosedTest() {
        Object[] inputContent = {"майка", "ушанка", "валенки"};
        Object[] contentToDelete = {"майка", "валенки"};
        Object[] expectedContent = {"ушанка"};
        String expectedOutput = "Сумка: " + bag1.getType().getRussian() + ", Владелец: " + person1.getName() + ", Из сумки было выкинуто: майка, валенки"  + CRLF;
        bag1.setContent(inputContent);
        bag1.deleteContent(contentToDelete);
        assertArrayEquals(expectedContent, bag1.getContent());
        assertEquals(expectedOutput, OUT_CONTENT.toString());
    }
}
