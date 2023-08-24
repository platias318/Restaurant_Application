package gr.aueb.softeng.view.SignUp.SignUpPersonel;

import java.util.HashMap;

public interface SignUpPersonelView {
    /**
     * It creates a hash map in which we have the description, for example whether it's a username or the chef's phone number, as the key,
     * and the value of the key is obtained from the screen where the chef has entered the registration details.
     * @return We return this Hash Map with the screen's data.
     */
    HashMap<String,String> getChefDetails();
    /**
     * It displays an alert message with a title and a message.
     * @param title The title of the message.
     * @param message The content of the message.
     */
    void showErrorMessage(String title, String message);
    /**
     * It is called in order to return to the previous Activity
     */
    void goBack();
    /**
     * It displays a success message when the chef successfully creates their account and returns to the previous activity when the OK button is pressed
     */
    void showAccountCreatedMessage();
}
