package com.dungeoncrawl.logic.items;
import com.dungeoncrawl.logic.Cell;
import com.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    protected int damage;
    private Cell cell;

    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getDamage() {
        return this.damage;
    }

}
