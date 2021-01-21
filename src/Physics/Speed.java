package Physics;

public class Speed {
    private double verticalSpeed;
    private double horizontalSpeed;

    public Speed(double verticalSpeed, double horizontalSpeed){
        this.setVerticalSpeed(verticalSpeed);
        this.setHorizontalSpeed(horizontalSpeed);
    }

    public void bounce(boolean vertically){
        if( vertically)
            setVerticalSpeed(getVerticalSpeed() * -1.0);
        else
            setHorizontalSpeed(getHorizontalSpeed() * -1.0);
    }

    public void cornerBounce(double verticalDirectness,double horizontalDirectness){
        double impactAngle = Math.atan(Math.abs(getVerticalSpeed())/Math.abs(getHorizontalSpeed()));
        double centreAngle = Math.atan(Math.abs(horizontalDirectness)/Math.abs(verticalDirectness));
        double finalAngle = Math.PI/2-2*centreAngle+impactAngle;
        setHorizontalSpeed(Math.sqrt(2)*Math.sin(finalAngle));
        setVerticalSpeed(Math.sqrt(2)*Math.cos(finalAngle));
    }

    public void accelerate( double rate){
        setVerticalSpeed(getVerticalSpeed() * rate);
        setHorizontalSpeed(getHorizontalSpeed() * rate);
    }

    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    public void setVerticalSpeed(double verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public void setHorizontalSpeed(double horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }
}
