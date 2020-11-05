package model;

import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private Checker checker;

    public boolean hasChecker() {
        return checker != null;
    }

    public Checker getChecker() {
        return checker;
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public Tile(boolean light, int x, int y) {
        setWidth(Size.TILE.getSize());
        setHeight(Size.TILE.getSize());
        relocate(x * Size.TILE.getSize(), y * Size.TILE.getSize());

        setFill(light ? Color.valueOf("#3E97D1") : Color.valueOf("#0A67A3"));
    }
}
