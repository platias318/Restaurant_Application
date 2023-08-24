package gr.aueb.softeng.view.Login;

import gr.aueb.softeng.dao.ChefDAO;
import gr.aueb.softeng.dao.CustomerDAO;
import gr.aueb.softeng.dao.OwnerDAO;
import gr.aueb.softeng.dao.UserDAO;
import gr.aueb.softeng.domain.Chef;
import gr.aueb.softeng.domain.Customer;
import gr.aueb.softeng.domain.Owner;

public class LoginPresenter {
    private LoginView view;
    private ChefDAO chefDAO;
    private CustomerDAO custDAO;
    private OwnerDAO ownerDAO;
    private UserDAO userDAO;
    /**
     * It initializes the user DAO, customer DAO, chef DAO, and owner DAO in order to store and retrieve customers, chefs, owners, and users from our static lists.
     * @param userDAO The instance of the DAO
     * @param custDAO The instance of the DAO
     * @param chefDAO The instance of the DAO
     * @param ownerDAO The instance of the DAO
     */
    public LoginPresenter(ChefDAO chefDAO, CustomerDAO custDAO, OwnerDAO ownerDAO,UserDAO userDAO)
    {
        this.chefDAO = chefDAO;
        this.custDAO = custDAO;
        this.ownerDAO = ownerDAO;
        this.userDAO = userDAO;
    }
    /**
     *It returns the view object that we created above
     * @return The object instance
     */
    public LoginView getView() {
        return view;
    }

    private String inputUsername;
    private String inputPassword;
    /**
     * It initializes the view from which we will use the methods of its interface.
     * @param view The instance of the view.
     */
    public void setView(LoginView view) {
        this.view = view;
    }

    /**
     *
     * This method initially checks in which category the user who clicked the login button belongs to,
     * or if they don't belong to any category and have incorrect credentials. It displays the appropriate notification message
     * on the screen and calls the respective success message based on the user's category.
     */
    public void authenticate() {
        inputUsername = view.ExtractUsername();
        inputPassword = view.ExtractPassword();

        Customer cust = custDAO.find(inputUsername, inputPassword);
        Chef chef= chefDAO.find(inputUsername, inputPassword);
        Owner owner = ownerDAO.find(inputUsername, inputPassword);

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            view.showErrorMessage("Error!", "Please fill in all the fields.");
        }else if (cust != null) {
            view.showCustomerFoundMessage(cust.getUserId());
        }
        else if (chef != null) {
            view.showChefFoundMessage(chef.getUserId());
        }
        else if (owner != null) {
            view.showOwnerFoundMessage(owner.getUserId());
        }
        else{
            view.showErrorMessage("Incorrect details", "The information you entered was not correct. Please try again.");
        }
    }

    /**
     *It gets called so that the customer can sign up
     */
    public void onSignup(){
        view.signup();
    }

    /**
     * It gets called so that the chef can sign up
     */
    public void onSignupPersonel() {view.signupPersonel();}

    /**
     *It gets called so that the owner can sign up
     */
    public void onSignupOwner(){view.signupOwner();}
}
