package net.omidn.pdfcreator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox rootContainer = new VBox();
        // menu bar
        Menu fileMenu = new Menu("File");
        MenuItem addFilesMenuItem = new MenuItem("Add Files...");
        MenuItem saveAsMenuItem = new MenuItem("Save As...");
        MenuItem quiteMenuItem = new MenuItem("Quite");
        fileMenu.getItems().addAll(addFilesMenuItem, saveAsMenuItem, quiteMenuItem);

        // help menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About the App");
        helpMenu.getItems().add(aboutMenuItem);

        MenuBar appMenuBar = new MenuBar(fileMenu, helpMenu);


        // mainArea
        HBox mainAreaHBox = new HBox();
        VBox.setVgrow(mainAreaHBox, Priority.ALWAYS);

        //ListView
        ListView<String> fileListView = new ListView<>();
        fileListView.getItems().add("Omid");
        fileListView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);



        mainAreaHBox.getChildren().add(fileListView);

        rootContainer.getChildren().addAll(appMenuBar, mainAreaHBox);
        Scene scene = new Scene(rootContainer, 700, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Prototype");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
