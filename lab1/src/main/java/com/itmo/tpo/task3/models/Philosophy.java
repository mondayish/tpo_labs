package com.itmo.tpo.task3.models;

import com.itmo.tpo.task3.models.impl.GemStone;

public interface Philosophy {

    // заметить
    void notice(GemStone gemStone);

    // думать
    void think(Thing obj);
}