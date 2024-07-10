package Controller;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import View.*;

public class endGameMenuController {
    public Label winner;
    boolean bringUp = false;

    public void bringUp(MouseEvent mouseEvent) {
        if (!bringUp) {
            winner.setText(String.format("%s won", GraphicController.getWinner()));
            bringUp = true;
        }
    }

    public void playAgain(MouseEvent mouseEvent) {
        playMenuGraphic menu = new playMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void quit(MouseEvent mouseEvent) {
        mainMenuGraphic menu = new mainMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
