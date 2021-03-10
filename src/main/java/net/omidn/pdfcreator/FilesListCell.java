package net.omidn.pdfcreator;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.File;

public class FilesListCell  extends ListCell<String>{

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if(empty){
            setGraphic(null);
        }


    }
}
