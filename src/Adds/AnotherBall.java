package Adds;

import Models.Ball;
import javafx.scene.paint.Color;

public class AnotherBall extends Extra{
    @Override
    public Color getBrickColor() {
        return Color.BLUE;
    }

    @Override
    public void getModifiedGameItems(GameElements gameElements) {
        Ball ball = new Ball();
        gameElements.getPlayground().getPlaygroundRepresentation().getChildren().add(ball.getBallRepresentation());
        gameElements.getPlayground().getNewBalls().add(ball);
    }
}
