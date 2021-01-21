package Patterns;

import Models.Brick;

public class PieceOfInformation {
    private Brick brick;

    public PieceOfInformation(Brick brick){
        this.setBrick(brick);
    }

    public Brick getBrick() {
        return brick;
    }

    public void setBrick(Brick brick) {
        this.brick = brick;
    }
}
