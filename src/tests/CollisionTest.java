package tests;
import Adds.Normal;
import Models.Ball;
import Models.Brick;
import Models.Platform;
import Physics.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionTest {
    @Test
    public void ballBrickCollisionTest(){
        Ball ball = new Ball();
        ball.setPosition(new Position(50,50));
        ball.getBallRepresentation().setRadius(20);
        ball.getBallRepresentation().setCenterY(ball.getPosition().getY());
        ball.getBallRepresentation().setCenterX(ball.getPosition().getX());

        Brick.setHeight(10);
        Brick.setWidth(100);

        Brick brick = new Brick(50,50, new Normal());
        assertTrue(ball.collide(brick));

        brick = new Brick(50,70, new Normal());
        assertTrue(ball.collide(brick));

        brick = new Brick(50+20/Math.sqrt(2),50+20/Math.sqrt(2), new Normal());
        assertTrue(ball.collide(brick));

        brick = new Brick(50-20/Math.sqrt(2)-100,50-20/Math.sqrt(2)-10, new Normal());
        assertTrue(ball.collide(brick));

        brick = new Brick(50+20/Math.sqrt(2),50-20/Math.sqrt(2)-10, new Normal());
        assertTrue(ball.collide(brick));
    }

    @Test
    public void ballPlatformCollisionTest(){
        Ball ball = new Ball();
        ball.setPosition(new Position(50,50));
        ball.getBallRepresentation().setRadius(20);
        ball.getBallRepresentation().setCenterY(ball.getPosition().getY());
        ball.getBallRepresentation().setCenterX(ball.getPosition().getX());


        Platform platform = new Platform(100,10,new Position(50,50));
        assertTrue(ball.collide(platform));

        platform = new Platform(100,10,new Position(50,70));
        assertTrue(ball.collide(platform));

        platform = new Platform(100,10,new Position(50+20/Math.sqrt(2),50+20/Math.sqrt(2)));
        assertTrue(ball.collide(platform));

        platform = new Platform(100,10,new Position(50-20/Math.sqrt(2)-100,50-20/Math.sqrt(2)-10));
        assertTrue(ball.collide(platform));

        platform = new Platform(100,10,new Position(50+20/Math.sqrt(2),50-20/Math.sqrt(2)-10));
        assertTrue(ball.collide(platform));

    }
}


