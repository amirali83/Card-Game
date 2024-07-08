package View;

import Controller.GraphicController;
import example.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class loginMenuGraphic extends Application{
    public static void main(String[] args) {
        AppController.startGame();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.centerOnScreen();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane pane = fxmlLoader.load(getClass().getResource("/FXML/loginMenu.fxml"));
        ModuleLayer.Controller controller = fxmlLoader.getController();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Login Menu");

        GraphicController.setStage(stage);
        GraphicController.setController(controller);
        GraphicController.setPane(pane);
        GraphicController.setScene(scene);

        stage.show();
    }
}
