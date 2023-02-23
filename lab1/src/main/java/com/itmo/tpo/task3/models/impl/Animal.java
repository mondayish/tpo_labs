package com.itmo.tpo.task3.models.impl;

import com.itmo.tpo.task3.enums.Color;
import com.itmo.tpo.task3.enums.Planet;
import com.itmo.tpo.task3.models.Alive;

import java.util.Objects;

public class Animal extends Alive {

    private static final int MAX_POWER = 3;
    private static final int MIN_POWER = 0;

    private Color color;
    private int power;

    public Animal(String name, Planet place, int energy, Color color, int power) {
        super(name, place, energy);
        this.color = color;
        setPower(power);
    }

    public void setPower(int power) {
        if (power > MAX_ENERGY) this.power = MAX_POWER;
        else this.power = Math.max(power, MIN_POWER);
    }

    public int getPower() {
        return power;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return power == animal.power &&
                color == animal.color && Objects.equals(getName(), animal.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, power, getName());
    }

    @Override
    public String toString() {
        return "Animal " + getName() + ", цвет: " + color.getRussian() + ", находиться на " + getPlace().getRussian() +
                ". Состояние: " + getCondition().getRussian() + ". Энергия: " + getEnergy();
    }
}
