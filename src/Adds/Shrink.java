package Adds;

import Models.Platform;
import javafx.scene.paint.Color;

public class Shrink extends Extra{
    @Override
    public Color getBrickColor() {
        return Color.WHITE;
    }

    @Override
    public void getModifiedGameItems(GameElements gameElements) {
        gameElements.getPlatform().getPlatformRepresentation().setWidth(gameElements.getPlatform().getPlatformRepresentation().getWidth()*0.5);
        Platform.setWidth(gameElements.getPlatform().getPlatformRepresentation().getWidth());
    }
}
