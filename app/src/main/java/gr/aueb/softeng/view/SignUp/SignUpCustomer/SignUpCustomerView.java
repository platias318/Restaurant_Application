package gr.aueb.softeng.view.SignUp.SignUpCustomer;

import java.util.HashMap;

public interface SignUpCustomerView {
    /**

     It creates a hash map in which we have the description, such as "username" or "phone number" of the customer, as the key, and the value of the key is obtained from the screen where the customer
     entered their registration details.
     @return this hash map with the screen's data.
     */
    HashMap<String,String> getDetails();
    /**
     *Displays an alert message with the title "title" and the message "message".
     * @param title The title of the message
     * @param message The content of the message
     */
    void showErrorMessage(String title, String message);
    /**
     * Called to return to the previous Activity.
     */
    void goBack();
    /**
     * Displays a success message when the customer successfully creates their account and returns
     * to the previous activity when the "OK" button is pressed.
     */
    void showAccountCreatedMessage();
}
