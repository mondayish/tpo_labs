package com.itmo.tpo.task3.model;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.Set;

@Data
public class Group {

    private Set<Person> members;

    public String massNotice(Environment location){
        return "Все вместе наблюдают за:" + location.description();
    }

    public String addMember(Person person){
        person.setGroup(this);
        members.add(person);
        return person.description() + " присоеденился к группе.";
    }

    public String removeMember(Person person){
        person.setGroup(null);
        members.remove(person);
        return person.description() + " покинул группу.";
    }
}
