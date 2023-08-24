package gr.aueb.softeng.view.Chef.HomePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import gr.aueb.softeng.domain.Order;
import gr.aueb.softeng.team08.R;
import gr.aueb.softeng.view.Chef.OrderDetails.OrderDetailsActivity;

/**
 * This class is called to display the chef's initial page with his orders.
 */
public class ChefHomePageActivity extends AppCompatActivity implements ChefHomePageView,
            ChefHomePageRecyclerViewAdapter.ItemSelectionListener{

    public int chefId;
    RecyclerView recyclerView;
    TextView emptyView;
    ChefHomePageViewModel viewModel;
    /**
     *Creates the layout and initializes the activity.
     *Initializes the view model and passes the view to the presenter.
     *Provides the presenter with the chefId and initializes the layout's elements.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_chef_home_page);

        viewModel = new ViewModelProvider(this).get(ChefHomePageViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            chefId = extras.getInt("ChefId");
        }
        viewModel.getPresenter().setChef(chefId);
        viewModel.getPresenter().setOrderList();
        // ui initialization
        recyclerView = findViewById(R.id.recyclerViewChef);
        emptyView = findViewById(R.id.emptyOrdersChefText); // The TextView that appears when the list of orders is empty.
        viewModel.getPresenter().onChangeLayout();

        findViewById(R.id.gobackButton4).setOnClickListener(new View.OnClickListener(){ // Το κουμπί επιστροφής στην προηγούμενη σελίδα
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onBack();
            }
        });

    }
    /**
     *Called when returning to the screen of this activity.
     *Updates the list of orders in case a new one has been added to be displayed in the Recycler View, as well as the adapter of the recycler view.
     *Calls the changeLayout method of the presenter.
     */
    @Override
    protected void onResume(){
        super.onResume();
        viewModel.getPresenter().setOrderList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChefHomePageRecyclerViewAdapter(viewModel.getPresenter().getOrderList(), this));
        viewModel.getPresenter().onChangeLayout();
    }
    /**
     *Calls the activity to display the details of the order passed as a parameter.
     *@param order the order selected in the Recycler View by the chef.
     */
    @Override
    public void selectOrder(Order order) {
        Intent intent = new Intent(ChefHomePageActivity.this, OrderDetailsActivity.class);
        intent.putExtra("IsCustomer", false);
        intent.putExtra("OrderId", order.getId());
        startActivity(intent);
    }
    /**
     *This method is called when the chef's list of orders is empty, in order to display the message on the screen that the list is empty.
     */
    @Override
    public void ShowNoOrders() {
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    /**
     *This method is called when the list of orders is NOT empty, and the Recycler View with its items is displayed on the screen.
     *It also sets up the adapter and layout manager of the recycler view.
     */
    @Override
    public void ShowOrders() {
        recyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ChefHomePageRecyclerViewAdapter(viewModel.getPresenter().getOrderList(), this));
    }
    /**
     * Called when we want to return to the previous Activity, which in our case is the login page (this is called by our activity).
     */
    public void goBack(){
        finish();
    }
}