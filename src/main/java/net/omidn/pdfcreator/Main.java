package net.omidn.pdfcreator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        HBox.setHgrow(fileListView, Priority.ALWAYS);
        // adding list editing buttons
        VBox btnContainer = new VBox();
        Button removeBtn = new Button("Remove");
        Button moveUpBtn = new Button("Move Up");
        Button moveDownBtn = new Button("Move Down");
        btnContainer.getChildren().addAll(removeBtn, moveUpBtn, moveDownBtn);

        removeBtn.setMaxWidth(Double.MAX_VALUE);
        moveDownBtn.setMaxWidth(Double.MAX_VALUE);
        moveUpBtn.setMaxWidth(Double.MAX_VALUE);
        // TODO add margins to buttons


        mainAreaHBox.getChildren().add(fileListView);
        mainAreaHBox.getChildren().add(btnContainer);

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
