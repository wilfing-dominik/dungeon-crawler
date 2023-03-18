package com.dungeoncrawl.logic;

import com.dungeoncrawl.logic.actors.Player;
import com.dungeoncrawl.logic.passages.Door;
import com.dungeoncrawl.logic.environment.Fire;
import com.dungeoncrawl.logic.items.Potion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    GameMap gameMap = new GameMap(4, 4, CellType.FLOOR);

    @Test
    void NoKeyDoor(){
        //If the player does not have a key in their inventory, the door shan't open
        Player player = new Player(gameMap.getCell(1, 1));
        Door door = new Door(gameMap.getCell(2, 1));
        player.move(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertEquals("door", door.getTileName());

    }

    @Test
    void IsDamaged(){
        Player player = new Player(gameMap.getCell(1, 1));
        Fire fire = new Fire(gameMap.getCell(2, 1));
        player.move(1, 0);

        assertEquals(7, player.getCurrentHealth());
    }

    @Test
    void IsHealed(){
        Player player = new Player(gameMap.getCell(1, 1));
        Fire fire = new Fire(gameMap.getCell(2, 1));
        Potion potion= new Potion(gameMap.getCell(3, 1));
        player.move(1, 0);
        player.move(1, 0);
        player.pickUpItem();

        assertTrue(player.getCurrentHealth()>7);
    }


}
