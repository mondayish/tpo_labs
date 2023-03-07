package com.itmo.tpo.task3.model;

import lombok.Data;

import java.util.Set;

@Data
public class Group implements Alive{

    private Set<Person> members;


    @Override
    public String speak() {
        //todo
        return null;
    }

    @Override
    public String usePassage(Passage passage) {
        //todo
        return null;
    }

    public String addMember(Person person){
        //todo
        return null;
    }

    public String removeMember(Person person){
        //todo
        return null;
    }
}
