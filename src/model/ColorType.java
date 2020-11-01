package model;

public enum ColorType {
    ORANGE(1), WHITE(-1);

    final int moveDir;

    ColorType(int moveDir) {
        this.moveDir = moveDir;
    }
}
