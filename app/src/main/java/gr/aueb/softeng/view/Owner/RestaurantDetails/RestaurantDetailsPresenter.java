package gr.aueb.softeng.view.Owner.RestaurantDetails;


import gr.aueb.softeng.dao.RestaurantDAO;

import gr.aueb.softeng.domain.Restaurant;


public class RestaurantDetailsPresenter {
    private RestaurantDAO restaurantDAO;
    private Restaurant restaurant;
    private RestaurantDetailsView view;

    /**
     *
     * It initializes the restaurant DAO to allow us to save restaurants and retrieve them from our static list.
     * @param restaurantDAO Instance of the DAO
     */

    public RestaurantDetailsPresenter(RestaurantDAO restaurantDAO){
        this.restaurantDAO = restaurantDAO;

    }


    /**
     * It initializes the view from which we will use the methods of its interface.
     * @param v Instance of the view
     */
    public void setView(RestaurantDetailsView v)
    {
        this.view = v;
    }

    /**
     * It finds the object of the restaurant through its unique ID from the list of restaurants in the DAO.
     * @param restaurantId is the ID of the restaurant whose details we want to display.
     */
    public void setRestaurant(int restaurantId) {
        restaurant = restaurantDAO.find(restaurantId);
    }

    /**
     * It calls the methods of the view that display the details of the restaurant on the screen.
     */
    public void setDetails(){
        view.setRestName("Name: "+ restaurant.getRestaurantName());
        view.setRestId("Id: "+String.valueOf(restaurant.getId()));
        view.setRestTables("Total tables: "+ String.valueOf(restaurant.getTotalTables()));
        view.setRestAddressStreet("Address Street: "+restaurant.getAddress().getStreetName());
        view.setRestAddressNumber("Address Number: "+String.valueOf(restaurant.getAddress().getStreetNumber()));
        view.setRestAddressCity("Address City: "+restaurant.getAddress().getCity());
        view.setRestZip("Address ZC: "+String.valueOf(restaurant.getAddress().getZipCode()));
    }

    /**
     * It is called to export statistics when the "Export Statistics" button is clicked.s
     */
    public void onExtractStats(){
        view.extractStats();
    }

    /**
     *It is called when we want to return to the home screen
     */
    public void OnBack(){
        view.goBack();
    }

    /**
     * It is called to add a new chef when the "Add New Chef" button is clicked by the owner.
     */
    public void onAddChef(){view.addChef();}
    public RestaurantDetailsView getView(){
        return this.view;
    }
    public Restaurant getRestaurant(){
        return this.restaurant;
    }

}
