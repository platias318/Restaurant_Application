package gr.aueb.softeng.view.Owner.AddRestaurant;

import java.util.HashMap;

public interface AddRestaurantView {    /**
 * It creates a hash map in which we have the description as the key, for example, if it's "rname" or the restaurant's phone number,
 * and the value is the value of the key, which we take from the screen that the owner has entered
 * the restaurant's details to be added.
 */
    HashMap<String,String>  getRestDetails();
    /**
     * Displays an alert-type message with
     * title as the title and message as the message.
     * @param title The title of the message
     * @param message The content of the message
     */
    void showErrorMessage(String title, String message);
    /**
     * It gets called so that we return to the previous activity that called us
     */
    void goBack();

    /**
     * It displays a success message when the owner succesfully adds his new restaurant to the system
     * and returns to the previous activity when the OK button gets pressed
     */
    void showRestaurantAddedMessage();
}
