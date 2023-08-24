package gr.aueb.softeng.view.Chef.OrderDetails;
import gr.aueb.softeng.team08.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This class displays the details of the selected order on the chef's screen.
 */
public class OrderDetailsActivity extends AppCompatActivity  implements OrderDetailsView {

    public int OrderId;
    RecyclerView recyclerView;
    OrderDetailsViewModel viewModel;

    Button setCompletedButton;

    boolean isCustomer;
    /**
     *Creates the layout and initializes the activity.
     *Initializes the view model and passes the view to the presenter.
     *Provides the presenter with the ownerId and initializes the layout's elements.
     *@param savedInstanceState The instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_order_details);

        viewModel = new ViewModelProvider(this).get(OrderDetailsViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            OrderId = extras.getInt("OrderId");
            isCustomer = extras.getBoolean("IsCustomer");

        }

        viewModel.getPresenter().setOrder(OrderId);
        viewModel.getPresenter().setOrderLineList();
        viewModel.getPresenter().setOrderDetails();
        // ui initialization
        recyclerView = findViewById(R.id.OrderLinesRecyclerView);
        setCompletedButton  = findViewById(R.id.SetCompletedButton);

        //changeLayout depending on who is using it(chef or customer)
        viewModel.getPresenter().chooseLayout(isCustomer);

        findViewById(R.id.SetCompletedButton).setOnClickListener(new View.OnClickListener() { //Το κουμπί που πατιέται όταν μια παραγγελία πατηθεί ότι είναι completed
            public void onClick(View v) {
                viewModel.getPresenter().onCompleted();
            }
        });

        findViewById(R.id.GoBack3).setOnClickListener(new View.OnClickListener(){ // To κουμπί επιστροφής στην αρχική οθόνη
            @Override
            public void onClick(View v){viewModel.getPresenter().OnBack();}
        });
    }

    /**
     *This method displays a success message on the screen and is called when the order is marked as Completed by the chef.
     *Additionally, it creates an onClick listener that, when the OK button is pressed on the screen, returns us to the previous activity that invoked us.
     */
    public void showOrderCompletedMessage()
    {
        new AlertDialog.Builder(OrderDetailsActivity.this)
                .setCancelable(true)
                .setTitle("Successful completion of the order.")
                .setMessage("The order has been added to the list of completed orders!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).create().show();
    }
    /**
     * Hides the SetCompletedButton button which is for cases when the activity is called by the customer.
     */
    @Override
    public void hideCompletionButton() {
        setCompletedButton.setVisibility(View.GONE);
    }

    /**
     * Displays the SetCompletedButton button which is for cases of the chef, allowing them to mark the order as completed.
     */
    @Override
    public void showCompletedButton() {
        setCompletedButton.setVisibility(View.VISIBLE);
    }
    /**
     *Called when returning to the screen of this activity.
     *Updates the list of Order Lines in case a new one has been added to be displayed in the Recycler View, as well as the adapter of the recycler view.
     *Calls the changeLayout method of the presenter.
     */
    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getPresenter().setOrderLineList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderDetailsRecyclerViewAdapter(viewModel.getPresenter().getOrderLineList()));
    }

    /**
     *Displays the ID of the order that has been clicked on the screen.
     *@param orderId The unique ID of the order.
     */
    public void setOrderId(String orderId){
        ((TextView)findViewById(R.id.OrderIdText)).setText("Id:"+orderId);
    }

    /**
     *Displays the status of the currently selected order on the screen.
     *@param state The status of the order.
     */
    public void setOrderState(String state){
        ((TextView)findViewById(R.id.OrderStateText)).setText("State:"+state);
    }

    /**
     *Displays the table number where the order was placed on the screen.
     *@param num The table number.
     */
    public void setTableNumber(String num){
        ((TextView)findViewById(R.id.TableNumberText)).setText("Table:"+num);
    }

    /**
     *Displays the hour and minute when the order was placed on the screen.
     *@param date The time in hour and minute format as a concatenated String.
     */
    public void setDate(String date){
        ((TextView)findViewById(R.id.DateText)).setText(date);
    }
    /**
     * Called when we want to return to the previous Activity, which in our case is the login page (our activity calls this).
     *
     */
    public void goBack(){
        finish();
    }


}