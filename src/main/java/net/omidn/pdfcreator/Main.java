package net.omidn.pdfcreator;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox rootContainer = new VBox();
        MenuBar appMenuBar = new MenuBar(new Menu("File"), new Menu("123"));

        rootContainer.getChildren().add(appMenuBar);
        Scene scene = new Scene(rootContainer, 700, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Prototype");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
