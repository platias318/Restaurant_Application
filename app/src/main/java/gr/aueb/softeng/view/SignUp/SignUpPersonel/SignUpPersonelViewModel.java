package gr.aueb.softeng.view.SignUp.SignUpPersonel;

import androidx.lifecycle.ViewModel;

import gr.aueb.softeng.memoryDao.ChefDAOmemory;
import gr.aueb.softeng.memoryDao.UserDAOmemory;

public class SignUpPersonelViewModel extends ViewModel {
    SignUpPersonelPresenter presenter;
    /**
     * It initializes the presenter and passes it a new object of type 'user dao memory' and 'chef dao memory' to use.
     */
    public SignUpPersonelViewModel() {
        presenter = new SignUpPersonelPresenter(new UserDAOmemory(), new ChefDAOmemory());

    }
    /**
     * It returns the presenter to the classes where it holds the information.
     * @return the instance of the presenter that we created earlier.
     */
    public SignUpPersonelPresenter getPresenter() {
        return presenter;
    }

}
