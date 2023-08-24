package gr.aueb.softeng.view.Customer.TopUp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import gr.aueb.softeng.memoryDao.CustomerDAOmemory;
import gr.aueb.softeng.team08.R;

/**
 *In this page the user can see and renew his balance
 */

public class TopUpActivity extends AppCompatActivity implements TopUpView{
private int customerId = -1;
private TopUpViewModel viewModel;
private TextView balanceText;
    /**
     * It creates the layout and initializes the activity
     * @param savedInstanceState the instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_top_up);


        viewModel = new TopUpViewModel(new CustomerDAOmemory());
        viewModel.getPresenter().setView(this);
        balanceText = findViewById(R.id.BalanceText);

        if (savedInstanceState == null)
        {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();

            if (extras != null) {
                customerId = extras.getInt("CustomerId");
            }

        }
        viewModel.getPresenter().setCustomer();
        viewModel.getPresenter().setLayout();

        findViewById(R.id.AddTenEuroButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onTopUp(10.0);
            }
        });
        findViewById(R.id.AddTwentyEuroButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onTopUp(20.0);
            }
        });
        findViewById(R.id.AddFiftyEuroButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onTopUp(50.0);
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    /**
     *We set the textView so that is shows the required balance
     */
    @Override
    public void setBalance(String balance) {
       balanceText.setText(balance);
    }

    /**
     * @return the customer id
     */
    public int getCustomerId() {
        return customerId;
    }
}