package com.dungeoncrawl.logic.actors;


import com.dungeoncrawl.logic.Cell;
import com.dungeoncrawl.logic.items.Item;
import com.dungeoncrawl.logic.CellType;
import com.dungeoncrawl.logic.passages.Opened;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player extends Actor {
    List<Item> inventory = new ArrayList<>();

    public Player(Cell cell) {
        super(cell);
        this.health = 10;
        this.dmg = 4;
        this.currentHealth = this.health;
    }

    public String getTileName() {
        return "player";
    }

    public void pickUpItem() {
        Random rand;
        if (this.getCell().getItem().getTileName().equals("potion")){
            rand= new Random();
            this.currentHealth += rand.nextInt(3)+1;
            if (this.currentHealth>10) {this.currentHealth=10;}
            this.getCell().setItem(null);
        }
        if (this.getCell().getItem() != null) {
            this.inventory.add(this.getCell().getItem());
            if (this.getCell().getItem().getTileName().equals("sword")) {
                this.dmg += this.getCell().getItem().getDamage();
            }
            this.getCell().setItem(null);
        }
    }

    public List<Item> getInventory() {
        return this.inventory;
    }

    ;

    public String getInventoryAsString() {
        StringBuilder inventory = new StringBuilder();
        for (int i = 0; i < this.inventory.size(); i++) {
            inventory.append("| ");
            inventory.append(this.inventory.get(i).getTileName());
            inventory.append("\n");
        }
        return inventory.toString();
    }

    @Override
    public void move(int dx, int dy) {
        if (this.currentHealth > 0) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (!nextCell.getType().equals(CellType.WALL) && nextCell.getActor() == null) {
                if (nextCell.getFire() != null) {
                    cell.getActor().currentHealth -= nextCell.getFire().getDamage();
                }

                if(nextCell.getTrap() != null){
                    cell.getActor().currentHealth -= nextCell.getTrap().getDamage();
                }


                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;

            }
            else if (nextCell.getPassage() != null && nextCell.getPassage().getTileName().equals("door")) {
                for (Item item : this.getInventory()) {
                    if (item.getTileName().equals("key")) {
                        nextCell.setPassage(new Opened(cell));
                        cell.setPassage(null);
                    }
                }
            } else if (nextCell.getPassage() != null && nextCell.getPassage().getTileName().equals("opened")) {
                cell.setActor(null);
                nextCell.getNeighbor(dx, dy).setActor(this);
                cell = nextCell.getNeighbor(dx, dy);
            } else if (nextCell.getPassage() != null && nextCell.getPassage().getTileName().equals("exit")) {
                System.out.println("load next map");
            } else if (nextCell.getActor() != null && (this.getTileName().equals("player") || nextCell.getActor().getTileName().equals("player"))) {
                fight(cell.getActor(), nextCell.getActor());
                if (cell.getActor().currentHealth > 0) {
                    nextCell.setActor(null);
                } else {
                    this.cell.setActor(null);
                }
            }
        } else if (this.getTileName().equals("player")) {
            System.out.println("GAME OVER");
        }
    }
}

