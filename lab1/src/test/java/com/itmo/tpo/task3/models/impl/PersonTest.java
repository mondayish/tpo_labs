package com.itmo.tpo.task3.models.impl;

import com.itmo.tpo.task3.enums.Condition;
import com.itmo.tpo.task3.enums.Gem;
import com.itmo.tpo.task3.enums.Planet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream ERR_CONTENT = new ByteArrayOutputStream();
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final PrintStream ORIGINAL_ERR = System.err;
    private static final String CRLF = "\r\n";

    private Person person1;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
        System.setErr(new PrintStream(ERR_CONTENT));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
        System.setErr(ORIGINAL_ERR);
    }

    @BeforeEach
    public void init() {
        OUT_CONTENT.reset();
        ERR_CONTENT.reset();
        person1 = new Person("Снифф", Planet.MARS, 10, 2);
    }

    @DisplayName("Check setting valid attention to Person")
    @ParameterizedTest(name = "Setting attention = {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    public void setAttentionValidValuesTest(int attention) {
        person1.setAttention(attention);
        assertEquals(attention, person1.getAttention());
    }

    @DisplayName("Check setting invalid attention to Person")
    @ParameterizedTest(name = "Setting attention = {0}")
    @ValueSource(ints = {-1, 6})
    public void setAttentionInvalidValuesTest(int attention) {
        person1.setAttention(attention);
        assertNotEquals(attention, person1.getAttention());
    }

    @DisplayName("Check setting clothes to Person")
    @Test
    public void setClothesTest() {
        String[] uniqueClothes = {"майка", "ушанка", "валенки"};
        String[] nonUniqueClothes = {"майка", "майка", "ушанка", "ушанка", "валенки"};
        person1.setClothes(uniqueClothes);
        assertArrayEquals(uniqueClothes, person1.getClothes());
        person1.setClothes(nonUniqueClothes);
        assertArrayEquals(uniqueClothes, person1.getClothes());
    }

    @DisplayName("Check person doesn't notice gemstone")
    @Test
    public void noticeNonSparkleTest() {
        GemStone nonSparkleGem = new GemStone("Камень1", Planet.EARTH, 100, 100, Gem.CRYSTAL);
        String expectedOut = person1.getName() + " ничего не замечает" + CRLF;

        person1.notice(nonSparkleGem);
        assertEquals(Condition.NOTHING, person1.getCondition());
        assertEquals(expectedOut, OUT_CONTENT.toString());
    }

    @DisplayName("Check person notice gemstone")
    @Test
    public void noticeSparkleTest() {
        GemStone sparkleGem = new GemStone("Камень1", Planet.EARTH, 100, 100, Gem.CRYSTAL);
        String expectedOut = person1.getName() + " заметил, что " + sparkleGem.getName() + " сверкает" + CRLF;

        sparkleGem.sparkle();
        person1.notice(sparkleGem);
        assertEquals(Condition.NOTICE, person1.getCondition());
        assertTrue(OUT_CONTENT.toString().contains(expectedOut));
    }

    @DisplayName("Check person sigh with energy")
    @Test
    public void sighWithEnergyTest() {
        String expectedOut = person1.getName() + " ещё полон энергии" + CRLF;
        person1.sigh();
        assertEquals(expectedOut, OUT_CONTENT.toString());
    }

    @DisplayName("Check person sigh without energy")
    @Test
    public void sighWithoutEnergyTest() {
        person1 = new Person("Снифф", Planet.MARS, 1, 2);
        String expectedOut = person1.getName() + " вздохнул" + CRLF;
        person1.sigh();
        assertEquals(expectedOut, OUT_CONTENT.toString());
    }

    @DisplayName("Check person think about something")
    @Test
    public void thinkTest() {
        GemStone gemStone = new GemStone("Камень1", Gem.CRYSTAL);
        String expectedOut = person1.getName() + " думает о " + gemStone.getName() + CRLF;
        person1.think(gemStone);
        assertEquals(Condition.THINK, person1.getCondition());
        assertEquals(expectedOut, OUT_CONTENT.toString());
    }


}