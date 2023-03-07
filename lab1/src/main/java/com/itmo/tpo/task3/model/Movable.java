package com.itmo.tpo.task3.model;

import com.itmo.tpo.task3.model.exceptions.NoAccessToPassageException;

public interface Movable {

    String usePassage(Passage passage) throws NoAccessToPassageException;

}
