package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ColorType;
import model.Size;

public class Main extends Application {

    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();

    private Tile[][] board = new Tile[Size.WIDTH][Size.HEIGHT];


    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Size.WIDTH * Size.TILE_SIZE, Size.HEIGHT * Size.TILE_SIZE);
        root.getChildren().addAll(tileGroup,pieceGroup);

        for (int y = 0; y < Size.HEIGHT; y++) {
            for (int x = 0; x < Size.WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(ColorType.ORANGE, x, y);
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(ColorType.WHITE, x, y);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
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

    private Piece makePiece(ColorType type, int x, int y) {
        Piece piece = new Piece(type,x,y);

        return piece;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
