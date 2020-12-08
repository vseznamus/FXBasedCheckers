package model;

import javafx.scene.layout.StackPane;

public class Checker extends StackPane {

    private ColorType colorType;
    private double oldX, oldY;

    public ColorType getColor() {
        return colorType;
    }

    public Checker(ColorType colorType, int x, int y) {
        this.colorType = colorType;
        move(x,y);
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public void move(int x, int y) {
        oldX = x * Size.TILE.getSize();
        oldY = y * Size.TILE.getSize();
        relocate(oldX, oldY);
    }

    public void abortMove() {
        relocate(oldX, oldY);
    }
}
