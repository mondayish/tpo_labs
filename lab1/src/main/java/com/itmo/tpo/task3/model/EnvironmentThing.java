package com.itmo.tpo.task3.model;

import lombok.Data;

@Data
public class EnvironmentThing implements Describable {

    private String name;

    public EnvironmentThing(String name) {
        this.name = name;
    }

    @Override
    public String description() {
        return getName();
    }
}
