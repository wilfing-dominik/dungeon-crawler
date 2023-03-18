package com.dungeoncrawl.logic.passages;

import com.dungeoncrawl.logic.Cell;

public class Exit extends Passage{
    public Exit(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "exit";
    }
}
