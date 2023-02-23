package com.itmo.tpo.task3.models;

import com.itmo.tpo.task3.enums.Planet;

public abstract class Stone extends Thing {

    private static final int MAX_SIZE = 100;
    private static final int MIN_SIZE = 0;

    private int age;
    private int size;

    protected Stone(String name, Planet place, int age, int size) {
        super(name, place);
        setAge(age);
        setSize(size);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size < MIN_SIZE || size > MAX_SIZE) this.size = (int) (Math.random() * MAX_SIZE);
        else this.size = size;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }
}
