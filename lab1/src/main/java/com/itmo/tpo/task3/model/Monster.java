package com.itmo.tpo.task3.model;

import lombok.Data;

@Data
public class Monster implements Alive, Movable, Describable {

    private String name;
    private Environment location;

    public String generateSound(String noise) {
        // todo генерирует звуки
        return "что-то";
    }

    public String usePassage(Passage passage) {
        // todo переход из локации в локацию
        return "что-то";
    }

    public String generateGoo(){
        // todo генерирует слизь
        return "что-то";
    }

    public String generateSmell(){
        //todo
        return null;
    }

    @Override
    public String description() {
        return getName();
    }
}
