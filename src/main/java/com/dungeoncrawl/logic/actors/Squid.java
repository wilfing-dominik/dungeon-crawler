package com.dungeoncrawl.logic.actors;

import com.dungeoncrawl.logic.Cell;
import com.dungeoncrawl.logic.CellType;

import java.util.Random;

public class Squid extends Actor{
    private int direction;
    private int step = 1;
    public Squid(Cell cell) {
        super(cell);
        this.health = 8;
        this.dmg = 4;
        this.currentHealth= this.health;
        Random random = new Random();
        direction = random.nextInt(2) + 1;
    }

    @Override
    public void autoMove() {
        int x = 0;
        int y = 0;
        if (direction == 1) {
            x += step;
        } else {
            y += step;
        }
        Cell nextCell = this.getCell().getNeighbor(x, y);
        if (nextCell.getType().equals(CellType.WALL)) {
            if (step == 1) {
                step = -1;
            } else {
                step = 1;
            }
        }
        move(x, y);

    }

    @Override
    public String getTileName() {
        return "squid";
    }
}
