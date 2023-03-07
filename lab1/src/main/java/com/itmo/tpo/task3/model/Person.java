package com.itmo.tpo.task3.model;

import com.itmo.tpo.task3.model.exceptions.NoAccessToPassageException;
import com.itmo.tpo.task3.model.exceptions.PassageAlreadyUnlockedException;
import com.itmo.tpo.task3.model.exceptions.PersonNotInTheSameGroupException;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class Person implements Alive, Movable{

    private String surname;
    private Group group;
    private Environment location;

    @SneakyThrows
    public String usePassage(Passage passage) {
        // todo использует проход для перемещения между локациями
        return "что-то";
    }

    public String hear(){
        // todo слышит звуки из окружения
        return "что-то";
    }

    public String lookAround(){
        // todo видит объекты в текущем окружении
        return "что-то";
    }

    public String think(Object object){
        // todo мнение о предмете/проходе/локации/человеке
        // todo нормально ли пробрасывать object?
        return "что-то";
    }

    public String generateSound(){
        // todo что-то говорит, генерирует звук в окружение
        return "что-то";
    }

    public String smell(){
        //todo
        return null;
    }

    public String speakTo(Person person) throws PersonNotInTheSameGroupException{
        // todo говорит что-то кому-то, проверка что люди в одной группе
        return "что-то";
    }

    public String follow(Person person) throws PersonNotInTheSameGroupException {
        // todo следует за членом команды
        return "что-то";
    }

    public String tryUnlockPassage() throws PassageAlreadyUnlockedException {
        //todo
        return null;
    }

}
