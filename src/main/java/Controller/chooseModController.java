package Controller;

import javafx.scene.input.MouseEvent;
import View.*;

public class chooseModController {
    public void gamblersMode(MouseEvent mouseEvent) {

    }

    public void dualMode(MouseEvent mouseEvent) {
        secondPlayerLoginGraphic menu = new secondPlayerLoginGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
