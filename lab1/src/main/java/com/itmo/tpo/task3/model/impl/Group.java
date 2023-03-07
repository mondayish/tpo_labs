package com.itmo.tpo.task3.model.impl;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Group {

    private Set<Person> members = new HashSet<>();

    public String massNotice(Environment location) {
        return "Все вместе наблюдают за: " + location.description();
    }

    public String addMember(Person person) {
        person.setGroup(this);
        members.add(person);
        return person.description() + " присоединился к группе.";
    }

    public String removeMember(Person person) {
        person.setGroup(null);
        members.remove(person);
        return person.description() + " покинул группу.";
    }
}
