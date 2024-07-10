package Controller;

import com.sun.tools.javac.Main;
import javafx.scene.input.MouseEvent;
import View.*;

public class levelUpMenuController {
    public void mainMenu(MouseEvent mouseEvent) {
        mainMenuGraphic menu = new mainMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
