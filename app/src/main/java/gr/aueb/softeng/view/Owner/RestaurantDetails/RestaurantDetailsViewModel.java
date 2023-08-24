package gr.aueb.softeng.view.Owner.RestaurantDetails;

import android.util.Log;

import androidx.lifecycle.ViewModel;


import gr.aueb.softeng.memoryDao.RestaurantDAOmemory;


public class RestaurantDetailsViewModel extends ViewModel {
    RestaurantDetailsPresenter presenter;

    /**
     * Initializes the presenter and passes a new object of type RestaurantDAO to it for usage.
     */
    public RestaurantDetailsViewModel(){
        presenter = new RestaurantDetailsPresenter(new RestaurantDAOmemory());
    }

    /**
     * Returns the presenter to the classes where it contains the information.
     * @return the instance of the presenter that was created earlier.
     */
    public RestaurantDetailsPresenter getPresenter()
    {
        return presenter;
    }

}
