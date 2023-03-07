package com.itmo.tpo.task3.model.exceptions;

import com.itmo.tpo.task3.model.Movable;
import com.itmo.tpo.task3.model.Passage;

public class NoAccessToPassageException extends Exception{

    private Movable subject;
    private Passage passage;

    @Override
    public String getMessage(){
        return "Нет доступа к проходу, он в другой локации";
    }

}
