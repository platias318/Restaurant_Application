package gr.aueb.softeng.view.SignUp.SignUpCustomer;

import java.util.HashMap;
import java.util.Map;

import gr.aueb.softeng.dao.CustomerDAO;
import gr.aueb.softeng.dao.UserDAO;
import gr.aueb.softeng.domain.Customer;

public class SignUpCustomerPresenter {
    private CustomerDAO customerDAO;
    private  UserDAO userDAO;
    /**
     * Initializes the user DAO and customer DAO to be able to store and retrieve customers and users from our static list.
     * @param userDAO
     * @param custDAO
     */
    public SignUpCustomerPresenter(UserDAO userDAO, CustomerDAO custDAO)
    {
        this.customerDAO = custDAO;
        this.userDAO=userDAO;
    }
    SignUpCustomerView view;
    /**
     * Initializes the view from which we will use the methods of its interface.
     * @param v Instance of the view
     */
    public void setView(SignUpCustomerView v)
    {
        this.view = v;
    }
    /**
     * Returns the view object that we created above.
     * @return the object instance
     */
    public SignUpCustomerView getView() {
        return view;
    }
    /**

     This method is called when the customer clicks on the "Create Account" button after entering all their information.
     It performs checks on each field to determine if it is acceptable, and if any field is not acceptable,
     it displays a notification message on the customer's screen, prompting them to make the necessary changes. If the entered information is correct, an appropriate message is displayed, and the customer is added to the application.
     */
    public void onCreateAccount(){
        boolean isEmpty=false;
        HashMap<String,String> details = view.getDetails();

        for(Map.Entry<String, String> set: details.entrySet()){
            if(set.getValue().isEmpty() || set.getValue()==null){
                isEmpty=true;
                break;
            }
        }
        if(isEmpty){
            view.showErrorMessage("Error!", "Please fill in all the fields!.");
        }else if(details.get("username").length() < 4 || details.get("username").length() > 15) {
            view.showErrorMessage("Error!", "Enter 4 to 15 characters in the Username.");
        }else if(!details.get("email").contains("@") &&( !details.get("email").contains(".com") || !details.get("email").contains(".gr"))) {
            view.showErrorMessage("Error!", "Please enter the email correctly.");
        }else if (details.get("telephone").length() != 10 ) {
            view.showErrorMessage("Error!", "Please provide a valid phone number.");
        }else if (details.get("cardNumber").length() != 16) { // anti gia 5 na valw akrivws posa einai pragmatika
            view.showErrorMessage("Error!", "Please enter a valid 16-digit card number.");
        }else if (details.get("password").length() < 8) {
            view.showErrorMessage("Error!", "The password should consist of 8 digits or more");
        }else if (details.get("cvv").length() != 3 || !details.get("cvv").matches("\\d+")) {
            view.showErrorMessage("Error!", "Please enter a valid CVV.");
        }else if (userDAO.find(details.get("username"))!=null){
            view.showErrorMessage("Error!","There is already an account with this username. \nPlease provide new details.!" );
        }else{
            Customer customer= new Customer(details.get("username"),details.get("name"),details.get("surname"),details.get("telephone"),
                    details.get("email"),details.get("password"), customerDAO.nextId(),details.get("cardNumber"),details.get("cardHolderName"),
                    details.get("cvv"));

            customerDAO.save(customer);
            userDAO.save(customer);

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
