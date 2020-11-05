package model;

public enum Size {
    TILE(100), WIDTH(8), HEIGHT(8);

    private int size;

    Size(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
