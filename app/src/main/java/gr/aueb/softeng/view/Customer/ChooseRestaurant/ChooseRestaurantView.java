package gr.aueb.softeng.view.Customer.ChooseRestaurant;

public interface ChooseRestaurantView {
    void goBack();


    /**
     * We hide the recyclerView and make visible an update message for the absence of restaurants.
     */
    void ShowNoRestaurants();

    /**
     * We display and set up the recyclerView and hide the message of absence of restaurants
     */
    void ShowRestaurants();
}
