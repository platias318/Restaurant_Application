package gr.aueb.softeng.view.Customer.HomePage;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.PopupWindow;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;


import gr.aueb.softeng.memoryDao.ChefDAOmemory;
import gr.aueb.softeng.memoryDao.CustomerDAOmemory;
import gr.aueb.softeng.memoryDao.OrderDAOmemory;
import gr.aueb.softeng.team08.R;
import gr.aueb.softeng.view.Chef.OrderDetails.OrderDetailsActivity;
import gr.aueb.softeng.view.Customer.PlaceOrder.PlaceOrderActivity;
import gr.aueb.softeng.view.Customer.TopUp.TopUpActivity;


/**
 * On this page, the user can see their current order, cancel it, view their order history, or be directed to the top-up page and the order entry page.
 */
public class CustomerHomePageActivity extends AppCompatActivity implements CustomerHomepageView,FragmentListener{

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private int customerId = -1;
    private int restaurantId = -1;
    private CustomerHomePageViewModel viewModel;
    private int tableNumber = 0;

    /**
     * A pop-up window appears for the selection
     * * of an integer corresponding to its number
     * * of the table for the order that is about to
     * * be submitted.
     */
    public void showTableNumberPickerPopup() {

        PopupWindow popupWindow = new PopupWindow(this);

        // Set the content view of the pop-up window
        View contentView = getLayoutInflater().inflate(R.layout.quantity_picker, null);
        popupWindow.setContentView(contentView);

        // Set the width and height of the pop-up window
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        // Configure the NumberPicker
        NumberPicker numberPicker = contentView.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(viewModel.getPresenter().getRestaurantCapacity());

        // Configure the Confirm button
        Button confirmButton = contentView.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the selected number
                int tableNo = numberPicker.getValue();
                if (viewModel.getPresenter().checkTableAvailability(tableNo))
                {
                    tableNumber = tableNo;
                    popupWindow.dismiss();
                    redirectPlaceOrder();
                }// Dismiss the pop-up window
                else
                {
                    tableUnavailableMessage();
                }
            }
        });

        // Show the pop-up window
        popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
    }

    /**
     * Displays an alert message
     * about the unavailability of the selected table
     * that was chosen with the pop-up window
     * from the method showTableNumberPickerPopup().
     */
    public void tableUnavailableMessage()
    {
        new android.app.AlertDialog.Builder(CustomerHomePageActivity.this)
                .setCancelable(true)
                .setTitle("Unavailable Table.")
                .setMessage("The table you have chosen is not available.")
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * Displays an alert message
     * to ask the user if they are sure
     * they want to cancel the order.
     */
    public void ShowConfirmationMessage() {
        new AlertDialog.Builder(CustomerHomePageActivity.this)
                .setTitle("Order Cancellation")
                .setMessage("Are you sure you want to continue?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.getPresenter().cancel();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

    }


    /**
     * Creates the layout and initializes
     * the activity.
     * @param savedInstanceState the Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_customer_homepage);


        viewModel = new ViewModelProvider(this).get(CustomerHomePageViewModel.class);
        viewModel.getPresenter().setView(this);
        if (savedInstanceState == null)
        {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();

            if (extras != null) {
                customerId = extras.getInt("CustomerId");
                restaurantId = extras.getInt("RestaurantId");
            }
        }
        viewModel.getPresenter().setCustomer(customerId);
        viewModel.getPresenter().setRestaurant(restaurantId);
        viewModel.getPresenter().setCurrentOrder();

        //tabbed Layout initialization
        tabLayout = findViewById(R.id.CustomerHomePageTabLayout);//tabs currentOrderTab and orderHistoryTab
        viewPager2 = findViewById(R.id.CustomerHomePageViewPager);
        viewPager2.setUserInputEnabled(false);//No touch scrolling allowed
        CustomerHomePageViewPagerAdapter adapter = new CustomerHomePageViewPagerAdapter(this,this);
        viewPager2.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());//the appropriate tab is set when selecte
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }
        });

        findViewById(R.id.TopUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onTopUp();
            }
        });





    }

    @Override
    protected void onPause() {
        super.onPause();
        viewPager2.setAdapter(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager2.setAdapter(new CustomerHomePageViewPagerAdapter(this,this));
        viewModel.getPresenter().setCurrentOrder();
        viewModel.getPresenter().setOrderHistory();
    }

    /**
     * Moves to the balance top-up page.
     */
    public void redirectTopUp() {
        Intent intent = new Intent(CustomerHomePageActivity.this , TopUpActivity.class) ;
        intent.putExtra("CustomerId",customerId);
        startActivity(intent);
    }


    /**
     * Moves to the order details page
     * for the currently active order.
     */
    @Override
    public void showCurrentOrder() {
        if (((CustomerHomePageViewPagerAdapter)viewPager2.getAdapter()).getCurrFragment() instanceof CurrentOrderPageFragment) {
            CurrentOrderPageFragment currFragment = (CurrentOrderPageFragment) ((CustomerHomePageViewPagerAdapter)viewPager2.getAdapter()).getCurrFragment();
            currFragment.showCurrentOrder();
        }}

    /**
     * Moves to the order details page
     * for a specific order selected from the history.
     */
    @Override
    public void showNoCurrentOrder() {
        if (((CustomerHomePageViewPagerAdapter)viewPager2.getAdapter()).getCurrFragment() instanceof CurrentOrderPageFragment) {
            CurrentOrderPageFragment currFragment = (CurrentOrderPageFragment) ((CustomerHomePageViewPagerAdapter) viewPager2.getAdapter()).getCurrFragment();
            currFragment.showNoCurrentOrder();
        }
    }


    public CustomerHomePageViewModel getViewModel()
    {
        return viewModel;
    }

    /**
     * Redirects to the order placement page.
     */
    @Override
    public void redirectToOrderDetails() {
        Intent intent = new Intent(CustomerHomePageActivity.this , OrderDetailsActivity.class) ;
        intent.putExtra("IsCustomer",true);
        intent.putExtra("OrderId",viewModel.getPresenter().getCurrentOrder().getId());
        startActivity(intent);
    }

    /**
     * Moves to the order details page
     * for a specific order selected from the history.
     */
    @Override
    public void redirectToOrderDetails(int id) {
        Intent intent = new Intent(CustomerHomePageActivity.this , OrderDetailsActivity.class) ;
        intent.putExtra("IsCustomer",true);
        intent.putExtra("OrderId",id);
        startActivity(intent);
    }

    public void redirectPlaceOrder()
    {
        Intent intent = new Intent(CustomerHomePageActivity.this , PlaceOrderActivity.class) ;
        intent.putExtra("RestaurantId",restaurantId);
        intent.putExtra("CustomerId",customerId);
        intent.putExtra("TableNumber",tableNumber);
        startActivity(intent);
    }

}