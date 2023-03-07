package com.itmo.tpo.task3.model.exceptions;

public class PersonNotInTheSameGroupException extends Exception{

    @Override
    public String getMessage(){
        return "Взаимодействие с членом не входящим в текущую группу невозможно.";
    }
}
