package com.itmo.tpo.task3.enums;

public enum Color {
    RED("Красный"),
    BLUE("Голубой"),
    BLAKE("Чёрный"),
    PINK("Розовый"),
    WHITE("Белый"),
    GREEN("Зелёный"),
    PURPLE("Фиолетовый");

    private final String russian;

    public String getRussian() {
        return russian;
    }

    Color(String russian) {
        this.russian = russian;
    }
}
