package net.omidn.pdfcreator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.text.CharacterIterator;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Date;

public class FilesListCell extends ListCell<String> {

    private HBox container;
    private ImageView thumbnail;
    private VBox detailsContainer;
    private Label fileNameLabel;
    private Label metadataLabel;


    public FilesListCell() {
        super();
        container = new HBox();
        container.setPadding(new Insets(3, 0, 3, 7));
        container.setSpacing(7.0);
        container.setAlignment(Pos.CENTER_LEFT);
        // imageview setup
        thumbnail = new ImageView();

        // details container
        detailsContainer = new VBox();
        detailsContainer.setAlignment(Pos.CENTER_LEFT);
        fileNameLabel = new Label();
        metadataLabel = new Label();

        fileNameLabel.setId("file-name-label");
        metadataLabel.setId("meta-label");

        detailsContainer.getChildren().addAll(fileNameLabel, metadataLabel);

        container.getChildren().addAll(thumbnail, detailsContainer);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            File file = new File(item);
            Image thumbnailImg = new Image("file:///" + item, 80, 60, false, false);
            thumbnail.setImage(thumbnailImg);
            fileNameLabel.setText("File: " + file.getName());

            metadataLabel.setText("Size: " + humanReadableByteCountBin(file.length()) + "     Last Modified: " + epochAsString(file.lastModified()));

            container.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    Stage imagePreviewStage = new Stage();
                    Image img = new Image("file:///" + item);
                    ImageView imageView = new ImageView(img);
                    ScrollPane pane = new ScrollPane(imageView);
                    Scene scene = new Scene(pane);
                    imagePreviewStage.setHeight(700);
                    imagePreviewStage.setWidth(1000);
                    imagePreviewStage.setScene(scene);
                    imagePreviewStage.setTitle("File: " + item);
                    imagePreviewStage.show();
                }
            });
            setGraphic(container);
        }


    }

    /**
     * Convert the number of byte into human readable format like: 1.2kB, 3.4MB, ...
     *
     * @param bytes number of bytes
     * @return bytes in human readable format
     */
    public static String humanReadableByteCountBin(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %ciB", value / 1024.0, ci.current());
    }

    /**
     * Returns a more readable form for the epoch seconds passed.
     *
     * @param epochTime the UNIX-time that should be formatted
     * @return a string a in the form  <code>yyyy/MM/dd, HH:mm</code>
     */
    public static String epochAsString(long epochTime) {
        Date date = new Date(epochTime);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd, HH:mm");
        return dateFormat.format(date);
    }

}
