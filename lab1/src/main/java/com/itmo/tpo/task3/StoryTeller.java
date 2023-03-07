package com.itmo.tpo.task3;

import com.itmo.tpo.task3.model.impl.Environment;
import com.itmo.tpo.task3.model.impl.Person;

public class StoryTeller {

    public static void main(String[] args) {
        Environment location1 = Environment.builder()
                .name("первоначальное окружение")
                .build();
        Environment location2 = Environment.builder()
                .name("подножие монолита")
                .build();

        Person rodriges = Person.builder()
                .surname("Родригес")
                .location(location1).build();
        Person johansen = Person.builder()
                .surname("Йохансен")
                .location(location1).build();


        rodriges.generateSound("Я обнаружил нечто интересное");
    }
}
