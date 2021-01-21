package Physics;

public class Position {
    private double x=0;
    private double y=0;

    public Position(double x, double y){
        this.setX(x);
        this.setY(y);
    }

    public void move(Speed speed,double timeDelta){
        this.setX(this.getX() + speed.getHorizontalSpeed() *timeDelta);
        this.setY(this.getY() + speed.getVerticalSpeed() *timeDelta);
    }

    @Override
    public String toString() {
        return "("+this.x+","+this.y+")";
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
