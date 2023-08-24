package gr.aueb.softeng.view.Owner.Statistics;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import gr.aueb.softeng.memoryDao.OwnerDAOmemory;
import gr.aueb.softeng.memoryDao.RestaurantDAOmemory;


public class StatisticsViewModel extends ViewModel {
   StatisticsPresenter presenter;
    /**
     * Initializes the presenter and passes it a new instance of type owner dao and restaurant dao to use.
     */
    public StatisticsViewModel(){
        presenter = new StatisticsPresenter(new OwnerDAOmemory(),new RestaurantDAOmemory());
    }
    /**
     * Returns the presenter to the classes where it contains the information.
     * @returns the instance of the presenter that was created above.
     */
    public StatisticsPresenter getPresenter()
    {
        return presenter;
    }

}
