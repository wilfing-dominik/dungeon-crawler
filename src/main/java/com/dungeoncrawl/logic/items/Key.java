package com.dungeoncrawl.logic.items;

import com.dungeoncrawl.logic.Cell;

public class Key extends Item{

    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
