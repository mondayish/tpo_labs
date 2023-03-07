package com.itmo.tpo.task3.model;

import lombok.Data;

@Data
public class EnvironmentThing implements Describable {

    private String name;


    @Override
    public String description() {
        return getName();
    }
}
