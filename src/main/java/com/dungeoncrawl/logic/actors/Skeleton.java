package com.dungeoncrawl.logic.actors;

import com.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
        this.health = 10;
        this.dmg = 2;
        this.currentHealth= this.health;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void move(int dx, int dy) {
    }
}
