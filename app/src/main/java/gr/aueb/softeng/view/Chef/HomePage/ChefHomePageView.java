package gr.aueb.softeng.view.Chef.HomePage;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

public interface ChefHomePageView {
    /**
     * Called when we want to return to the previous Activity, which in our case is the login page (our activity calls this).
     */
    void goBack();
    /**
     *This method is called when the list of orders for the chef is empty, in order to display the message on the screen that the list is empty.
     */
     void ShowNoOrders();
    /**
     *This method is called when the list of orders is NOT empty, and the Recycler View with its items is displayed on the screen.
     *It also sets up the adapter and layout manager of the recycler view.
     */
     void ShowOrders();
}
