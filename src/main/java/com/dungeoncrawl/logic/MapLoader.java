package com.dungeoncrawl.logic;

import com.dungeoncrawl.logic.actors.*;
import com.dungeoncrawl.logic.environment.Fire;
import com.dungeoncrawl.logic.environment.Trap;
import com.dungeoncrawl.logic.items.Key;
import com.dungeoncrawl.logic.items.Potion;
import com.dungeoncrawl.logic.items.Sword;
import com.dungeoncrawl.logic.passages.Door;
import com.dungeoncrawl.logic.passages.Exit;
import com.dungeoncrawl.logic.passages.Opened;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static Player player;
    public static GameMap loadMap(int level) {
        String levelSelect = String.format("/map%d.txt", level);
        InputStream is = MapLoader.class.getResourceAsStream(levelSelect);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            if(player == null){
                                player = new Player(cell);
                            }else{
                                cell.setActor(player);
                                player.setCell(cell);
                            }
                            map.setPlayer(player);
                            break;
                        case 't':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.WALL);
                            new Door(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            new Opened(cell);
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            new Ghost(cell);
                            break;
                        case 'q':
                            cell.setType(CellType.FLOOR);
                            new Squid(cell);
                            break;
                        case 'X':
                            cell.setType(CellType.FLOOR);
                            new Exit(cell);
                            break;
                        case 'f':
                            cell.setType(CellType.FLOOR);
                            new Fire(cell);
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            new Trap(cell);
                            break;
                        case 'l':
                            cell.setType(CellType.FLOOR);
                            new Lich(cell);
                            break;
                        case 'e':
                            cell.setType(CellType.FLOOR);
                            new LostKnight(cell);
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            new Potion(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'" + "Char Number: " + x);
                    }
                }
            }
        }
        return map;
    }

}
