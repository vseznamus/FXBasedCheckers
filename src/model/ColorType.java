package model;

public enum ColorType {
    ORANGE(1), WHITE(-1);

    final int moveDirection;

    ColorType(int moveDirection) {
        this.moveDirection = moveDirection;
    }
}
