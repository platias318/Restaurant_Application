package gr.aueb.softeng.view.Owner.Statistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import gr.aueb.softeng.team08.R;

/**
 * This class displays on the screen the statistics of the selected restaurant.
 */
public class StatisticsActivity extends AppCompatActivity implements StatisticsView {
    StatisticsViewModel viewModel;
    public int RestaurantId;

    /**
     * It displays the restaurant's average monthly income on the screen using the provided
     * @param monthly income value as a String.
     */
    public void setAVGMonthlyIncome(String monthlyIncome){
        ((TextView)findViewById(R.id.AvgMonthlyIncomeResult)).setText(monthlyIncome);
    }

    /**
     * It displays the restaurant's annual income on the screen.
     * @param It displays the calculated annual income of the restaurant as a String on the screen.
     */
    public void setYearlyIncome(String yearlyIncome){
        ((TextView)findViewById(R.id.YearlyIncomeResult)).setText(yearlyIncome);
    }

    /**
     * It displays the expenses per customer on the screen.
     * @param It displays the calculated expenses per customer in the form of a String.
     */
    public void setAvgOrderExpenses(String orderExpenses){
        ((TextView)findViewById(R.id.AverageExpensesForCustomerResult)).setText(orderExpenses);
    }

    /**
     * It displays the calculated average daily expenses on the screen.
     * @param It displays the calculated amount of average daily expenses (revenue) in String format on the screen.
     */
    public void setAVGDailyRevenue(String revenue){
        ((TextView)findViewById(R.id.AverageDailyRevenueResult)).setText(revenue);
    }

    /**
     * It displays the percentage of canceled orders on the screen.
     * @param canc is the calculated percentage of canceled orders in string format.
     */
    public void setOrderCancellationRate(String canc){
        ((TextView)findViewById(R.id.OrderCancellationRateResult)).setText(canc + " %");
    }

    /**
     * Displays an alert message with the specified title and message.
     * @param title The title of the message
     * @param message The content of the message
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(StatisticsActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }
    private static boolean initialized = false;

    /**
     * Creates the layout and initializes the activity.
     * Initializes the ViewModel and passes the view to the Presenter.
     * Retrieves the restaurant id that needs to be displayed from the calling activity.
     * Sets up button click listeners and calls corresponding activities.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_statistics);
        viewModel = new ViewModelProvider(this).get(StatisticsViewModel.class);
        viewModel.getPresenter().setView(this);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            RestaurantId = extras.getInt("RestaurantId");
        }
        viewModel.getPresenter().setRestaurant(RestaurantId);
        viewModel.getPresenter().calculateStats();
        findViewById(R.id.GoBack).setOnClickListener(new View.OnClickListener(){ // Όταν πατηθεί το κουμπί επιστροφής στην αρχική σελίδα
            @Override
            public void onClick(View v){viewModel.getPresenter().OnBack();}
        });
    }

    /**
     * It gets called so that we return to the previous activity
     */
    public void goBack(){
        finish();
    }
}