package com.itmo.tpo.task3.model;

import lombok.Data;

import java.util.Set;

@Data
public class Environment {

    private String name;
    private String sound;
    private String smell;
    private String event;
    private Set<EnvironmentThing> things;

}
