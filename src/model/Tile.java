package model;

import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private Checker checker;
    private boolean light;
    private double tileX;
    private double tileY;

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
        this.light = light;
        this.tileX = x;
        this.tileY = y;
    }

    public boolean isLight() {
        return light;
    }

    public double getTileX() {
        return tileX;
    }

    public double getTileY() {
        return tileY;
    }
}
