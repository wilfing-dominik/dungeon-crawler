package com.dungeoncrawl.logic.passages;

import com.dungeoncrawl.logic.Cell;

public class Opened extends Passage{


    public Opened(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "opened";
    }
}
