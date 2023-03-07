package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.exceptions.NoAccessToPassageException;
import com.itmo.tpo.task3.exceptions.PersonNotInTheSameGroupException;
import com.itmo.tpo.task3.model.Alive;
import com.itmo.tpo.task3.model.Describable;
import com.itmo.tpo.task3.model.Movable;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Person implements Alive, Movable, Describable {

    private String surname;
    private Group group;
    private Environment location;

    public String usePassage(Passage passage) {
        if (Objects.isNull(location) || (!location.equals(passage.getThisSide()) && !location.equals(passage.getOtherSide())))
            throw new NoAccessToPassageException();
        String message;
        if (passage.isLocked()) message = description() + " пытался пройти через " + passage.description() + ", но " +
                "проход оказался заперт";
        else {
            message = description() + " прошёл через " + passage.description();
            if (location.equals(passage.getThisSide())) setLocation(passage.getOtherSide());
            else setLocation(passage.getThisSide());
        }

        return message;
    }

    public String hear() {
        return description() + " слышит " + location.getSound();
    }

    public String lookAround() {
        StringBuilder message;
        if (location.getThings().isEmpty())
            message = new StringBuilder(description() + " не видит ничего интересного вокруг себя.");
        else {
            message = new StringBuilder(description() + " видит ");
            for (EnvironmentThing thing : location.getThings()) {
                message.append(thing.description()).append(", ");
            }
            message.replace(message.length() - 2, message.length() - 1, ".");
        }
        return message.toString();
    }

    public String think(Describable object, String thought) {
        return description() + " думает о " + object.description() + ": " + thought + ".";
    }

    public String generateSound(String speech) {
        String message = description() + " сказал: \"" + speech + "\".";
        location.setSound(message);
        return message;
    }

    public String smell() {
        return description() + " почуял " + location.getSmell();
    }

    public String speakTo(Person person, String speech) {
        if (!group.getMembers().contains(person)) throw new PersonNotInTheSameGroupException();
        return description() + " обратился к " + person.description() + " и сказал: " + speech + ".";
    }

    public String follow(Person person) {
        if (!group.getMembers().contains(person)) throw new PersonNotInTheSameGroupException();
        return description() + " следует за " + person.description();
    }

    public String tryUnlockPassage(Passage passage, String probablySecretSwitch) {
        if (!location.equals(passage.getThisSide()) && !location.equals(passage.getOtherSide()))
            throw new NoAccessToPassageException();
        StringBuilder message = new StringBuilder(description() + "пытается отпереть " + passage.description() + "...\n");
        if (!passage.isLocked()) message.append("Но проход оказался не заперт.");
        else {
            message.append("Он пробует нажать на ").append(probablySecretSwitch).append("...\n");
            if (passage.tryUnlock(probablySecretSwitch)) message.append("И проход открылся!");
            else message.append("Но ничего не произошло.");
        }

        return message.toString();
    }


    @Override
    public String description() {
        return getSurname();
    }
}
