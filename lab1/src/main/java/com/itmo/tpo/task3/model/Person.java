package com.itmo.tpo.task3.model;

import lombok.Data;

@Data
public class Person implements Alive{

    private String surname;
    // todo национальность?))
    private Group group;
    // todo нужен ли отдельный класс группы?
    private Environment location;

    @Override
    public String usePassage(Passage passage){
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

    @Override
    public String speak(){
        // todo что-то говорит, генерирует звук в окружение
        return "что-то";
    }

    public String smell(){
        //todo
        return null;
    }

    public String speakTo(Person person){
        // todo говорит что-то кому-то, проверка что люди в одной группе
        return "что-то";
    }

    public String follow(Person person){
        // todo следует за членом команды
        return "что-то";
    }

}
