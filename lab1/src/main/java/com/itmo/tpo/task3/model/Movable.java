package com.itmo.tpo.task3.model;

import com.itmo.tpo.task3.model.exceptions.NoAccessToPassageException;
import lombok.SneakyThrows;

public interface Movable {

    @SneakyThrows
    String usePassage(Passage passage) throws NoAccessToPassageException;

}
