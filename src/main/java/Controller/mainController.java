package Controller;

import View.ProfileMenuGraphic;
import View.loginMenuGraphic;
import example.AppController;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import Module.*;
import View.*;

public class mainController {
    public Label HP;
    public Label XP;
    public Label Coins;
    public Label Level;
    User user = GraphicController.getUser();

    public void startGame(MouseEvent mouseEvent) {

    }

    public void GameHistory(MouseEvent mouseEvent) {
        GameHistoryMenuGraphic menu = new GameHistoryMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Shop(MouseEvent mouseEvent) {
        shopMenuGraphic menu = new shopMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Profile(MouseEvent mouseEvent) {
        ProfileMenuGraphic menu = new ProfileMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Setting(MouseEvent mouseEvent) {
    }

    public void Logout(MouseEvent mouseEvent) {
        AppController.run("log out");
        loginMenuGraphic menu = new loginMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(MouseEvent mouseEvent) {
        HP.setText(Integer.toString(user.getHP()));
        XP.setText(Integer.toString(user.getXP()));
        Coins.setText(Integer.toString(user.getCoins()));
        Level.setText(Integer.toString(user.getLevel()));
    }
}
