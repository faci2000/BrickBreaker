package Models;

import Adds.Extra;
import Patterns.Observable;
import Patterns.Observer;
import Patterns.PieceOfInformation;
import Physics.Collidable;
import Physics.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class Brick implements Collidable, Observable {
    private static float width;
    private static  float height;
    Position position;
    private Rectangle brickRepresentation;
    ArrayList<Observer> observers;
    Extra type;

    public Brick(double x,double y,Extra type){
        position = new Position(x,y);
        setBrickRepresentation(new Rectangle(x,y, getWidth(), getHeight()));
        getBrickRepresentation().setFill(type.getBrickColor());
        this.type = type;
        observers = new ArrayList<>();
    }

    public void moveBrickDown(double gap){
        this.position.setY(this.position.getY()+this.getBrickRepresentation().getHeight()+gap);
        this.getBrickRepresentation().setY(this.position.getY());
    }

    public static float getWidth() {
        return width;
    }

    public static void setWidth(float width) {
        Brick.width = width;
    }

    public static float getHeight() {
        return height;
    }

    public static void setHeight(float height) {
        Brick.height = height;
    }

    @Override
    public boolean collide(Collidable object) {
        return false;
    }

    @Override
    public void onCollision(Collidable collidable) {
        this.inform(new PieceOfInformation(this));
    }

    @Override
    public void inform(PieceOfInformation pieceOfInformation) {
        for(int i=0;i<observers.size();i++){
            observers.get(i).update(pieceOfInformation);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public Rectangle getBrickRepresentation() {
        return brickRepresentation;
    }

    public void setBrickRepresentation(Rectangle brickRepresentation) {
        this.brickRepresentation = brickRepresentation;
    }
}
