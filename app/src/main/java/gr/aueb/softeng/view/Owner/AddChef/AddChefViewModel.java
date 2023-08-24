package gr.aueb.softeng.view.Owner.AddChef;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import gr.aueb.softeng.memoryDao.ChefDAOmemory;
import gr.aueb.softeng.memoryDao.RestaurantDAOmemory;

public class AddChefViewModel extends ViewModel {
    AddChefPresenter presenter;
    /**
     * It initializes the presenter object and passes a new chef dao object and a restaurant dao object as well
     */
   // It initializes the presenter object and passes a new chef dao object and a restaurant dao object as well
    public AddChefViewModel() {
        presenter = new AddChefPresenter(new RestaurantDAOmemory(), new ChefDAOmemory());

    }
    /**
     * Returns the presenter instance to the classes where it contains the information.
     * @return The presenter instance we created above
     */
    public AddChefPresenter getPresenter() {
        return presenter;
    }
}
