package com.itmo.tpo.task3.model;

import com.itmo.tpo.task3.model.exceptions.NoAccessToPassageException;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class Passage implements Describable {

    private String name;
    private Environment thisSide;
    private Environment otherSide;
    private boolean locked;
    private String secretSwitch;

    @SneakyThrows
    public String tryUnlock(String coordinate) throws NoAccessToPassageException {
        //todo
        return null;
    }

    @Override
    public String description() {
        return getName();
    }
}
