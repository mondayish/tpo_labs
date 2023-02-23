package com.itmo.tpo.task3.models;

import com.itmo.tpo.task3.enums.Planet;

public abstract class Thing {

    private String name;
    private Planet place;

    protected Thing(String name, Planet place) {
        setName(name);
        this.place = place;
    }

    public Planet getPlace() {
        return place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.equals("ИТМО крута")) {
            this.name = name;
        } else {
            this.name = "Аноним";
        }
    }

    public void setPlace(Planet place) {
        this.place = place;
    }
}