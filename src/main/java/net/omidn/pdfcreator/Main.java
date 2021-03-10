package net.omidn.pdfcreator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

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
    ObservableList<String> currentFileInList = FXCollections.observableArrayList();

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
//        fileListView.getItems().add("Omid");
        fileListView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        fileListView.setItems(currentFileInList);
        fileListView.setCellFactory(lv -> new FilesListCell());
        // TODO remove this add code
        currentFileInList.add("/home/omid/Desktop/IMG_۲۰۲۱۰۲۲۶_۰۹۳۵۵۷.jpg");

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


        // ========== Event Handling ===========

        // add files action
        addFilesMenuItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.setTitle("Choose images to convert to PDF: ");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Image (*.jpg, *.png)", "*.jpg", "*.png");
            fileChooser.getExtensionFilters().add(extensionFilter);
            List<File> chosenFiles = fileChooser.showOpenMultipleDialog(primaryStage);
            if (chosenFiles != null) {
                currentFileInList.addAll(chosenFiles.stream().map(File::getAbsolutePath).collect(Collectors.toList()));
            }
        });

        // remove button action
        removeBtn.setOnAction(event -> {
            currentFileInList.removeAll(fileListView.getSelectionModel().getSelectedItems());
        });

        // move up button action
        moveUpBtn.setOnAction(event -> {
            ObservableList<Integer> items;
            if ((items = fileListView.getSelectionModel().getSelectedIndices()) != null) {

                int itemIndex = items.get(0);
                if (itemIndex != 0) {
                    String temp = currentFileInList.get(itemIndex - 1);
                    currentFileInList.set(itemIndex - 1, currentFileInList.get(itemIndex));
                    currentFileInList.set(itemIndex, temp);
                    fileListView.getSelectionModel().select(itemIndex - 1);
                }
            }
        });


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
