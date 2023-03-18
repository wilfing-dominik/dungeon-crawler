package com.dungeoncrawl.logic.actors;

import com.dungeoncrawl.logic.Cell;
import com.dungeoncrawl.logic.Drawable;
import com.dungeoncrawl.logic.CellType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health;
    protected int currentHealth;
    protected int dmg;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }


    public void move(int dx, int dy) {
        if (this.currentHealth > 0) {
            Cell nextCell = cell.getNeighbor(dx, dy);
            if (!nextCell.getType().equals(CellType.WALL) && nextCell.getActor() == null) {
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
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

    public int getHealth() {
        return health;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void autoMove() {
    }

    ;

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void attack(Actor attacker, Actor defender) {
        defender.currentHealth -= attacker.dmg;
    }

    public void fight(Actor player, Actor monster) {

        ArrayList<String> logs = new ArrayList<>();

        while (player.currentHealth > 0 && monster.currentHealth > 0) {
            /*try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            attack(player, monster);
            logs.add(player.getTileName() + " hit " + monster.getTileName() + " for " + player.dmg + " dmg");
            if (monster.currentHealth > 0) {
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                attack(monster, player);
                logs.add(monster.getTileName() + " hit " + player.getTileName() + " for " + monster.dmg + " dmg");
            }

        }
        logs.add("Fight is over");

        Stage stage = new Stage();
        stage.setTitle("Fight result");

        VBox layout  = new VBox();
        Scene scene = new Scene(layout, 300, 300);

        stage.setScene(scene);
        stage.show();

        ListView lw = new ListView<>();
        for (String log:logs) {

            lw.getItems().add(log);

        }
        layout.getChildren().add(lw);

        Button btn = new Button();
        btn.setText("Close window");
        layout.getChildren().add(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });


    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
