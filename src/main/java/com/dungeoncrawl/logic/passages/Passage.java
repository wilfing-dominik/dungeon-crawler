package com.dungeoncrawl.logic.passages;

import com.dungeoncrawl.logic.Cell;
import com.dungeoncrawl.logic.Drawable;

public abstract class Passage implements Drawable {
    private Cell cell;

    public Passage(Cell cell){
        this.cell = cell;
        this.cell.setPassage(this);
    }
}
