package gr.aueb.softeng.view.Owner.HomePage;

import androidx.lifecycle.ViewModel;


import gr.aueb.softeng.memoryDao.OwnerDAOmemory;
import gr.aueb.softeng.memoryDao.RestaurantDAOmemory;

public class OwnerHomePageViewModel extends ViewModel {

    private OwnerHomePagePresenter presenter;

    /**
     * It initializes the presenter and passes him parameters the new daos that he is gonna need to use
     */
    public OwnerHomePageViewModel(){
        presenter= new OwnerHomePagePresenter(new OwnerDAOmemory() , new RestaurantDAOmemory());
    }

    /**
     *
     * @return Returns the presenter in which we have stored out data
     */
    public OwnerHomePagePresenter getPresenter(){
        return this.presenter;
    }
}
