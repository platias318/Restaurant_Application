package gr.aueb.softeng.view.Owner.HomePage;

public interface OwnerHomePageView {
    /**
     * It calls the new activity that adds a new restaurant to the owner.
     */
    void AddRestaurant();
    /**
     * It's called when the owner's list of restaurants is empty, so that the notification message appears on the screen
     * and the empty recycler view is hidden.
     */
    void ShowNoRestaurants();
    /**
     *It's called when the owner's list of restaurants is NOT empty, and it displays the items
     * in the recycler view, while hiding the message that would be shown if the list was empty.
     */
    void ShowRestaurants();

    /**
     * Returns the previous activity that called us
     */
    void goBack();
}
