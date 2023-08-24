package gr.aueb.softeng.view.Owner.AddChef;

import java.util.HashMap;
import java.util.Map;

import gr.aueb.softeng.dao.ChefDAO;
import gr.aueb.softeng.dao.RestaurantDAO;
import gr.aueb.softeng.domain.Restaurant;

public class AddChefPresenter {
    private ChefDAO chefDAO;
    private RestaurantDAO restDAO;
    AddChefView view;
    private Restaurant restaurant;
    /**
     * It initializes the chef DAO and restaurant DAO to allow us to store and retrieve restaurants and chefs from our static lists.
     * @param chefDAO
     * @param restDAO
     */
    public AddChefPresenter(RestaurantDAO restDAO, ChefDAO chefDAO)
    {
        this.chefDAO = chefDAO;
        this.restDAO = restDAO;
    }
    /**
     * It initializes the view that we are going to use its interface methods
     * @param v Instance του view
     */
    public void setView(AddChefView v)
    {
        this.view = v;
    }
    /**
     * It finds the restaurant we want to add the chef to using the unique ID. It searches for the restaurant in the static list contained within the restaurant DAO.
     * @param id The unique ID of the restaurant where we want to add the chef.
      */

    public void setRestaurant(int id){
        this.restaurant = restDAO.find(id);
}
    /**
     *This method is called when the chef insertion button is pressed in the restaurant's interface,
     aa*fter all the necessary chef information has been entered.
     *We perform checks for each field to determine whether it is acceptable. If not, we display a notification message on the owner's screen,
     *prompting them to make the necessary changes.
     *Additionally, we validate each field against the chef's provided registration details.
     *If any detail doesn't match, we show an error message on the screen.
     *If all details are correct, an appropriate success message is displayed, and the chef is added to the restaurant.
     */
    public void onAddChefAccount(){
        boolean isEmpty=false;
        HashMap<String,String> details = view.getChefDetails();

        for(Map.Entry<String, String> set: details.entrySet()){
            if(set.getValue().isEmpty() || set.getValue()==null){
                isEmpty=true;
                break;
            }
        }
        if(isEmpty){
            view.showErrorMessage("Error!", "Please complete all the fields!.");
        }else if(details.get("username").length() < 4 || details.get("username").length() > 15) {
            view.showErrorMessage("Error!", "Enter 4 to 15 characters in the Username.");
        }else if (details.get("telephone").length() < 10) {
            view.showErrorMessage("Error!", "Please provide a valid phone number.");
        }else if (details.get("iban").length() < 5) { // anti gia 5 na valw akrivws posa einai pragmatika
            view.showErrorMessage("Error!", "Please enter a valid IBAN.");
        }else if (details.get("tin").length() < 3){
            view.showErrorMessage("Error!", "Please enter a valid TIN (Tax Identification Number).");
        }else if (chefDAO.find(details.get("username"))==null) { // the chef has logged in
            view.showErrorMessage("Error!", "The information you provided does not correspond to any chef in the application , \n Try Again!");
        }else if (chefDAO.find(details.get("username"))!=null &&  chefDAO.find(details.get("username")).getName().equals(details.get("name")) &&
                chefDAO.find(details.get("username")).getSurname().equals(details.get("surname")) && String.valueOf(chefDAO.find(details.get("username")).getIban()).equals(details.get("iban"))
                && String.valueOf(chefDAO.find(details.get("username")).getTin()).equals(details.get("tin")) && String.valueOf(chefDAO.find(details.get("username")).getTelephone()).equals(details.get("telephone"))){

            restaurant.addChef(chefDAO.find(details.get("username")));
            view.showChefAddedMessage();
        }
    }
    /**
     * It calls the view method that takes us to the previous activity that called us
     */
    public void onBack(){
        view.goBack();
    }

    /**
     *It returns the view object that we created above
     * @return the object instance
     */
    public AddChefView getView(){
        return this.view;
    }

    /**
     * It returns the restaurant object that we created above
     * @return the object instance
     */
    public Restaurant getRestaurant(){
        return this.restaurant;
    }
}
