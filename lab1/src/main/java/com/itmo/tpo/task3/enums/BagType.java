package com.itmo.tpo.task3.enums;

public enum BagType {
    BACKPACK("Рюкзак"),
    CASE("Чемодан"),
    LADY_BAG("Женская сумочка");

    private final String russian;

    BagType(String russian) {
        this.russian = russian;
    }

    public String getRussian() {
        return russian;
    }
}
