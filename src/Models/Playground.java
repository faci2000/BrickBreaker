package Models;

import Adds.Extra;
import Adds.GameElements;
import Adds.Normal;
import Patterns.Observer;
import Patterns.PieceOfInformation;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.reflections.Reflections;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Playground implements Observer {
    private Platform platform;
    private ArrayList<Ball> balls;
    private ArrayList<Brick> bricks;
    float width;
    float height;
    private Pane playgroundRepresentation;
    int bricksInRow;
    private boolean gameOver=false;
    private ArrayList<Ball> newBalls = new ArrayList<>();


    public Playground(float width,float height,int bricksInRow, int brickRows){
        this.width = width;
        this.height = height;
        this.setPlatform(new Platform(200,30));
        this.setBricks(new ArrayList<>());
        this.setBalls(new ArrayList<>());
        this.getBalls().add(new Ball());
        this.bricksInRow=bricksInRow;
        Brick.setHeight(50);
        Brick.setWidth((width-10*(bricksInRow +1))/bricksInRow);
        setPlaygroundRepresentation(new Pane(getBalls().get(0).getBallRepresentation()));
        getPlaygroundRepresentation().setPrefWidth(width);
        getPlaygroundRepresentation().setPrefHeight(height);
        getPlaygroundRepresentation().setBackground(new Background(new BackgroundFill(Color.web("#1c1202"), CornerRadii.EMPTY, Insets.EMPTY)));

        playgroundRepresentation.getChildren().add(getPlatform().getPlatformRepresentation());

        for(int i=0;i<brickRows;i++){
//                Brick brick = new Brick((1+j)*10+j* Brick.getWidth(),(i+1)*10+i* Brick.getHeight(),);
//                //System.out.println((j+1)*10+" "+(i+1)*10);
//                playgroundRepresentation.getChildren().add(brick.getBrickRepresentation());
//                getBricks().add(brick);
//                brick.addObserver(this);
                try{
                    drawNewLineOfBricks();
                }catch (Exception e){
                    System.out.println("There was a problem creating new line of bricks, check added extras."+ e);
                }
        }

        Playground.getExtras();
    }

    public void drawNewLineOfBricks() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for(Brick brick : getBricks())
            brick.moveBrickDown(10);
        for(int i=0;i<bricksInRow;i++){
            Class<? extends Extra> clazz = (Class<? extends Extra>) Class.forName(getBrickType());
            Extra type = clazz.getDeclaredConstructor().newInstance();
            Brick brick = new Brick((1+i)*10+i* Brick.getWidth(),10,type);
            //System.out.println((j+1)*10+" "+(i+1)*10);
            playgroundRepresentation.getChildren().add(brick.getBrickRepresentation());
            getBricks().add(brick);
            brick.addObserver(this);
        }
    }

    public int checkIfTouchBorder(Ball ball){
        if(width- ball.getPosition().getX()<= ball.getBallRepresentation().getRadius()|| ball.getPosition().getX()<= ball.getBallRepresentation().getRadius())
            return 1;
        else if(height-ball.getPosition().getY()<= ball.getBallRepresentation().getRadius() || ball.getPosition().getY()<= ball.getBallRepresentation().getRadius())
            return -1;

        return 0;
    }

    public void refresh(float timeDelta) {
        getPlatform().move();

        for(Ball ball : balls){
            if(ball.hasTouchedTheGround()){
                Text text =new Text(440,360,"Game Over");
                text.setFill(Color.WHITE);
                text.setFont(Font.font ("Verdana", 40));
                playgroundRepresentation.getChildren().add(text);
                this.setGameOver(true);
            }
            if(checkIfTouchBorder(ball)==-1){
                ball.getSpeed().bounce(true);
                System.out.println("vertical");
            }


            if(checkIfTouchBorder(ball)==1){
                ball.getSpeed().bounce(false);
                System.out.println("horizontal");
            }


            if(ball.collide(getPlatform()))
                ball.onCollision(getPlatform());

            for(int i = 0; i< getBricks().size(); i++){
                if(ball.collide(getBricks().get(i))){
                    System.out.println("Collided");
                    ball.onCollision(getBricks().get(i));
                    getBricks().get(i).onCollision(ball);
                }
            }
            ball.move(timeDelta);
        }

        if(!getNewBalls().isEmpty()){
            for(Ball ball : getNewBalls()){
                balls.add(ball);
            }
            setNewBalls(new ArrayList<>());
        }

        if(getBricks().size()<bricksInRow*2){
            try{
                drawNewLineOfBricks();
            }catch (Exception e){
                System.out.println("There was a problem creating new line of bricks, check added extras."+ e);
            }
        }

    }

    public static Set<Class<? extends Extra>>  getExtras(){

        System.out.println(GameElements.class.getPackage().getName());
        Reflections reflections = new Reflections("Adds");
        //Reflections reflections = new  Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("Adds")).setScanners(new SubTypesScanner()));
        Set<Class<? extends Extra>> classes = reflections.getSubTypesOf(Extra.class);
        System.out.println(classes);
        return classes;
    }

    public String getBrickType(){
        Set<Class<? extends Extra>> classes = getExtras();
        Random rand = new Random();
        int n = rand.nextInt(classes.size()*6);
        for(Class<? extends Extra> clazz : classes){
            if (n==0)
                return clazz.getName();
            n-=1;
        }
        return Normal.class.getName();
    }

    public Pane getPlaygroundRepresentation() {
        return playgroundRepresentation;
    }

    public void setPlaygroundRepresentation(Pane playgroundRepresentation) {
        this.playgroundRepresentation = playgroundRepresentation;
    }


    @Override
    public void update(PieceOfInformation pieceOfInformation) {
        this.getBricks().remove(pieceOfInformation.getBrick());
        this.playgroundRepresentation.getChildren().remove(pieceOfInformation.getBrick().getBrickRepresentation());
        pieceOfInformation.getBrick().type.getModifiedGameItems(new GameElements(balls,bricks,platform,this));
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public ArrayList<Ball> getNewBalls() {
        return newBalls;
    }

    public void setNewBalls(ArrayList<Ball> newBalls) {
        this.newBalls = newBalls;
    }
}
