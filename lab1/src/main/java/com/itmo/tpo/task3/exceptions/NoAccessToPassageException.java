package com.itmo.tpo.task3.exceptions;

import com.itmo.tpo.task3.model.Movable;
import com.itmo.tpo.task3.model.impl.Passage;

public class NoAccessToPassageException extends RuntimeException {

    private Movable subject;
    private Passage passage;

    @Override
    public String getMessage() {
        return  "Нет доступа к проходу, он в другой локации";
    }

}
