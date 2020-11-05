package model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import model.ColorType;
import model.Size;

public class Checker extends StackPane {

    private ColorType colorType;
    private double mouseX, mouseY;
    private double oldX, oldY;

    public ColorType getColor() {
        return colorType;
    }

    public Checker(ColorType colorType, int x, int y) {
        this.colorType = colorType;
        move(x * Size.TILE.getSize(), y * Size.TILE.getSize());
        Ellipse backGround = new Ellipse(Size.TILE.getSize() * 0.3, Size.TILE.getSize() * 0.3);
        backGround.setFill(colorType == ColorType.ORANGE ? Color.valueOf("#FFA07A") : Color.valueOf("#FFFFFF"));
        backGround.setStroke(Color.BLACK);
        backGround.setStrokeWidth(Size.TILE.getSize() * 0.03);
        backGround.setTranslateX((Size.TILE.getSize() - Size.TILE.getSize() * 0.3 * 2) / 2);
        backGround.setTranslateY((Size.TILE.getSize() - Size.TILE.getSize() * 0.3 * 2) / 2);

        getChildren().addAll(backGround);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX,e.getSceneY() - mouseY + oldY );
        });
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
}
