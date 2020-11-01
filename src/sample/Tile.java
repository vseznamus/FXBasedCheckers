package sample;

import javafx.scene.Scene;
import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import model.Size;

public class Tile extends Rectangle {

    private Piece piece;

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Tile(boolean light, int x, int y) {
        setWidth(Size.TILE_SIZE);
        setHeight(Size.TILE_SIZE);
        relocate(x * Size.TILE_SIZE, y * Size.TILE_SIZE);

        setFill(light ? Color.valueOf("#0A67A3") : Color.valueOf("#3E97D1"));
    }
}
