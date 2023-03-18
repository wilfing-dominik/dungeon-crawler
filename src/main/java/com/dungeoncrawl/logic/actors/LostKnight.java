package com.dungeoncrawl.logic.actors;

import com.dungeoncrawl.logic.Cell;

public class LostKnight extends Actor {
    public LostKnight(Cell cell) {
        super(cell);
        this.health = 25;
        this.dmg = 2;
        this.currentHealth= this.health;
    }

    @Override
    public String getTileName() {
        return "Lost Knight";
    }

    @Override
    public void move(int dx, int dy) {
    }
}
