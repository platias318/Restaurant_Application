package gr.aueb.softeng.view.Login;

import android.app.AlertDialog;
import android.content.DialogInterface;

import gr.aueb.softeng.dao.ChefDAO;
import gr.aueb.softeng.dao.CustomerDAO;
import gr.aueb.softeng.dao.OwnerDAO;
import gr.aueb.softeng.dao.UserDAO;
import gr.aueb.softeng.view.View;

public interface LoginView extends View {
    /**
     * This method extracts the name that the user has typed in the Username field
     */
    String ExtractUsername();
    /**
     *This method ectracts the password that the user has typed in the Password field
     */
    String ExtractPassword();
    /**
     * It displays a success message when the customer successfully logs into their account
     * and is directed to the Home Page activity when the "OK" button is pressed.
     */
    void showCustomerFoundMessage(int id);
    /**
     * It displays a success message when the chef successfully logs into their account
     * and is directed to the Home Page activity when the "OK" button is pressed.
     */
    void showChefFoundMessage(int id);
    /**
     * It displays a success message when the owner successfully logs into their account
     * and is directed to the Home Page activity when the "OK" button is pressed.
     */
    void showOwnerFoundMessage(int id);
    /**
     * This method gets called when the customer register button gets pressed
     */
    void signup();
    /**
     *This method get called when the chef register button gets pressed
     */
    void signupPersonel();
    /**
     *This method gets called when the owner register button gets pressed
     */
    void signupOwner();
    /**
     * It displays an alert message with the title "title" and the message "message."
     * @param title The title of the message
     * @param message The content of the message
     */
    void showErrorMessage(String title, String message);
}
