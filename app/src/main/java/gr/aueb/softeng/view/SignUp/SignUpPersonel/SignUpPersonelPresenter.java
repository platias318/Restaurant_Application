package gr.aueb.softeng.view.SignUp.SignUpPersonel;

import java.util.HashMap;
import java.util.Map;

import gr.aueb.softeng.dao.ChefDAO;

import gr.aueb.softeng.dao.UserDAO;
import gr.aueb.softeng.domain.Chef;


public class SignUpPersonelPresenter {
    private ChefDAO chefDAO;
    private UserDAO userDAO;
    SignUpPersonelView view;
    /**
     * Initialize the user dao and chef dao in order to be able to store and retrieve from our static list the chefs and the users.
     * @param userDAO
     * @param chefDAO
     */
    public SignUpPersonelPresenter(UserDAO userDAO, ChefDAO chefDAO)
    {
        this.chefDAO = chefDAO;
        this.userDAO = userDAO;
    }
    /**
     * Initializes the view from which we will utilize the methods of its interface.
     * @param v the view instance
     */
    public void setView(SignUpPersonelView v)
    {
        this.view = v;
    }
    /**
     * Returns the view object that we created earlier
     * @return the object instance
     */
    public SignUpPersonelView getView() {
        return view;
    }
    /**
     * This method is called when the account creation button is pressed by the chef, after all its details have been entered.
     * We perform checks on each field to determine if it's acceptable, and if it's not, a notification message appears on the chef's screen, informing them to make the necessary changes.
     * If the details are correct, an appropriate message is displayed, and the chef is added to the application.
     */
    public void onCreateChefAccount(){
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
        }else if(!details.get("email").contains("@") &&( !details.get("email").contains(".com") || !details.get("email").contains(".gr"))) {
            view.showErrorMessage("Error!", "Please provide the correct email.");
        }else if (details.get("telephone").length() != 10) {
            view.showErrorMessage("Error!", "Please enter a valid phone number.");
        }else if (details.get("iban").length() < 20) { // iban has 34 digits in our example we use greater than 20 for easier Input in the ui
            view.showErrorMessage("Error!", "Please provide a valid IBAN.");
        }else if (details.get("password").length() < 8) {
            view.showErrorMessage("Error!", "The password should consist of 8 digits or more.");
        }else if (details.get("tin").length() != 9){
            view.showErrorMessage("Error!", "Please enter a valid Tax Identification Number (AFM) with 9 digits.");
        }else if (userDAO.find(details.get("username"))!=null){ // there is already a user with the same username and password
            view.showErrorMessage("Error!","There is already an account with this username.\n Please provide new details." );
        }else{

            Chef chef= new Chef(details.get("username"),details.get("name"),details.get("surname"),details.get("telephone"),
                    details.get("email"),details.get("password"), chefDAO.nextId(),details.get("iban"),details.get("tin"));

            chefDAO.save(chef);
            userDAO.save(chef);

            view.showAccountCreatedMessage();
        }

    }
    /**
     * It calls the method of the view that takes us back to the previous activity that invoked us
     */
    public void onBack(){
        view.goBack();
    }
}
