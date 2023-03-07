package com.itmo.tpo.task3.model;

import lombok.Data;

@Data
public class Monster implements Alive{

    private String name;
    private Environment location;

    @Override
    public void speak() {
        // todo генерирует звуки
    }

    @Override
    public void usePassage(Passage passage) {
        // todo переход из локации в локацию
    }

    public void generateGoo(){
        // todo генерирует слизь
    }
}
