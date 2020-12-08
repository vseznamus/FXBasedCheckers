package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;
import model.move.MoveResult;
import model.move.MoveType;
import org.w3c.dom.Text;
import service.BrainService;

import java.awt.*;

public class EntryClass extends Application {

    BrainService game = new BrainService();

    private Group tileGroup = new Group();
    Group checkerGroup = new Group();
    Group overGroup = new Group();
    private double mouseX, mouseY;


    Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Size.WIDTH.getSize() * Size.TILE.getSize(), Size.HEIGHT.getSize() * Size.TILE.getSize());
        root.getChildren().addAll(tileGroup, checkerGroup, overGroup);

        for (int i = 0; i < game.initTiles().size(); i++) {
            Tile newTile = game.initTiles().get(i);
            newTile.setWidth(Size.TILE.getSize());
            newTile.setHeight(Size.TILE.getSize());
            newTile.relocate(newTile.getTileX() * Size.TILE.getSize(), newTile.getTileY() * Size.TILE.getSize());
            newTile.setFill(newTile.isLight() ? Color.valueOf("#3E97D1") : Color.valueOf("#0A67A3"));
            tileGroup.getChildren().add(newTile);
        }

        for (int i = 0; i < game.initObjects().size(); i++) {
            Checker newChecker = game.initObjects().get(i);
            Ellipse backGround = new Ellipse(Size.TILE.getSize() * 0.3, Size.TILE.getSize() * 0.3);
            backGround.setFill(game.initObjects().get(i).getColor() == ColorType.ORANGE ? Color.valueOf("#FFA07A") : Color.valueOf("#FFFFFF"));
            backGround.setStroke(Color.BLACK);
            backGround.setStrokeWidth(Size.TILE.getSize() * 0.03);
            backGround.setTranslateX((Size.TILE.getSize() - Size.TILE.getSize() * 0.3 * 2) / 2);
            backGround.setTranslateY((Size.TILE.getSize() - Size.TILE.getSize() * 0.3 * 2) / 2);


            newChecker.getChildren().addAll(backGround);

            newChecker.setOnMousePressed(e -> {
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();
            });

            newChecker.setOnMouseDragged(e -> {
                newChecker.relocate(e.getSceneX() - mouseX + newChecker.getOldX(), e.getSceneY() - mouseY + newChecker.getOldY());
            });
            checkerGroup.getChildren().add(newChecker);

            newChecker.setOnMouseReleased(e -> {
                game.moveSwitcher(newChecker);
                if (!game.checkerRemover.isEmpty()) {
                    checkerGroup.getChildren().remove(game.checkerRemover.get(0));
                    game.checkerRemover.remove(0);
                }

            });

        }
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("CHECKERS BY VSEZNAMUS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
