package Adds;

import javafx.scene.paint.Color;

public class FasterBall extends  Extra{
    @Override
    public Color getBrickColor() {
        return Color.YELLOW;
    }

    @Override
    public void getModifiedGameItems(GameElements gameElements) {
        gameElements.getBalls().forEach(x->x.getSpeed().accelerate(1.1));
    }
}
