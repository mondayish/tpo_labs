package com.itmo.tpo.task3.models.impl;

import com.itmo.tpo.task3.enums.Gem;
import com.itmo.tpo.task3.enums.Planet;
import com.itmo.tpo.task3.models.Stone;

public class GemStone extends Stone {

    private Gem type;
    private boolean spark;   //сверкает ли?

    public GemStone(String name, Planet place, int age, int size, Gem type) {
        super(name, place, age, size);
        this.type = type;
    }

    public GemStone(String name, Gem type) {
        this(name, Planet.EARTH, (int) (Math.random() * 10000), 1, type);
    }

    public boolean getSpark() {
        return spark;
    }

    public Gem getType() {
        return type;
    }

    public void setType(Gem type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if (!spark)
            return type.getRussian() + " " + getName() + " стоит на " + getPlace().getRussian() + " уже " + getAge() + " лет. Размер: " + getSize();
        else
            return type.getRussian() + " " + getName() + " стоит на " + getPlace().getRussian() + " уже " + getAge() + " лет и сверкает. Размер: " + getSize();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.hashCode() != obj.hashCode() || this.getClass() != obj.getClass()) {
            return false;
        }
        GemStone gemStone = (GemStone) obj;
        return getName().equals(gemStone.getName()) && getAge() == gemStone.getAge() && getPlace() == gemStone.getPlace() && type == gemStone.type;
    }

    @Override
    public int hashCode() {
        return getName().hashCode() + getPlace().hashCode() + type.hashCode();
    }

    // сверкать
    public void sparkle() {
        if (!spark) {
            spark = true;
            System.out.println(getName() + " сверкает. Место: " + getPlace().getRussian());
        } else spark = false;
    }
}
