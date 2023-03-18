package com.dungeoncrawl.logic.environment;

import com.dungeoncrawl.logic.Cell;
import com.dungeoncrawl.logic.Drawable;

public class Trap implements Drawable {
    private int damage;
    private Cell cell;

    public Trap(Cell cell){
        this.cell = cell;
        cell.setTrap(this);
        damage = 3;
    }

    public int getDamage(){
        return damage;
    }


    @Override
    public String getTileName() {
        return "trap";
    }
}
