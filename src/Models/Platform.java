package Models;

import Physics.Collidable;
import Physics.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform implements Collidable {
    private static double width;
    private static double height;
    Position position;
    private Rectangle platformRepresentation;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;

    public Platform(double width,double height){
        this.setWidth(width);
        this.setHeight(height);
        position = new Position(540-width/2,650);
        setPlatformRepresentation(new Rectangle(position.getX(),position.getY(),width,height));
        getPlatformRepresentation().setFill(Color.BLUE);
    }
    public Platform(double width,double height,Position position){
        this.setWidth(width);
        this.setHeight(height);
        this.position = position;
        setPlatformRepresentation(new Rectangle(position.getX(),position.getY(),width,height));
        getPlatformRepresentation().setFill(Color.BLUE);
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        Platform.width = width;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        Platform.height = height;
    }

    public void move(){
        if(isLeftPressed())
            position.setX(Math.max(0,position.getX()-10));
        else if(isRightPressed())
                position.setX(Math.min(1080- getWidth(),position.getX()+10));
        getPlatformRepresentation().setX(position.getX());
    }

    @Override
    public boolean collide(Collidable object) {
        return false;
    }

    @Override
    public void onCollision(Collidable object) {

    }

    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        isLeftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return isRightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        isRightPressed = rightPressed;
    }

    public Rectangle getPlatformRepresentation() {
        return platformRepresentation;
    }

    public void setPlatformRepresentation(Rectangle platformRepresentation) {
        this.platformRepresentation = platformRepresentation;
    }
}
