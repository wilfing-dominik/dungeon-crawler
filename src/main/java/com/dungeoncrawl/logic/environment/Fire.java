package com.dungeoncrawl.logic.environment;

import com.dungeoncrawl.logic.Cell;
import com.dungeoncrawl.logic.Drawable;

public class Fire implements Drawable {
    private int damage;
    private Cell cell;

    public Fire(Cell cell){
        this.cell = cell;
        cell.setFire(this);
        damage = 3;
    }

    public int getDamage(){
        return damage;
    }


    @Override
    public String getTileName() {
        return "fire";
    }
}
