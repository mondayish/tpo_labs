package com.itmo.tpo.task3.model.impl;

import com.itmo.tpo.task3.exceptions.NoAccessToPassageException;
import com.itmo.tpo.task3.exceptions.PersonNotInTheSameGroupException;
import com.itmo.tpo.task3.model.Alive;
import com.itmo.tpo.task3.model.Describable;
import com.itmo.tpo.task3.model.Movable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class Person implements Alive, Movable, Describable {

    private String surname;
    private Group group;
    private Environment location;

    public String usePassage(Passage passage) {
        if (Objects.isNull(location) || (!location.equals(passage.getThisSide()) && !location.equals(passage.getOtherSide())))
            throw new NoAccessToPassageException(this, passage);
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
        return description() + " слышит " + Optional.ofNullable(location).map(Environment::getSound).orElse("Nothing");
    }

    public String lookAround() {
        if (Objects.isNull(location) || Objects.isNull(location.getThings()) || location.getThings().isEmpty())
            return description() + " не видит ничего интересного вокруг себя.";
        else {
            return description() + " видит " +
                    location.getThings().stream().map(EnvironmentThing::getName).sorted().collect(Collectors.joining(", ")) + ".";
        }
    }

    public String think(Describable describable, String thought) {
        return description() + " думает о " + Optional.ofNullable(describable).map(Describable::description).orElse("Nothing")
                + ": " + Optional.ofNullable(thought).orElse("Nothing") + ".";
    }

    public String generateSound(String speech) {
        String message = description() + " сказал: \"" + Optional.ofNullable(speech).orElse("Nothing") + "\".";
        location.setSound(message);
        return message;
    }

    public String smell() {
        return description() + " почуял " + Optional.ofNullable(location).map(Environment::getSmell).orElse("Nothing");
    }

    public String speakTo(Person person, String speech) {
        if (Objects.isNull(group) || Objects.isNull(group.getMembers()) || !group.getMembers().contains(person))
            throw new PersonNotInTheSameGroupException(this, person);
        return description() + " обратился к " + person.description() + " и сказал: \"" + Optional.ofNullable(speech).orElse("Nothing") + "\".";
    }

    public String follow(Person person) {
        if (Objects.isNull(group) || Objects.isNull(group.getMembers()) || !group.getMembers().contains(person)) throw new PersonNotInTheSameGroupException(this, person);
        return description() + " следует за " + person.description();
    }

    public String tryUnlockPassage(Passage passage, String probablySecretSwitch) {
        if (Objects.isNull(location) || (!location.equals(passage.getThisSide()) && !location.equals(passage.getOtherSide())))
            throw new NoAccessToPassageException(this, passage);
        StringBuilder message = new StringBuilder(description() + " пытается отпереть " + passage.description() + "...\n");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return surname.equals(person.surname) && Objects.equals(group, person.group) && Objects.equals(location, person.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname);
    }
}
