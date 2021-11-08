/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Kimari Guthre
 */
package dialogboxes;

import baseline.ListItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveDialogBox {
    private ObservableList<ListItem> observableTodoList;

    private File file;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField filePathDisplay;

    public void setTodoList(List<ListItem> todoList) {
        this.observableTodoList = (ObservableList<ListItem>) todoList;
    }

    @FXML
    private void chooseFile() {
        //Load the correct file into file through a fileChooser.
        var chooser = new FileChooser();
        chooser.setTitle("Save to..");
        file = chooser.showOpenDialog(new Stage());
        //Then, if it isn't null, load the string representation of that file into filePathDisplay for display.
        if (file != null) {
            filePathDisplay.setText(file.getAbsolutePath());
        } else {
            filePathDisplay.setText(null);
        }
    }

    @FXML
    private void closeDialogBox() {
        //Simply close the dialog box.
        var stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveListToFile() {
        //Check if file is not null.
        if (file != null) {
            //If file is not null, then try to save all the items in there after trying to create the file.
            try (var f = new FileOutputStream(file); var o = new ObjectOutputStream(f)) {
                o.writeObject(new ArrayList<>(observableTodoList));

            } catch (FileNotFoundException e) {
                var logger = Logger.getLogger("FileNotFoundLogger");
                logger.log(Level.WARNING, "File could not be created or opened, or is a directory!", e);

            } catch (IOException e) {
                var logger = Logger.getLogger("IOExceptionLogger");
                logger.log(Level.SEVERE, "Unforeseen IO error!", e);

            }
            //Finally, close the dialog box.
            closeDialogBox();
        }
    }
}
