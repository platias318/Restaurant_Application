package gr.aueb.softeng.view.Owner.HomePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import gr.aueb.softeng.domain.Restaurant;
import gr.aueb.softeng.team08.R;

import gr.aueb.softeng.view.Customer.ChooseRestaurant.ChooseRestaurantRecyclerViewAdapter;
import gr.aueb.softeng.view.Owner.AddRestaurant.AddRestaurantActivity;
import gr.aueb.softeng.view.Owner.RestaurantDetails.RestaurantDetailsActivity;

/**
 *This class gets called when the owner logs in and displays his restaurants
 */
public class OwnerHomePageActivity extends AppCompatActivity implements OwnerHomePageView,
        OwnerHomePageRecyclerViewAdapter.ItemSelectionListener{
    public int ownerId;
    RecyclerView recyclerView;
    TextView emptyView;
    OwnerHomePageViewModel viewModel;
    /**
     * It creates the layout and initializes the activity.
     * It initializes the view model and passes the view to the presenter.
     * It gives the presenter the ownerId and initializes the layout elements.
     * @param savedInstanceState The instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_owner_home_page);

        viewModel = new ViewModelProvider(this).get(OwnerHomePageViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            ownerId = extras.getInt("OwnerId");
        }
        viewModel.getPresenter().setOwner(ownerId);
        viewModel.getPresenter().setRestaurantList();
        // ui initialization
         recyclerView = findViewById(R.id.RestaurantRecyclerView);
         emptyView = findViewById(R.id.emptyView);
         viewModel.getPresenter().onChangeLayout();

        findViewById(R.id.AddRestaurantButton).setOnClickListener(new View.OnClickListener() { //Όταν πατηθεί το κουμπί προσθήκης εστιατορίου
            public void onClick(View v) {
                viewModel.getPresenter().onAddRestaurant();
            }
        });

        findViewById(R.id.gobackButton5).setOnClickListener(new View.OnClickListener(){ //Όταν πατηθεί το κουμπί επιστροφής στην προηγούμενη σελίδα
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onBack();
            }
        });

    }

    /**
     * It's called when we return to the screen of this activity.
     * It updates the Restaurant list in case a new one has been added, so it can appear in the Recycler View, and also updates the Recycler View's adapter.
     * It calls the changeLayout method of the presenter.
     */
    @Override
    protected void onResume(){
        super.onResume();
        viewModel.getPresenter().setRestaurantList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OwnerHomePageRecyclerViewAdapter(viewModel.getPresenter().getRestaurantList(), this));
        viewModel.getPresenter().onChangeLayout();
    }

    /**
     *It calls the activity to display the details of the restaurant that was passed as a parameter.
     * @param restaurant the restaurant selected in the Recycler View by the user.
     */
    @Override
    public void selectRestaurant(Restaurant restaurant) {
        Intent intent = new Intent(OwnerHomePageActivity.this, RestaurantDetailsActivity.class);
        intent.putExtra("RestaurantId", restaurant.getId());
        startActivity(intent);
    }

    /**
     * It calls the activity in which the details of the new restaurant we want to add to the owner will be entered.
     */
    public void AddRestaurant(){
        Intent intent = new Intent(OwnerHomePageActivity.this, AddRestaurantActivity.class);
        intent.putExtra("OwnerId",ownerId);
        startActivity(intent);
    }

    /**
     * It's called when the owner's list of restaurants is empty, so that the notification message appears on the screen
     * and the empty recycler view is hidden.
     */
    @Override
    public void ShowNoRestaurants() {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    /**
     * It's called when the owner's list of restaurants is NOT empty, and it displays the items
     * in the recycler view, while hiding the message that would be shown if the list was empty.
     */
    @Override
    public void ShowRestaurants() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OwnerHomePageRecyclerViewAdapter(viewModel.getPresenter().getRestaurantList(), this));
    }


    /**
     *
     * It's called when we want to return to the previous activity, in our case, the login page (which calls our activity).
     */
    public void goBack(){
        finish();
    }
}

