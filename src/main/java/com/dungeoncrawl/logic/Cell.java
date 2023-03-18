package com.dungeoncrawl.logic;

import com.dungeoncrawl.logic.actors.Actor;
import com.dungeoncrawl.logic.environment.Fire;
import com.dungeoncrawl.logic.environment.Trap;
import com.dungeoncrawl.logic.items.Item;
import com.dungeoncrawl.logic.passages.Passage;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private Item item;
    private Passage passage;
    private Fire fire;
    private Trap trap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Actor getActor() {
        return actor;
    }

    public Item getItem() {
        return item;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPassage(Passage passage){
        this.passage = passage;
    }

    public Passage getPassage(){
        return this.passage;
    }

    public Fire getFire(){
        return this.fire;
    }

    public void setFire(Fire fire){
        this.fire = fire;
    }
    public Trap getTrap() {
        return this.trap;
    }
    public void setTrap(Trap trap) {
        this.trap=trap;
    }


}


