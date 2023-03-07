package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.exceptions.NoAccessToPassageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    private Environment firstLocation;
    private Environment secondLocation;
    private Person person;
    private Passage passage;

    @BeforeEach
    public void init() {
        firstLocation = Environment.builder()
                .name("Desert")
                .sound("Nothing")
                .smell("Nothing")
                .event("Nothing")
                .build();
        secondLocation = Environment.builder()
                .name("River")
                .sound("Nothing")
                .smell("Nothing")
                .event("Nothing")
                .build();
        person = Person.builder()
                .surname("Rodrigo")
                .location(firstLocation)
                .build();
        passage = Passage.builder()
                .name("Secret door")
                .thisSide(firstLocation)
                .otherSide(secondLocation)
                .locked(false)
                .build();
    }

    @Test
    @DisplayName("Check usePassage method")
    public void usePassageTest() {
        String expected = "Rodrigo прошёл через Secret door";
        assertEquals(expected, person.usePassage(passage));
        assertEquals(secondLocation, person.getLocation());

        expected = "Rodrigo пытался пройти через Secret door, но проход оказался заперт";
        passage.setLocked(true);
        assertEquals(expected, person.usePassage(passage));
        assertEquals(secondLocation, person.getLocation());

        passage.setLocked(false);
        person.setLocation(null);
        assertThrows(NoAccessToPassageException.class, () -> person.usePassage(passage));
    }
}
