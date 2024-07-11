package Controller;

import View.twoPlayerGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class selectCharacterMenuController implements Initializable {
    @FXML
    public Rectangle ch1;
    @FXML
    public Rectangle ch2;
    @FXML
    public Rectangle ch3;
    @FXML
    public Rectangle ch4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ch1.setFill(new ImagePattern(new Image(selectCharacterMenuController.class.getResource("/CardImage/ch1.png").toExternalForm())));
        ch2.setFill(new ImagePattern(new Image(selectCharacterMenuController.class.getResource("/CardImage/ch2.png").toExternalForm())));
        ch3.setFill(new ImagePattern(new Image(selectCharacterMenuController.class.getResource("/CardImage/ch3.png").toExternalForm())));
        ch4.setFill(new ImagePattern(new Image(selectCharacterMenuController.class.getResource("/CardImage/ch4.png").toExternalForm())));
    }

    public void reset() {
        ch1.setOpacity(0.5);
        ch2.setOpacity(0.5);
        ch3.setOpacity(0.5);
        ch4.setOpacity(0.5);
    }

    public void setFocus1(MouseEvent focus1) {
        reset();
        ch1.setOpacity(1);
    }

    public void setFocus2(MouseEvent mouseEvent) {
        reset();
        ch2.setOpacity(1);
    }

    public void setFocus3(MouseEvent mouseEvent) {
        reset();
        ch3.setOpacity(1);
    }

    public void setFocus4(MouseEvent mouseEvent) {
        reset();
        ch4.setOpacity(1);
    }

    public void selectCh1(MouseEvent mouseEvent) {
    }

    public void selectCh2(MouseEvent mouseEvent) {
    }

    public void selectCh3(MouseEvent mouseEvent) {
    }

    public void selectCh4(MouseEvent mouseEvent) {
    }
}
