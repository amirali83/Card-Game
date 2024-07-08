package Controller;

import View.mainMenuGraphic;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class shopController {

    public Rectangle card1;
    public Rectangle card3;
    public Rectangle card2;
    public Rectangle card4;
    public Rectangle card5;
    public Rectangle card6;
    int cardSelected;
    int pagenum = 1;

    public void pagebef(MouseEvent mouseEvent) {
        if (pagenum != 1) pagenum--;
        card1.setOpacity(1);
        card2.setOpacity(1);
        card3.setOpacity(1);
        card4.setOpacity(1);
        card5.setOpacity(1);
        card6.setOpacity(1);
        cardSelected = -1;
        update();
    }

    public void pageaf(MouseEvent mouseEvent) {
        if (!(pagenum * 6 > GraphicController.getAllCards().size())) pagenum++;
        card1.setOpacity(1);
        card2.setOpacity(1);
        card3.setOpacity(1);
        card4.setOpacity(1);
        card5.setOpacity(1);
        card6.setOpacity(1);
        cardSelected = -1;
        update();
    }

    public void back(MouseEvent mouseEvent) {
        mainMenuGraphic menu = new mainMenuGraphic();
        try {
            menu.start(GraphicController.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void buy(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (cardSelected == -1) {
            alert.setHeaderText("Select a cart first");
            alert.showAndWait();
            return;
        }
    }

    public void update() {
        card1.setVisible(false);
        card2.setVisible(false);
        card3.setVisible(false);
        card4.setVisible(false);
        card5.setVisible(false);
        card6.setVisible(false);
        for (int i = (pagenum - 1 ) * 6; i < Math.min(GraphicController.getAllCards().size(), pagenum * 6); i++) {
            if (i % 6 == 0) {
                card1.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/ghost1.png").toExternalForm())));
                card1.setVisible(true);
            }
            else if (i % 6 == 1) {
                card2.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/ghost1.png").toExternalForm())));
                card2.setVisible(true);
            }
            else if (i % 6 == 2) {
                card3.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/ghost1.png").toExternalForm())));
                card3.setVisible(true);
            }
            else if (i % 6 == 3) {
                card4.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/ghost1.png").toExternalForm())));
                card4.setVisible(true);
            }
            else if (i % 6 == 4) {
                card5.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/ghost1.png").toExternalForm())));
                card5.setVisible(true);
            }
            else if (i % 6 == 5) {
                card6.setFill(new ImagePattern(new Image(getClass().getResource("/CardImage/ghost1.png").toExternalForm())));
                card6.setVisible(true);
            }
        }
    }

    public void selectCard1(MouseEvent mouseEvent) {
        cardSelected = 1;
        card1.setOpacity(0.5);

        card2.setOpacity(1);
        card3.setOpacity(1);
        card4.setOpacity(1);
        card5.setOpacity(1);
        card6.setOpacity(1);
    }

    public void selectCard2(MouseEvent mouseEvent) {
        cardSelected = 2;
        card2.setOpacity(0.5);

        card1.setOpacity(1);
        card3.setOpacity(1);
        card4.setOpacity(1);
        card5.setOpacity(1);
        card6.setOpacity(1);
    }

    public void selectCard3(MouseEvent mouseEvent) {
        cardSelected = 3;
        card3.setOpacity(0.5);

        card2.setOpacity(1);
        card1.setOpacity(1);
        card4.setOpacity(1);
        card5.setOpacity(1);
        card6.setOpacity(1);
    }

    public void selectCard4(MouseEvent mouseEvent) {
        cardSelected = 4;
        card4.setOpacity(0.5);

        card2.setOpacity(1);
        card3.setOpacity(1);
        card1.setOpacity(1);
        card5.setOpacity(1);
        card6.setOpacity(1);
    }

    public void selectCard5(MouseEvent mouseEvent) {
        cardSelected = 5;
        card5.setOpacity(0.5);

        card2.setOpacity(1);
        card3.setOpacity(1);
        card4.setOpacity(1);
        card1.setOpacity(1);
        card6.setOpacity(1);
    }

    public void selectCard6(MouseEvent mouseEvent) {
        cardSelected = 6;
        card6.setOpacity(0.5);

        card2.setOpacity(1);
        card3.setOpacity(1);
        card4.setOpacity(1);
        card5.setOpacity(1);
        card1.setOpacity(1);
    }
}
