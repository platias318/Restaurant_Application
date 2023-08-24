package gr.aueb.softeng.view.Chef.HomePage;

import androidx.lifecycle.ViewModel;

import gr.aueb.softeng.memoryDao.ChefDAOmemory;
import gr.aueb.softeng.memoryDao.OrderDAOmemory;


public class ChefHomePageViewModel extends ViewModel {
    private ChefHomePagePresenter presenter;
    /**
     * Initializes the presenter by passing new DAOs as parameters that it will use.
     */
    public ChefHomePageViewModel(){
        presenter= new ChefHomePagePresenter(new ChefDAOmemory(), new OrderDAOmemory());
    }
    /**
     *
     * @return Returns the presenter that holds the stored data.
     */
    public ChefHomePagePresenter getPresenter(){
        return this.presenter;
    }
}
