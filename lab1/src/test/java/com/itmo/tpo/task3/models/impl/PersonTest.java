package com.itmo.tpo.task3.models.impl;

import com.itmo.tpo.task3.enums.Planet;
import org.junit.jupiter.api.BeforeEach;

class PersonTest {

    private Person person1;

    @BeforeEach
    public void init() {
        person1 = new Person("Снифф", Planet.MARS, 1, 2);
    }
}