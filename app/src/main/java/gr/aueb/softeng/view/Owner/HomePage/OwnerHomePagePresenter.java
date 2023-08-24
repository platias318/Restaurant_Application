package gr.aueb.softeng.view.Owner.HomePage;



import java.util.ArrayList;

import gr.aueb.softeng.dao.OwnerDAO;
import gr.aueb.softeng.dao.RestaurantDAO;
import gr.aueb.softeng.domain.Owner;
import gr.aueb.softeng.domain.Restaurant;

public class OwnerHomePagePresenter {

    OwnerHomePageView view;

    private ArrayList<Restaurant> restaurantList= new ArrayList<>();
    private OwnerDAO ownerDAO;
    private RestaurantDAO restaurantDAO;

    private Owner owner;

    /**
     * It initializes the Presenter so that we can add/find the owner and their restaurants.
     * @param ownerDAO An object containing the static list of Owners, allowing addition/retrieval of owners.
     * @param restaurantDAO An object containing the static list of restaurants, allowing addition/retrieval of restaurants for a specific owner.
     */
    public OwnerHomePagePresenter(OwnerDAO ownerDAO, RestaurantDAO restaurantDAO){
        this.ownerDAO=ownerDAO;
        this.restaurantDAO=restaurantDAO;
    }
    /**
     It populates the list of restaurants with the restaurants owned by the specific owner who is currently logged in.
     */
    public void setRestaurantList(){
        restaurantList = owner.getRestaurants();
    }

    /**
     * It sets the view object to enable us to use the methods of its interface.
     * @param view An instance of the view.
     */
    public void setView(OwnerHomePageView view){
        this.view=view;
    }

    /**
     * @return It returns the owner DAO with the owners that were passed as a parameter from the constructor of the class above.
     */
    public OwnerDAO getOwnerDAO(){
        return this.ownerDAO;
    }

    /**
     * @return It returns the owner DAO with the owners that were passed as a parameter from the constructor of the class above.
     */
    public RestaurantDAO getRestaurantDAO(){
        return this.restaurantDAO;
    }

    /**
     * @return It returns the restaurants of a specific owner based on their UserId.
     */
    public ArrayList<Restaurant> getRestaurants() {
        return ownerDAO.findRestaurants(owner.getUserId());
    }

    /**
     * It initializes the owner based on the object retrieved from the DAO and the unique Id that is passed to it.
     * @param ownerId is the User Id of a specific owner.
     */
    public void setOwner(int ownerId) {
        owner = ownerDAO.find(ownerId);
    }

    /**
     * It's called when the "Add New Restaurant" button is pressed by the owner.
     * It calls the method of the view that takes us to the Activity where we add a new restaurant.
     */

    public void onAddRestaurant(){
        view.AddRestaurant();
    }

    /**
     *
     * @return It returns the lsit containing the restaurants of a specific ownere
     */
    public ArrayList<Restaurant> getRestaurantList(){
        return this.restaurantList;
    }

    /**
     * It calls the method of the view that changes the appearance of the screen based on whether the list of
     * restaurants owned by the owner is empty or not.
     */
    public void onChangeLayout() {
        if (restaurantList.isEmpty()) {
            view.ShowNoRestaurants();
        }
        else {
            view.ShowRestaurants();
        }
    }

    /**
     * It calls the method of the view that takes us back to the previous activity that called us.
     */
    public void onBack(){view.goBack();}

    /**
     * It returns the owner object that was set earlier.
     * @return Returns the instance of the object.
     */
    public Owner getOwner(){
        return this.owner;
    }

    /**
     * It returns the view that was created earlier.
     * @return The instance of the view.
     */
    public OwnerHomePageView getView(){
        return this.view;
    }
}
