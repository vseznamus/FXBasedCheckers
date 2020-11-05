package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;
import service.BrainService;

public class Main extends Application {

    private Group tileGroup = new Group();
    private Group checkerGroup = new Group();
    private BrainService game = new BrainService();




    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Size.WIDTH.getSize() * Size.TILE.getSize(), Size.HEIGHT.getSize() * Size.TILE.getSize());
        root.getChildren().addAll(tileGroup, checkerGroup);
        for (int i = 0; i < game.initTiles().size(); i++) {
            tileGroup.getChildren().add(game.initTiles().get(i));
        }
        for (int i = 0; i < game.initObjects().size(); i++) {
            checkerGroup.getChildren().add(game.initObjects().get(i));
        }

        /*for (int y = 0; y < Size.HEIGHT.getSize(); y++) {
            for (int x = 0; x < Size.WIDTH.getSize(); x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                game.board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Checker checker = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    checker = game.makeChecker(ColorType.ORANGE, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    checker = game.makeChecker(ColorType.WHITE, x, y);
                }

                if (checker != null) {
                    tile.setChecker(checker);
                    checkerGroup.getChildren().add(checker);
                }
            }
        }

         */
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
