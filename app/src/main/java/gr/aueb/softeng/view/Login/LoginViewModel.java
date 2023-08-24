package gr.aueb.softeng.view.Login;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import gr.aueb.softeng.memoryDao.ChefDAOmemory;
import gr.aueb.softeng.memoryDao.CustomerDAOmemory;
import gr.aueb.softeng.memoryDao.OwnerDAOmemory;
import gr.aueb.softeng.memoryDao.UserDAOmemory;

public class LoginViewModel extends ViewModel{

    private LoginPresenter loginPresenter;
    /**
     *
     * It initializes the presenter and passes it a new object of type "user DAO memory,"
     * "restaurant DAO memory," "user DAO memory," and "owner DAO memory" to use.
     */
    public LoginViewModel()
    {
        loginPresenter = new LoginPresenter(new ChefDAOmemory(), new CustomerDAOmemory(), new OwnerDAOmemory(),new UserDAOmemory());
    }
    /**
     * It returns the presenter to the classes where it contains the information.
     * @return The instance of the presenter that was created earlier.
     */
    public LoginPresenter getPresenter() {
        return loginPresenter;
    }

}
