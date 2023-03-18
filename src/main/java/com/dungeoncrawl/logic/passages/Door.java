package com.dungeoncrawl.logic.passages;

import com.dungeoncrawl.logic.Cell;

public class Door extends Passage{

    public Door(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName() {
        return "door";
    }
}
