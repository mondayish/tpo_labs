package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.exceptions.PersonNotInTheSameGroupException;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Data
public class Group {

    private Set<Person> members = new HashSet<>();

    public String massNotice(@NonNull Environment location) {
        return "Все вместе наблюдают за: " + Optional.ofNullable(location.description()).orElse("Nothing");
    }

    public String addMember(@NonNull Person person) {
        person.setGroup(this);
        members.add(person);
        return person.description() + " присоединился к группе.";
    }

    public String removeMember(@NonNull Person person) {
        if (members.contains(person)) {
            person.setGroup(null);
            members.remove(person);
            return person.description() + " покинул группу.";
        }
        throw new PersonNotInTheSameGroupException();
    }
}
