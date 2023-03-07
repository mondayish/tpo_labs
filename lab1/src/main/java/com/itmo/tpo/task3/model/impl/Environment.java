package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.model.Describable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Environment implements Describable {

    @NonNull
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
