package gr.aueb.softeng.view.SignUp.SignUpCustomer;

import androidx.lifecycle.ViewModel;


import gr.aueb.softeng.memoryDao.CustomerDAOmemory;
import gr.aueb.softeng.memoryDao.UserDAOmemory;

public class SignUpCustomerViewModel extends ViewModel {
SignUpCustomerPresenter presenter;
    /**
     * Initializes the presenter and passes it a new object of type "user dao memory" and "restaurant dao memory" for it to use.
     */
public SignUpCustomerViewModel()
{
    presenter = new SignUpCustomerPresenter(new UserDAOmemory(), new CustomerDAOmemory());

}
    /**
     * Returns the presenter to the classes where it contains the information.
     * @return the instance of the presenter that we created above.
     */
    public SignUpCustomerPresenter getPresenter()
    {
        return presenter;
    }
}
