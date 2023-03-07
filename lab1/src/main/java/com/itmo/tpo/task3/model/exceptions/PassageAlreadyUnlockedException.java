package com.itmo.tpo.task3.model.exceptions;

public class PassageAlreadyUnlockedException extends Exception{

    @Override
    public String getMessage(){

        return "Дверь уже открыта";
    }
}
