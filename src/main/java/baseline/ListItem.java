/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Kimari Guthre
 */
package baseline;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class ListItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    private static final transient int MAX_DESC_LENGTH = 256;

    private String description;
    /*This time-based field is half of the reason the class exists, it can't be transient or dueDates would be lost on
    save.*/
    private LocalDate dueDate;

    public ListItem(String description, LocalDate dueDate) {
        /*Set this.description to description (which should be non-null), and this.dueDate to dueDate (which should be
        non-null). If description is larger than 256 characters, cut it down to 256 characters.*/
        if(description.length() <= MAX_DESC_LENGTH) {
            this.description = description;
        } else {
            this.description = description.substring(0, MAX_DESC_LENGTH);
        }
        this.dueDate = dueDate;
    }

    public ListItem(String description) {
        //If no dueDate is specified, set dueDate to null.
        this.description = description;
        this.dueDate = null;
    }

    public void setDescription(String description) {
        //If description isn't empty, and is less than 256 characters, change this.description to description.
        if(description.length() > 0) {
            if(description.length() <= MAX_DESC_LENGTH) {
                this.description = description;
            } else {
                /*If description isn't empty, but larger than 256 characters, change this.description to the first 256
                characters of description.*/
                this.description = description.substring(0, MAX_DESC_LENGTH);
            }
        }
    }

    public void setDueDate(LocalDate dueDate) {
        //Changes this.dueDate to dueDate.
        this.dueDate = dueDate;
    }

    public String getDescription() {
        //Returns description.
        return description;
    }

    public LocalDate getDueDate() {
        //Returns dueDate.
        return dueDate;
    }

    public String toString() {
        //Returns the description, used to more easily format the choiceBox in DeleteItemDialogBox.
        return getDescription();
    }
}
