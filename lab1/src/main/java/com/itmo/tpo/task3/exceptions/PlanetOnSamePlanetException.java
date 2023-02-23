package com.itmo.tpo.task3.exceptions;

import com.itmo.tpo.task3.enums.Planet;

public class PlanetOnSamePlanetException extends RuntimeException {   //unchecked exception
    private final Planet planet;

    public PlanetOnSamePlanetException(Planet planet) {
        this.planet = planet;
    }

    @Override
    public String getMessage() {
        return "Нельзя положить в сумку планету, на которой находится эта сумка: " + planet.getRussian() + " находиться на самой себе!!!";
    }
}

