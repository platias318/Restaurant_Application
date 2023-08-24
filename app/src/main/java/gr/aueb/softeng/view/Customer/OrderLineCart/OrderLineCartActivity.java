package gr.aueb.softeng.view.Customer.OrderLineCart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import java.util.ArrayList;

import gr.aueb.softeng.domain.OrderLine;
import gr.aueb.softeng.team08.R;

/**
 *In this page the user can remove an orderLine that he doesnt want
 */
public class OrderLineCartActivity extends AppCompatActivity implements OrderLineCartView,OrderLineSelectionListener {

    public ArrayList<OrderLine> orderLines;
    RecyclerView recyclerView;
    OrderLineCartViewModel viewModel;

    /**
     *It creates the layout and initializes the activity
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_order_line_cart);

        viewModel = new ViewModelProvider(this).get(OrderLineCartViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            orderLines = (ArrayList<OrderLine>) extras.getSerializable("OrderLines");
        }
            viewModel.getPresenter().setOrder(orderLines);
            recyclerView = findViewById(R.id.CartRecyclerView);
            setRecyclerView();
    }


    /**
     *It deletes the OrderLine using the presenter method
     */
    @Override
    public void deleteOrderLine(OrderLine currentItem) {
        viewModel.getPresenter().onDeleteOrderLine(currentItem);
    }

    /**
     * When we press the back button , we transfer the changes into the orderLines using the result
     */
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent();
        intent.putExtra("ModifiedOrderLines", viewModel.getPresenter().getOrderLines());// Add other data to the intent if needed
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }

    /**
     * It is used every time an orderLine is deleted to refresh the list's items.
     */
    @Override
    public void setRecyclerView() {
        recyclerView.setLayoutManager(null);
        recyclerView.setAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OrderLineCartRecyclerViewAdapter(viewModel.getPresenter().getOrderLines(),this));
    }
}