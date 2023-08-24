package gr.aueb.softeng.view.Chef.OrderDetails;

import androidx.lifecycle.ViewModel;

import gr.aueb.softeng.memoryDao.ChefDAOmemory;
import gr.aueb.softeng.memoryDao.OrderDAOmemory;


public class OrderDetailsViewModel extends ViewModel {
    private OrderDetailsPresenter presenter;
    /**
     * It initializes the presenter by passing new DAOs as parameters that it will use.
     */
    public OrderDetailsViewModel(){
        presenter= new OrderDetailsPresenter(new ChefDAOmemory(), new OrderDAOmemory());
    }
    /**
     *
     * @return returns the presenter where the data is saved
     */
    public OrderDetailsPresenter getPresenter(){
        return this.presenter;
    }
}
