package com.dungeoncrawl;

import com.dungeoncrawl.logic.Cell;
import com.dungeoncrawl.logic.MapLoader;
import com.dungeoncrawl.logic.actors.Actor;
import com.dungeoncrawl.logic.GameMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    int maxLevel =4;
    int currentMap =1;
    GameMap map = MapLoader.loadMap(currentMap);
    int windowSizeX = 10;
    int windowSizeY = 8;
    Canvas canvas = new Canvas(
            (windowSizeX * 2 - 1) * Tiles.TILE_WIDTH,
        (windowSizeY * 2 - 1 ) *  Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    List<Actor> enemies = new ArrayList<>();
    Label healthLabel = new Label();
    Label inventory = new Label();
    Label toolTip = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(healthLabel, 0, 0);
        ui.add(inventory, 0, 1);
        ui.add(toolTip, 0, 2);
        ui.setVgap(20);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                checkExit();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                checkExit();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                checkExit();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                checkExit();
                refresh();
                break;
            case F:
                map.getPlayer().pickUpItem();
                refresh();
                break;
        }
    }

    private void checkExit() {
        if(map.getPlayer().getCell().getPassage() != null && map.getPlayer().getCell().getPassage().getTileName().equals("exit") && currentMap < maxLevel){
            currentMap += 1;
            map = MapLoader.loadMap(currentMap);
            map.getPlayer().getInventory().clear();
            Canvas canvas = new Canvas(
                    (windowSizeX * 2 - 1) * Tiles.TILE_WIDTH,
                    (windowSizeY * 2 - 1) * Tiles.TILE_WIDTH);
        }
    }

    private void moveEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).autoMove();
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        moveEnemies();
        enemies.clear();
        int[] yCoords = getRenderCoordinates(map.getPlayer().getY(), windowSizeY, map.getHeight());
        int[] xCoords = getRenderCoordinates(map.getPlayer().getX(), windowSizeX, map.getWidth());
        int minY = yCoords[0];
        int maxY = yCoords[1];
        int minX = xCoords[0];
        int maxX = xCoords[1];
        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x - minX, y - minY);
                    if (!cell.getActor().getTileName().equals("player")) {
                        enemies.add(cell.getActor());
                    }
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x - minX, y - minY);

                } else if (cell.getFire() != null) {
                    Tiles.drawTile(context, cell.getFire(), x - minX, y - minY);

                }else if (cell.getTrap() != null) {
                    Tiles.drawTile(context,cell.getTrap(), x-minX,y-minY);
                }else if (cell.getPassage() != null) {
                    Tiles.drawTile(context, cell.getPassage(), x - minX, y - minY);
                } else {
                    Tiles.drawTile(context, cell, x - minX, y - minY);
                }
            }
        }
        if (map.getPlayer().getCell().getItem() != null) {
            toolTip.setText("Press F to pick up " + map.getPlayer().getCell().getItem().getTileName());
        } else {
            toolTip.setText("");
        }
        healthLabel.setText("Health: " + map.getPlayer().getCurrentHealth());
        inventory.setText("Inventory: \n" + map.getPlayer().getInventoryAsString());
    }

    private int[] getRenderCoordinates(int playerPos, int windowSize, int absoluteSize) {
        int[] coords = new int[2];
        int min = 0;
        int max = 0;
        if (windowSize + playerPos < absoluteSize) {
            max = windowSize + playerPos;
        } else {
            max = absoluteSize;
            min = max - (windowSize * 2 - 1);
        }
        if (playerPos - windowSize >= 0 && min == 0) {
            min = playerPos -windowSize + 1;
        } else if(min == 0) {
            min = 0;
            max = windowSize * 2 + 1;
        }
        coords[0] = min;
        coords[1] = max;
        return coords;
    }
}
