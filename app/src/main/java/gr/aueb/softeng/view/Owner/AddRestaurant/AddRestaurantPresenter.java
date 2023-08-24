package gr.aueb.softeng.view.Owner.AddRestaurant;

import java.util.HashMap;
import java.util.Map;

import gr.aueb.softeng.dao.OwnerDAO;
import gr.aueb.softeng.dao.RestaurantDAO;
import gr.aueb.softeng.domain.Address;

import gr.aueb.softeng.domain.Owner;
import gr.aueb.softeng.domain.Restaurant;


public class AddRestaurantPresenter {
    private RestaurantDAO restaurantDAO;
    private OwnerDAO ownerDAO;
    AddRestaurantView view;
    private int ownerId;
    private Owner owner;
    /**
     * It initializes the owner dao and the restaurant dao in order to be able to store and retrieve from our
     * statistics list the restaurants and their owners.
     * @param ownerDAO
     * @param restaurantDAO
     */
    public AddRestaurantPresenter(OwnerDAO ownerDAO, RestaurantDAO restaurantDAO) {
        this.ownerDAO= ownerDAO;
        this.restaurantDAO = restaurantDAO;
    }

    /**
     * It retrieves the owner through the unique id from the static list contained in
     * the owner dao.
     * @param id the unique id of the owner we want to add the restaurant to.
     */
    public void setOwner(int id){
        owner= ownerDAO.find(id);
    }

    /**
     * It initializes the view from which we will use the methods of its interface.
     * @param view the view instance
     */
    public void setView(AddRestaurantView view) {
        this.view = view;
    }

    /**
     * This method is called when the restaurant creation button is pressed,
     * after all the details have been entered.
     * We perform checks on each field to determine if it's acceptable, and if it's not, a notification message is displayed on the owner's screen
     * for necessary changes.
     * If the details are correct, an appropriate message is displayed, the new restaurant is created, and it's stored in the restaurant dao
     * and in the owner's list of restaurants.
     */
    public void onCreateRestaurant() {
        boolean isEmpty = false;
        HashMap<String, String> details = view.getRestDetails();

        for (Map.Entry<String, String> set : details.entrySet()) {
            if (set.getValue().isEmpty() || set.getValue() == null) {
                isEmpty = true;
                break;
            }
        }
        if (isEmpty) {
            view.showErrorMessage("Error!", "Please complete all the fields.!.");
        } else if (details.get("name").length() < 4 || details.get("name").length() > 15) {
            view.showErrorMessage("Error!", "Enter 4 to 15 characters in the Restaurant Name");
        } else if (details.get("telephone").length() < 10) {
            view.showErrorMessage("Error!", "Please provide a valid phone number.");
        } else if (details.get("streetName").length() < 3) {
            view.showErrorMessage("Error!", "Please enter 4 or more characters in the Street Name");
        } else if (details.get("streetNumber").equals("0") ) {
            view.showErrorMessage("Error!", "Please enter 1 digit or more in the Street Number.");
        } else if (details.get("zc").length() < 2) {
            view.showErrorMessage("Error!", "Please enter 2 digits or more in the Postal Code (ZIP Code).");
        }else if (details.get("total_tables").equals("0")){
            view.showErrorMessage("Error!", "You must have at least 1 table or more to create the restaurant.");
        } else if (restaurantDAO.find(details.get("name")) != null) {
            view.showErrorMessage("Error!", "There is already a restaurant with the same name. Please provide new details!");
        } else {
            Restaurant restaurant= new Restaurant(restaurantDAO.nextId(),details.get("name"),details.get("telephone"),Integer.parseInt(details.get("total_tables")),new Address(Integer.parseInt(details.get("streetNumber")),details.get("streetName"),Integer.parseInt(details.get("zc")),details.get("city")));

            restaurantDAO.save(restaurant);
            owner.addRestaurant(restaurant);
            view.showRestaurantAddedMessage();
        }
    }
     /**
     * It calls the view method that takes us to the previous activity that called us
     */
    public void onBack(){
        view.goBack();
    }

    /**
     * It returns the view that we created above
     * @return the instance of the object
     */
    public AddRestaurantView getView(){
        return this.view;
    }

    /**
     * It returns the owner object that was setted above
     * @return The object instance
     */
    public Owner getOwner(){
        return this.owner;
    }
}
