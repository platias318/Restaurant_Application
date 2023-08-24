package gr.aueb.softeng.view.Customer.ChooseRestaurant;

import androidx.lifecycle.ViewModel;

import gr.aueb.softeng.memoryDao.RestaurantDAOmemory;


public class ChooseRestaurantViewModel extends ViewModel {
    ChooseRestaurantPresenter presenter;
    /**
     * It initializes the presenter by passing it new daos as parameters that it will use.
     */
    public ChooseRestaurantViewModel()
    {
        presenter = new ChooseRestaurantPresenter(new RestaurantDAOmemory());
    }
    /**
     *
     * @return Returns the presenter to whom the data have been stored
     */
    public ChooseRestaurantPresenter getPresenter() {
        return presenter;
    }
}
