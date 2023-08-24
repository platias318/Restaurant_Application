package gr.aueb.softeng.view.Customer.ChooseRestaurant;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import gr.aueb.softeng.dao.RestaurantDAO;
import gr.aueb.softeng.domain.Restaurant;

public class ChooseRestaurantPresenter {

    ChooseRestaurantView view;
    private RestaurantDAO restaurantDAO;

    private ArrayList<Restaurant> restaurants;
    /**
     * It initializes the Presenter so that we can add/find the restaurants.
     * @param restaurantDAO object where we contain the static list with the restaurants and we can add/receive objects
     */

    public ChooseRestaurantPresenter(RestaurantDAO restaurantDAO)
    {
        this.restaurantDAO = restaurantDAO;
        restaurants = new ArrayList<>();
    }

    /**
     *It sets our view object to use the methods of its interface
     * @param view an instance of a view
     */
    public void setView(ChooseRestaurantView view) {
        this.view = view;
    }
    /**
     *It returns the view object we created earlier
     * @return the Instance of the object
     */
    public ChooseRestaurantView getView() {
        return view;
    }
    /**
     * It fills the list with the restaurants of the specific order.
     */
    public void setRestaurantList() {
        restaurants = (ArrayList<Restaurant>) restaurantDAO.findAll();
    }
    /**
     *  We check if the list with the restaurants is empty to
     *  display them or show a message that there are no restaurants.
     */
    public void onChangeLayout() {
        if (restaurants.isEmpty()) {
            view.ShowNoRestaurants();
        }
        else {
            view.ShowRestaurants();
        }
    }
    /**
     * t calls the method of the view that takes us to the previous activity that called us.
     */
    public void onBack(){
        view.goBack();
    }
    /**
     * Returns the restaurant list
     * @return the list with the restaurants
     */
    public ArrayList<Restaurant> getRestaurantList() {
        return restaurants;
    }
}
