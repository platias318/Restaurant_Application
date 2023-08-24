package gr.aueb.softeng.view.SignUp.SignUpOwner;

import java.util.HashMap;

public interface SignUpOwnerView {
    /**
     * It creates a hash map in which we use the description (e.g., "username" or "telephone" of the owner) as the key and the value of the key is obtained
     * from the screen entered by the owner's registration details.
     * @return We return this hash map with the data from the screen.
     */
    HashMap<String,String> getOwnerDetails();
    /**
     * Displays an alert message with
     * title 'title' and message 'message'.
     * @param title The title of the message
     * @param message The content of the message
     */
    void showErrorMessage(String title, String message);
    /**
     * Called to return to the previous Activity.
     */
    void goBack();
    /**
     * Displays a success message when the owner successfully creates their account
     * and returns to the previous activity when the OK button is pressed.
     */
    void showAccountCreatedMessage();
}
