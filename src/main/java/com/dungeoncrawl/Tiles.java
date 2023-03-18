package com.dungeoncrawl;

import com.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(0, 31));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("door", new Tile(5,9));
        tileMap.put("opened", new Tile(6,9));
        tileMap.put("ghost", new Tile(27, 6));
        tileMap.put("squid", new Tile(27, 8));
        tileMap.put("exit", new Tile(2,6));
        tileMap.put("fire", new Tile(15,10));
        tileMap.put("trap", new Tile(22,0));
        tileMap.put("lich",new Tile(24,0));
        tileMap.put("Lost Knight",new Tile(31,0));
        tileMap.put("potion", new Tile(16,30));

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
