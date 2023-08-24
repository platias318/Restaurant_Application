package gr.aueb.softeng.view.SignUp.SignUpOwner;

import java.util.HashMap;
import java.util.Map;

import gr.aueb.softeng.dao.OwnerDAO;
import gr.aueb.softeng.dao.UserDAO;
import gr.aueb.softeng.domain.Owner;


public class SignUpOwnerPresenter {
    private OwnerDAO ownerDAO;
    private UserDAO userDAO;
    SignUpOwnerView view;
    /**
     * Initializes the user dao and customer dao to be able to store and retrieve from our static list the owners and users.
     * @param userDAO
     * @param ownerDAO
     */
    public SignUpOwnerPresenter( UserDAO userDAO, OwnerDAO ownerDAO)
    {
        this.ownerDAO = ownerDAO;
        this.userDAO = userDAO;
    }
    /**
     * Initializes the view from which we will use the methods of its interface.
     * @param v Instance of the view
     */
    public void setView(SignUpOwnerView v)
    {
        this.view = v;
    }
    /**
     * Returns the view object that we created above.
     * @return the Instance of the object
     */
    public SignUpOwnerView getView() {
        return view;
    }
    /**

     This method is called when the owner clicks on the account creation button after all the required information has been entered. It performs checks on each field to determine if it is acceptable. If a field is not acceptable, a notification message is displayed on the owner's screen, prompting them to make the necessary changes.
     If the information is correct, an appropriate message is displayed, and the owner is added to the application.
     */
    public void onCreateOwnerAccount(){
        boolean isEmpty=false;
        HashMap<String,String> details = view.getOwnerDetails();

        for(Map.Entry<String, String> set: details.entrySet()){
            if(set.getValue().isEmpty() || set.getValue()==null){
                isEmpty=true;
                break;
            }
        }
        if(isEmpty){
            view.showErrorMessage("Error!", "Please fill in all the fields.!.");
        }else if(details.get("username").length() < 4 || details.get("username").length() > 15) {
            view.showErrorMessage("Error!", "Enter 4 to 15 characters in the Username.");
        }else if(!details.get("email").contains("@") &&( !details.get("email").contains(".com") || !details.get("email").contains(".gr"))) {
            view.showErrorMessage("Error!", "Please enter the email correctly.");
        }else if (details.get("telephone").length() != 10) {
            view.showErrorMessage("Error!", "Please provide a valid phone number..");
        }else if (details.get("iban").length() < 20) { // iban has 34 digits in our example we use greater than 20 for easier Input in the ui
            view.showErrorMessage("Error!", "Please enter a valid IBAN.");
        }else if (details.get("password").length() < 8) {
            view.showErrorMessage("Error!", "The password should consist of 8 digits or more.");
        }else if (details.get("tin").length() != 9){
            view.showErrorMessage("Error!", "Please enter a valid Tax Identification Number (AFM) with 9 digits.");
        }else if (userDAO.find(details.get("username"))!=null){
            view.showErrorMessage("Error!","There is already an account with this username. \n Please provide new details!" );
        }else{
            Owner owner= new Owner(details.get("username"),details.get("name"),details.get("surname"),details.get("telephone"),
                    details.get("email"),details.get("password"), ownerDAO.nextId(),details.get("iban"),details.get("tin"));

            ownerDAO.save(owner);
            userDAO.save(owner); // na dw min exei thema kai epeidh den einai tipou user den touw vazei

            view.showAccountCreatedMessage();
        }
    }
    /**
     * It calls the method of the view that takes us back to the previous activity that invoked us.
     */
    public void onBack(){
        view.goBack();
    }

}
