package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.model.Describable;
import lombok.Data;

import java.util.Set;

@Data
public class Environment implements Describable {

    private String name;
    private String sound;
    private String smell;
    private String event;
    private Set<EnvironmentThing> things;

    @Override
    public String description() {
        return getEvent();
    }
}
