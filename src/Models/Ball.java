package Models;

import Physics.Collidable;
import Physics.Position;
import Physics.Speed;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball implements Collidable {
    private Position position;
    private Speed speed;
    private Circle ballRepresentation;


    public Ball(){
        this.setPosition(new Position(540,360));
        this.setSpeed(new Speed(-1,-1));
        this.setBallRepresentation(new Circle(this.getPosition().getX(), this.getPosition().getY(),20));
        this.getBallRepresentation().setFill(Color.YELLOW);
    }

    public void move(float timeDelta){
        System.out.println(this);
        this.getPosition().move(this.getSpeed(),timeDelta);
        getBallRepresentation().setCenterX(this.getPosition().getX());
        getBallRepresentation().setCenterY(this.getPosition().getY());
    }

    public boolean hasTouchedTheGround(){
        return (this.getPosition().getY()+this.getBallRepresentation().getRadius())>=720;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Circle getBallRepresentation() {
        return ballRepresentation;
    }

    public void setBallRepresentation(Circle ballRepresentation) {
        this.ballRepresentation = ballRepresentation;
    }

    @Override
    public boolean collide(Collidable object) {
        if(object instanceof Brick){
            //System.out.println("Checking brick");
            double deltaX = this.position.getX() - Math.max(((Brick) object).position.getX(), Math.min(this.position.getX(), ((Brick) object).position.getX() + Brick.getWidth()));
            double deltaY = this.position.getY() - Math.max(((Brick) object).position.getY(), Math.min(this.position.getY(), ((Brick) object).position.getY() + Brick.getHeight()));
            return (deltaX * deltaX + deltaY * deltaY) - (this.getBallRepresentation().getRadius() * this.getBallRepresentation().getRadius())<= 0.0001 ;
        }
        else if(object instanceof Platform){
            double deltaX = this.position.getX() - Math.max(((Platform) object).position.getX(), Math.min(this.position.getX(), ((Platform) object).position.getX() + Platform.getWidth()));
            double deltaY = this.position.getY() - Math.max(((Platform) object).position.getY(), Math.min(this.position.getY(), ((Platform) object).position.getY() + Platform.getHeight()));
            return (deltaX * deltaX + deltaY * deltaY) - (this.getBallRepresentation().getRadius() * this.getBallRepresentation().getRadius())<= 0.0001 ;
        }
        else
            return false;

    }

    @Override
    public void onCollision(Collidable object) {
        System.out.println(object);
        if(object instanceof Brick) {
            if (((Brick) object).position.getX() <= this.getPosition().getX() && (((Brick) object).position.getX() + Brick.getWidth()) >= this.getPosition().getX())
                this.getSpeed().bounce(true);
            else if (((Brick) object).position.getY() <= this.getPosition().getY() && (((Brick) object).position.getY() + Brick.getHeight()) >= this.getPosition().getY())
                this.getSpeed().bounce(false);
            else
                this.getSpeed().cornerBounce(this.position.getX() - Math.max(((Brick) object).position.getX(), Math.min(this.position.getX(), ((Brick) object).position.getX() + Brick.getWidth())),
                        this.position.getY() - Math.max(((Brick) object).position.getY(), Math.min(this.position.getY(), ((Brick) object).position.getY() + Brick.getHeight())));
        }
        if(object instanceof Platform){
            System.out.println("Collided with platform");
                if(((Platform) object).position.getX()<=this.getPosition().getX()&&(((Platform) object).position.getX()+ Platform.getWidth())>=this.getPosition().getX())
                    this.getSpeed().bounce(true);
                else if(((Platform) object).position.getY()<=this.getPosition().getY()&&(((Platform) object).position.getY()+ Platform.getHeight())>=this.getPosition().getY())
                    this.getSpeed().bounce(false);
                else
                    this.getSpeed().cornerBounce(this.position.getX() - Math.max(((Platform) object).position.getX(), Math.min(this.position.getX(), ((Platform) object).position.getX() + Platform.getWidth())),
                            this.position.getY() - Math.max(((Platform) object).position.getY(), Math.min(this.position.getY(), ((Platform) object).position.getY() + Platform.getHeight())));
        }
    }

    @Override
    public String toString() {
        return this.getPosition().toString() + " - x: "+ this.getSpeed().getHorizontalSpeed() + " y: "+this.getSpeed().getVerticalSpeed();
    }
}
