package gr.aueb.softeng.view.Owner.AddRestaurant;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import gr.aueb.softeng.memoryDao.OwnerDAOmemory;
import gr.aueb.softeng.memoryDao.RestaurantDAOmemory;

public class AddRestaurantViewModel extends ViewModel {
    AddRestaurantPresenter presenter;
    /**
     * It initializes the presenter and passes gim a new owner dao and restaurant dao object so that he can use them
     */
    public AddRestaurantViewModel(){
        presenter = new AddRestaurantPresenter(new OwnerDAOmemory(), new RestaurantDAOmemory());
    }
    /**
     * It returns the presenter to the classes where it contains the information.
     * @return The presenter instance that we created above
     */
    public AddRestaurantPresenter getPresenter(){
        return this.presenter;
    }

}
