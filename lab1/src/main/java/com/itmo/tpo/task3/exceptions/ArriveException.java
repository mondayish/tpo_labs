package com.itmo.tpo.task3.exceptions;

import com.itmo.tpo.task3.models.impl.Animal;
import com.itmo.tpo.task3.enums.Planet;

public class ArriveException extends Exception {   //checked exception
    private final Animal animal;
    private final Planet from;
    private final Planet to;

    public ArriveException(Animal animal, Planet from, Planet to) {
        this.animal = animal;
        this.from = from;
        this.to = to;
    }

    @Override
    public String getMessage() {
        if (from == to) {
            return "Путешествие не имеет смысла, на животных можно перемещаться только между планетами: Планета отправления " +
                    "== Планета прибытия(" + from.getRussian() + "). ArriveException!!!";
        } else {
            return "Animal " + animal.getName() + " не находиться на планете " + from.getRussian() + ". ArriveException!!!";
        }
    }
}
