package gr.aueb.softeng.view.Customer.HomePage;


import gr.aueb.softeng.view.View;

public interface CustomerHomepageView extends View{

    /**
     *It displays an alert message:
     * To ask the user if they are sure
     * that they want to cancel the order.
     */
    void ShowConfirmationMessage();

    /**
     * We are redirected to the active order's items preview page.
     */
    void redirectTopUp();

    /**
     * We retrieve the fragment of the tabLayout and display the current Order,
     * * hiding the rest of the graphical elements.
     */
    void showCurrentOrder();

    /**
     * We retrieve the fragment of the tabLayout and hide the frame that would contain the order details,
     *  but we display the other elements necessary for adding a new order (guidance message, + button).
    void showNoCurrentOrder();
     */
     void showNoCurrentOrder();

    /**
     * A pop-up window appears for selecting
     * * an integer corresponding to the
     * * table number for the order to be
     * * submitted.
     * */
    void showTableNumberPickerPopup();
}

