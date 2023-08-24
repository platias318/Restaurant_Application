package gr.aueb.softeng.view.Customer.PlaceOrder;

import java.util.ArrayList;

import gr.aueb.softeng.domain.OrderLine;

public interface PlaceOrderView {

    /**
     * We navigate to the order contents preview page,
     * waiting for it to return with a potentially modified list of orderlines.
     */
    void redirectToCart(ArrayList<OrderLine> orderLines);
    /**
     * We hide the recyclerView and the "Complete Order" button,
     * while displaying the message of no available dishes.
     */
    void showEmptyList();
    /**
     * We display and set up the recyclerView and the "Complete Order" button,
     * while hiding the message that there are no available dishes.
     */
    void showDishList();

    /**
     * We display an Alert dialog window to inform the user that the order has failed because
     * they do not have the required balance. Then, they are redirected to the Homepage.
     */
    void insufficientFundsMessage();


    /**
     * We display an Alert dialog window to inform the user about the total cost of the order.
     * Then, the user either chooses to complete it by selecting "Yes" and returning to the Homepage,
     * or selects "No" and closes the window.
     */
    void ShowConfirmationMessage(ConfirmationListener confirmationListener);

    void goBack();

}
