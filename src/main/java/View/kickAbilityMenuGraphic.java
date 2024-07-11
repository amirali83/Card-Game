package View;

import Controller.GraphicController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import static java.awt.event.KeyEvent.KEY_PRESSED;


public class kickAbilityMenuGraphic extends Application {
    int turn;
    public kickAbilityMenuGraphic(int i) {
        turn = i;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(getClass().getResource("/FXML/kickAbilityMenu.fxml"));
        ModuleLayer.Controller controller = fxmlLoader.getController();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.centerOnScreen();
        //stage.setTitle("Shop Menu");

        GraphicController.setStage(stage);
        GraphicController.setController(controller);
        GraphicController.setPane(pane);
        GraphicController.setScene(scene);
        GraphicController.setTime(System.nanoTime());
        //pane.getChildren().get(1).requestFocus();
        //stage.requestFocus();
        stage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode() == (KeyCode.SPACE) || keyEvent.getCode() == KeyCode.R) {
                    System.out.println("nfjnbjfnjfnvk");
                    stage.close();
                    GraphicController.getLives()[turn] -= (int) (20 - (GraphicController.getPowerAttack()) / 5);
                    twoPlayerGame.setLive(GraphicController.getLives());
                    return;
                }
            }
        });
    }
}
