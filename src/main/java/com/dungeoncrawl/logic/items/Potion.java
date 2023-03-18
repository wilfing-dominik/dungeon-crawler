package com.dungeoncrawl.logic.items;

import com.dungeoncrawl.logic.Cell;

public class Potion extends Item {

    public Potion(Cell cell) {super(cell);}

    @Override
    public String getTileName() {return "potion";}
}