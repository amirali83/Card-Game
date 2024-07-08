package Controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Module.*;

import java.util.ArrayList;

public class GraphicController {
    private static Stage stage;
    private static Scene scene;
    private static Pane pane;
    private static ModuleLayer.Controller controller;
    private static String command;
    private static User user = null;
    private static User opponent = null;
    private static ArrayList<Card> allCards = new ArrayList<>();

    public static void setStage(Stage stage) {GraphicController.stage = stage;}
    public static void setScene(Scene scene) {GraphicController.scene = scene;}
    public static void setPane(Pane pane) {GraphicController.pane = pane;}
    public static void setController(ModuleLayer.Controller controller) {GraphicController.controller = controller;}
    public static void setCommand(String command) {GraphicController.command = command;}
    public static void setUser(User user) {GraphicController.user = user;}
    public static void setOpponent(User user) {GraphicController.opponent = user;}
    public static void setAllCards(ArrayList<Card> allCards) {GraphicController.allCards.addAll(allCards);}

    public static Stage getStage() {return stage;}
    public static Scene getScene() {return scene;}
    public static Pane getPane() {return pane;}
    public static ModuleLayer.Controller getController() {return controller;}
    public static String getCommand() {return command;}
    public static User getUser() {return user;}
    public static User getOpponent() {return opponent;}
    public static ArrayList<Card> getAllCards() {return allCards;}
}
