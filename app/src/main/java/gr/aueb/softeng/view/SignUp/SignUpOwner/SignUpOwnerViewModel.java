package gr.aueb.softeng.view.SignUp.SignUpOwner;

import androidx.lifecycle.ViewModel;


import gr.aueb.softeng.memoryDao.OwnerDAOmemory;
import gr.aueb.softeng.memoryDao.UserDAOmemory;


public class SignUpOwnerViewModel extends ViewModel {
    SignUpOwnerPresenter presenter;
    /**
     * The translation of the sentence you provided from Greek to English is: “Initializes the presenter and passes it a new object of type user dao memory and owner dao memory to use
     */
    public SignUpOwnerViewModel() {
        presenter = new SignUpOwnerPresenter(new UserDAOmemory(), new OwnerDAOmemory());
    }
    /**
     * Returns the presenter to the classes where it contains the information
     * @return the instance of the presenter we created above
     */
    public SignUpOwnerPresenter getPresenter() {
        return presenter;
    }
}
