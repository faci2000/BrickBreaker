package Adds;

import Models.Platform;
import javafx.scene.paint.Color;

public class ExtendPlatform extends Extra {
    @Override
    public Color getBrickColor() {
        return Color.GREEN;
    }

    @Override
    public void getModifiedGameItems(GameElements gameElements) {
        gameElements.getPlatform().getPlatformRepresentation().setWidth(gameElements.getPlatform().getPlatformRepresentation().getWidth()*1.2);
        Platform.setWidth(gameElements.getPlatform().getPlatformRepresentation().getWidth());
    }
}
