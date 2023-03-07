package com.itmo.tpo.task3.model;

import com.itmo.tpo.task3.model.exceptions.NoAccessToPassageException;
import com.itmo.tpo.task3.model.exceptions.PersonNotInTheSameGroupException;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class Person implements Alive, Movable, Describable {

    private String surname;
    private Group group;
    private Environment location;

    @SneakyThrows
    public String usePassage(Passage passage) {
        if (!location.equals(passage.getThisSide()) && !location.equals(passage.getOtherSide()))
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

    public String hear(){
        return description() + " слышит " + location.getSound();
    }

    public String lookAround(){
        StringBuilder message;
        if (location.getThings().isEmpty()) message = new StringBuilder(description() + " не видит ничего интересного вокруг себя.");
        else {
            message = new StringBuilder(description() + " видит ");
            for (EnvironmentThing thing : location.getThings()) {
                message.append(thing.description()).append(", ");
            }
            message.replace(message.length() - 2, message.length() - 1, ".");
        }
        return message.toString();
    }

    public String think(Describable object, String thought){
        return description() + " думает о " + object.description() + ": " + thought + ".";
    }

    public String generateSound(String speech){
        String message = description() + " сказал: \"" + speech + "\"." ;
        location.setSound(message);
        return message;
    }

    public String smell(){
        return description() + " почуял " + location.getSmell();
    }

    @SneakyThrows
    public String speakTo(Person person, String speech) throws PersonNotInTheSameGroupException{
        if (!group.getMembers().contains(person)) throw new PersonNotInTheSameGroupException();
        return description() + " обратился к " + person.description() + " и сказал: " + speech + ".";
    }

    @SneakyThrows
    public String follow(Person person) throws PersonNotInTheSameGroupException {
        if (!group.getMembers().contains(person)) throw new PersonNotInTheSameGroupException();
        return description() + " следует за " + person.description();
    }

    @SneakyThrows
    public String tryUnlockPassage(Passage passage, String probablySecretSwitch) throws NoAccessToPassageException {
        if (!location.equals(passage.getThisSide()) && !location.equals(passage.getOtherSide()))
            throw new NoAccessToPassageException();
        StringBuilder message = new StringBuilder(description() + "пытается отпереть " + passage.description() + "...\n");
        if (!passage.isLocked()) message.append("Но проход оказался не заперт.");
        else {
            message.append("Он пробует нажать на ").append(probablySecretSwitch).append("...\n");
            if (probablySecretSwitch.equals(passage.getSecretSwitch())) message.append("И дверь открылась!");
            else message.append("Но ничего не произошло.");
        }
        
        return message.toString();
    }


    @Override
    public String description() {
        return getSurname();
    }
}
