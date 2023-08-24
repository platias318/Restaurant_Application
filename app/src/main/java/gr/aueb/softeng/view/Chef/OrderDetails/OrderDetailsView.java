package gr.aueb.softeng.view.Chef.OrderDetails;


public interface OrderDetailsView {
    /**
     * Displays the ID of the selected order on the screen.
     * @param orderId the unique ID of the order that was clicked.
     */
    void setOrderId(String orderId);
    /**
     * Displays the status of the currently selected order on the screen.
     * @param state the status of the order.
     */
    void setOrderState(String state);
    /**
     * Displays the table number where the order was placed on the screen.
     * @param num the table number.
     */
    void setTableNumber(String num);
    /**
     * Displays the time and minute when the order was placed on the screen.
     * @param date the time and minute in concatenated String format.
     */
     void setDate(String date);
    /**
     * Called when we want to go back to the previous Activity, which in our case is the login page (this calls our activity).
     *
     */
    void goBack();
    /**
     * This method displays a success message on the screen and is called when the order is marked as Completed by the chef.
     * Additionally, it creates an onClick listener that triggers when the OK button is pressed on the screen,
     * returning us to the previous activity that called us.
     */
    void showOrderCompletedMessage();
    /**
     *It hides the SetCompletedButton that is meant for cases when the activity is called by the customer.
     */
    void hideCompletionButton();
    /**
     *It displays the SetCompletedButton, which is intended for cases involving the chef, allowing them to change the order status.
     */
    void showCompletedButton();
}
