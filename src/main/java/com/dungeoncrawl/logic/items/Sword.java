package com.dungeoncrawl.logic.items;

import com.dungeoncrawl.logic.Cell;

public class Sword extends Item{

    public Sword(Cell cell) {
        super(cell);
        this.damage =5;
    }

    @Override
    public String getTileName() {
        return "sword";
    }



}
