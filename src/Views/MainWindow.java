package Views;

import Models.Playground;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.Date;


public class MainWindow extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Playground playground = new Playground(1080,720,8,3);
        Date time = new Date();
        final long[] latestUpdate = {time.getTime()};
        AnimationTimer frames = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if(playground.isGameOver())
                    this.stop();
                playground.refresh(5);
                latestUpdate[0] = time.getTime();

                //System.out.println(playground.getBall().getPosition());
            }
        };


//        Pane pane = new Pane();
//        pane.setBackground(new Background(new BackgroundFill(Color.web("#1c1202"), CornerRadii.EMPTY, Insets.EMPTY)));
//        pane.setPrefWidth(1080);
//        pane.setPrefHeight(720);
//        pane.getChildren().add(ball.getBallRepresentation());

        Scene scene = new Scene(playground.getPlaygroundRepresentation());
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                playground.getPlatform().setLeftPressed(true);
                System.out.println("Left pressed");
            }
            if (e.getCode() == KeyCode.D) {
                playground.getPlatform().setRightPressed(true);
                System.out.println("RightPressed");
            }
        });

        scene.setOnKeyReleased(e->{
            if (e.getCode() == KeyCode.A) {
                playground.getPlatform().setLeftPressed(false);
            }
            if (e.getCode() == KeyCode.D) {
                playground.getPlatform().setRightPressed(false);
            }
        });
        stage.setScene(scene);
        stage.show();
        frames.start();
        //timeline.play();




    }
}
