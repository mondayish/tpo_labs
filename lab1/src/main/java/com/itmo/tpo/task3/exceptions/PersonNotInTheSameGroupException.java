package com.itmo.tpo.task3.exceptions;

public class PersonNotInTheSameGroupException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Взаимодействие с членом не входящим в текущую группу невозможно.";
    }
}
