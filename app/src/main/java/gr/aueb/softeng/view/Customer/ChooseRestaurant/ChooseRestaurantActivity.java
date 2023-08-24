package gr.aueb.softeng.view.Customer.ChooseRestaurant;

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
import gr.aueb.softeng.view.Customer.HomePage.CustomerHomePageActivity;

/**
 * In this page the user chooses the restaurant that he wants to make the order
 */
public class ChooseRestaurantActivity extends AppCompatActivity implements ChooseRestaurantView, ChooseRestaurantRecyclerViewAdapter.RestaurantSelectionListener {

    ChooseRestaurantViewModel viewModel;
    int customerId;
    RecyclerView recyclerView;
    TextView emptyView;

    /**
     * Creates the layout and initializes
     * the activity.
     * @param savedInstanceState the state Instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_choose_restaurant);

        viewModel = new ViewModelProvider(this).get(ChooseRestaurantViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            customerId = extras.getInt("CustomerId");
        }
        viewModel.getPresenter().setRestaurantList();
        // ui initialization
        recyclerView = findViewById(R.id.ChooseRestaurantRecyclerView);
        emptyView = findViewById(R.id.NoRestaurants);
        viewModel.getPresenter().onChangeLayout();
        findViewById(R.id.gobackButton6).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onBack();
            }
        });

    }

    /**
     *  This method is called when we select the restaurants and we are heading to the HomePage.
     *  @param The restaurant that was selected
     */
    @Override
    public void selectRestaurant(Restaurant restaurant) {
        Intent intent = new Intent(ChooseRestaurantActivity.this, CustomerHomePageActivity.class);
        intent.putExtra("RestaurantId",restaurant.getId());
        intent.putExtra("CustomerId",customerId);
        startActivity(intent);
    }
    public void goBack(){
        finish();
    }

    /**
     * We hide the recyclerView and make visible
     * an update message for the absence of restaurants.
     */
    @Override
    public void ShowNoRestaurants() {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    /**
     * We display and set up the recyclerView and hide the message of absence of restaurants.
     */
    @Override
    public void ShowRestaurants() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChooseRestaurantRecyclerViewAdapter(viewModel.getPresenter().getRestaurantList(), this));
    }
}