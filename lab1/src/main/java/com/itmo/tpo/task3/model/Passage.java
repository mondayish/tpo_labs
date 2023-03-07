package com.itmo.tpo.task3.model;

import lombok.Data;

@Data
public class Passage {

    private String name;
    private Environment thisSide;
    private Environment otherSide;
    private boolean locked;
    private int[][] surfaceCoordinates;
    private String secretSwitch;
}
