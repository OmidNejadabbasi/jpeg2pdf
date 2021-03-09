package net.omidn.pdfcreator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    // view controls
    Menu fileMenu;
    VBox rootContainer;
    MenuItem addFilesMenuItem;
    MenuItem saveAsMenuItem;
    MenuItem quiteMenuItem;
    Menu helpMenu;
    MenuItem aboutMenuItem;
    MenuBar appMenuBar;
    HBox mainAreaHBox;

    ListView<String> fileListView;

    VBox btnContainer;
    Button removeBtn;
    Button moveUpBtn;
    Button moveDownBtn;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        rootContainer = new VBox();
        // menu bar
        fileMenu = new Menu("File");
        addFilesMenuItem = new MenuItem("Add Files...");
        saveAsMenuItem = new MenuItem("Save As...");
        quiteMenuItem = new MenuItem("Quite");
        fileMenu.getItems().addAll(addFilesMenuItem, saveAsMenuItem, quiteMenuItem);

        // help menu
        helpMenu = new Menu("Help");
        aboutMenuItem = new MenuItem("About the App");
        helpMenu.getItems().add(aboutMenuItem);

        appMenuBar = new MenuBar(fileMenu, helpMenu);


        // mainArea
        mainAreaHBox = new HBox();
        VBox.setVgrow(mainAreaHBox, Priority.ALWAYS);

        //ListView
        fileListView = new ListView<>();
        fileListView.getItems().add("Omid");
        fileListView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        HBox.setHgrow(fileListView, Priority.ALWAYS);
        // adding list editing buttons
        btnContainer = new VBox();
        removeBtn = new Button("Remove");
        moveUpBtn = new Button("Move Up");
        moveDownBtn = new Button("Move Down");
        btnContainer.getChildren().addAll(removeBtn, moveUpBtn, moveDownBtn);

        removeBtn.setMaxWidth(Double.MAX_VALUE);
        moveDownBtn.setMaxWidth(Double.MAX_VALUE);
        moveUpBtn.setMaxWidth(Double.MAX_VALUE);
        // add margins to buttons
        btnContainer.setPadding(new Insets(15));

        // TODO turn local variables into fields :(

        mainAreaHBox.getChildren().add(fileListView);
        mainAreaHBox.getChildren().add(btnContainer);

        rootContainer.getChildren().addAll(appMenuBar, mainAreaHBox);
        scene = new Scene(rootContainer, 700, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Prototype");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
