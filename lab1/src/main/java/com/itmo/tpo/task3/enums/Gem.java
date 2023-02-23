package com.itmo.tpo.task3.enums;

public enum Gem {
    RUBY("Рубин"),
    GARNET("Гранат"),
    CRYSTAL("Хрусталь");
    private final String russian;

    Gem(String russian) {
        this.russian = russian;
    }

    public String getRussian() {
        return russian;
    }
}
