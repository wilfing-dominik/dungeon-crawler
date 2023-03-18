package com.dungeoncrawl.logic.actors;

import com.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Lich extends Actor{


    public Lich(Cell cell) {
        super(cell);
        this.health = 16;
        this.dmg =6;
        this.currentHealth= this.health;
    }

    public void autoMove() {
        Random random = new Random();
        int x = random.nextInt(3) - 1;
        int y = 0;
        if (x == 0) {
            y = random.nextInt(3) - 1;
        }
        this.move(x, y);
    }

    @Override
    public String getTileName() {
        return "lich";
    }
}
