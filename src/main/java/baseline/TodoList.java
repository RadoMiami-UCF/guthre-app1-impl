/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Kimari Guthre
 */
package baseline;

import java.util.ArrayList;
import java.util.Calendar;

public class TodoList {

    private ArrayList<ListItem> listOfItems;

    void addItem(String description, Calendar dueDate) {
        //Adds an item with the required parameters to the end of the list.
    }

    void deleteItem(int index) {
        //Removes the item at index from the list.
    }

    void setDescriptionForItem(int itemIndex, String description) {
        //Calls listOfItems.get(itemIndex).setDescription(description).
    }

    void setDueDateForItem(int itemIndex, Calendar dueDate) {
        //Calls listOfItems.get(itemIndex).setDueDate(dueDate).
    }

    String getDescriptionOfItem(int itemIndex) {
        //Returns listOfItems.get(itemIndex).getDescription().
        return "";
    }

    Calendar getDueDateOfItem(int itemIndex) {
        //Return listOfItems.get(itemIndex).getDueDate().
        return new Calendar.Builder().setDate(1, 1, 1).build();
    }
}
