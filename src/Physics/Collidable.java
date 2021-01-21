package Physics;

public interface Collidable {
    public boolean collide(Collidable object);
    public void onCollision(Collidable object);
}
