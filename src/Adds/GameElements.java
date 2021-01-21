package Adds;

import Models.Ball;
import Models.Brick;
import Models.Platform;
import Models.Playground;

import java.util.ArrayList;

public class GameElements {
    private ArrayList<Ball> balls;
    private ArrayList<Brick> bricks;
    private Platform platform;
    private Playground  playground;

    public  GameElements(ArrayList<Ball> balls,ArrayList<Brick> bricks,Platform platform,Playground playground){
        this.setBalls(balls);
        this.setBricks(bricks);
        this.setPlatform(platform);
        this.setPlayground(playground);
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Playground getPlayground() {
        return playground;
    }

    public void setPlayground(Playground playground) {
        this.playground = playground;
    }
}
