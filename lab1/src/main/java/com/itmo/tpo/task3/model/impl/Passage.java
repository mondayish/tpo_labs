package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.model.Describable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Passage implements Describable {

    private String name;
    private Environment thisSide;
    private Environment otherSide;
    private boolean locked;
    private String secretSwitch;

    public boolean tryUnlock(String probablySecretSwitch) {
        return probablySecretSwitch.equals(secretSwitch);
    }

    @Override
    public String description() {
        return getName();
    }
}
