package gr.aueb.softeng.view.Owner.AddChef;

import java.util.HashMap;

public interface AddChefView {
    /**
     *Creates a hash map in which the key represents the description, for example, whether it's the username or the chef's phone number,
     *and the value is the actual value of the key, obtained from the screen where the owner has entered the chef's details.
     *This map is used by the owner to search for the chef using their provided details.
     */
    HashMap<String,String> getChefDetails();
    /**
     * Displays an alert message with a specified title and message.
     * @param title The title of the message
     * @param message The content of the message
     */
    void showErrorMessage(String title, String message);
    /**
     *It gets called so that we return to the previous activity
     */
    void goBack();

    /**
     * Displays a success message when the owner
     * successfully adds the chef and returns to the previous activity when the "OK" button is pressed.
     */
    void showChefAddedMessage();
}
