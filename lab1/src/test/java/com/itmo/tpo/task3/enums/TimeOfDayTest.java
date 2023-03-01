package com.itmo.tpo.task3.enums;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeOfDayTest {

    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final String CRLF = "\r\n";



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
    }

    @ParameterizedTest(name = "Description = {0}")
    @DisplayName("Description test")
    @ValueSource(strings = {"блаженная", "великолепная", "синяя"})
    public void describeTest(String des){
        String expectedOut = "Время: " + des + " Ночь" + CRLF;
        TimeOfDay.NIGHT.describe(des);
        assertEquals(expectedOut, OUT_CONTENT.toString());
    }
    
    @DisplayName("Beautiful description test")
    @Test
    public void beautifulDescriptionTest(){
        String description = "Произошло нечто ужасное";
        String expectedOut1 = description + ": это было " + "необычайно красиво" + CRLF;
        String expectedOut2 = description + ": это было " + "прекрасно" + CRLF;
        String expectedOut3 = description + ": это было " + "великолепно" + CRLF;
        String expectedOut4 = description + ": это было " + "удивительно" + CRLF;
        TimeOfDay.NIGHT.beautifulDescription(description, "удивительно");
        assertTrue(OUT_CONTENT.toString().equals(expectedOut1) ||
                OUT_CONTENT.toString().equals(expectedOut2) ||
                OUT_CONTENT.toString().equals(expectedOut3) ||
                OUT_CONTENT.toString().equals(expectedOut4));
    }
}