package Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.event.EventHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class kickAbilityMenuController implements Initializable {
    @FXML
    public Slider players;
    @FXML
    public Slider game;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //label.setText("This Label was initialized.");
        players.setValue(100);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(10),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                update();
                                //if (ActionEvent.ACTION.equals(KeyEvent.))
                            }
                        }));
                        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void update(){
        double t = (double) ((System.nanoTime() - GraphicController.getTime()) / ((double) 1000000000));
        while (t >= 2) {t -= 2;}
        //System.out.printf("%f\n", t);
        if (t <= 1) {game.setValue((double) (t * 100)); players.setValue((double) (100 - t * 100));}
        else {game.setValue((double) (100 - (t - 1) * 100)); players.setValue((double) ((t - 1) * 100));}

        GraphicController.setPowerAttack((int) Math.abs(game.getValue() - players.getValue()));
    }
}
