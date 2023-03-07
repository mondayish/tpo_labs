package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.exceptions.NoAccessToPassageException;
import com.itmo.tpo.task3.model.Alive;
import com.itmo.tpo.task3.model.Describable;
import com.itmo.tpo.task3.model.Movable;
import lombok.Data;

@Data
public class Monster implements Alive, Movable, Describable {

    private String name;
    private Environment location;

    public String generateSound(String noise){
        String message = description() + " издало \"" + noise + "\"." ;
        location.setSound(noise);
        return message;
    }

    public String usePassage(Passage passage) {
        if (!location.equals(passage.getThisSide()) && !location.equals(passage.getOtherSide()))
            throw new NoAccessToPassageException();
        String message;
        if (passage.isLocked()) message = description() + " хлюпая, " +
                "попыталось выдавить свою зелёную, желеобразную тушу через " + passage.description() + ", но " +
                "проход оказался заперт";
        else {
            message = description() + " хлюпая слизью, продавило свою зелёную, желеобразную тушу через " + passage.description();
            if (location.equals(passage.getThisSide())) setLocation(passage.getOtherSide());
            else setLocation(passage.getThisSide());
        }
        return message;
    }

    public String generateGoo(){
        location.getThings().add(new EnvironmentThing("Комок слизи"));
        return description() + " источает слизь.";
    }

    public String generateSmell(){
        location.setSmell("смрад");
        return description() + " источает смрад.";
    }

    @Override
    public String description() {
        return getName();
    }
}
