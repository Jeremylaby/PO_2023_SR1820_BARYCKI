package agh.ics.oop.model.util;

import agh.ics.oop.model.WorldElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class WorldElementBox extends StackPane{
    private final  static double CELL_WIDTH=50;
    private final  static double CELL_HEIGHT=50;
    private WorldElement worldElement;

    public WorldElementBox(WorldElement worldElement) {
        this.worldElement = worldElement;
        Image image = new Image(getClass().getResourceAsStream("/images/" + worldElement.getTexture()));
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        Label label = new Label(worldElement.getTextureText());
        imageView.setFitWidth(CELL_WIDTH);
        imageView.setFitHeight(CELL_HEIGHT);
        this.getChildren().addAll(imageView, label);
        label.setAlignment(Pos.CENTER);
    }
}
