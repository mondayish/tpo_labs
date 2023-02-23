package com.itmo.tpo.task3.enums;

public enum Planet {

    EARTH("Земля", 3, 10000),
    MOON("Луна", 3.5, 500),
    MARS("Марс", 4, 5000);

    private final String russian;
    private final double numberToSun;
    private final int size;
    private double densityOfBodiesAroundPlanet; //плотность космических тел вокруг планеты

    Planet(String russian, double numberToSun, int size) {
        this.russian = russian;
        this.numberToSun = numberToSun;
        this.size = size;
        this.densityOfBodiesAroundPlanet = 0;
    }

    // вычисляем коэффициент опасности путешествия
    public double getLevelOfDanger() {
        return densityOfBodiesAroundPlanet / size;
    }

    public void addDensityOfBodiesAroundPlanet(double densityOfBodiesAroundPlanet) {
        this.densityOfBodiesAroundPlanet += densityOfBodiesAroundPlanet;
    }

    public double getDensityOfBodiesAroundPlanet() {
        return densityOfBodiesAroundPlanet;
    }

    public int getSize() {
        return size;
    }

    public double getNumberToSun() {
        return numberToSun;
    }

    public String getRussian() {
        return russian;
    }
}
