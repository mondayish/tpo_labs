package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.exceptions.NoAccessToPassageException;
import com.itmo.tpo.task3.model.Describable;
import com.itmo.tpo.task3.model.impl.Environment;
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
    public boolean tryUnlock(String probablySecretSwitch) throws NoAccessToPassageException {
        return probablySecretSwitch.equals(secretSwitch);
    }

    @Override
    public String description() {
        return getName();
    }
}
