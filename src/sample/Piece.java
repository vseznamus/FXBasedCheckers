package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import model.ColorType;
import model.Size;

public class Piece extends StackPane {

    private ColorType colorType;

    public ColorType getColor() {
        return colorType;
    }

    public Piece(ColorType colorType, int x, int y) {
        this.colorType = colorType;
        relocate(x * Size.TILE_SIZE, y * Size.TILE_SIZE);
        Ellipse bg = new Ellipse(Size.TILE_SIZE * 0.3, Size.TILE_SIZE * 0.3);
        bg.setFill(colorType == ColorType.ORANGE ? Color.valueOf("#FFA07A") : Color.valueOf("#FFFFFF"));
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(Size.TILE_SIZE * 0.03);
        bg.setTranslateX((Size.TILE_SIZE - Size.TILE_SIZE * 0.3 * 2) / 2);
        bg.setTranslateY((Size.TILE_SIZE - Size.TILE_SIZE * 0.3 * 2) / 2);

        getChildren().addAll(bg);
    }
}
