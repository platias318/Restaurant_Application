package gr.aueb.softeng.view.Owner.RestaurantDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;



import gr.aueb.softeng.team08.R;

import gr.aueb.softeng.view.Owner.AddChef.AddChefActivity;
import gr.aueb.softeng.view.Owner.Statistics.StatisticsActivity;

/**
 * This class is called when a restaurant is clicked by the owner in order to display its details.
 */
public class RestaurantDetailsActivity extends AppCompatActivity implements RestaurantDetailsView {
    public int RestaurantId;
    RestaurantDetailsViewModel viewModel;

    /**
     * It displays the name of the selected restaurant on the screen.
     * @param name The name to be displayed.
     */
    public void setRestName(String name){
        ((TextView)findViewById(R.id.RestaurantNameDetailText)).setText(name);
    }

    /**
     * It displays the ID of the selected restaurant on the screen.
     * @param id The ID to be displayed.
     */
    public void setRestId(String id){
        ((TextView)findViewById(R.id.RestaurantIdDetailText)).setText(id);
    }

    /**
     * It displays the number of tables of the selected restaurant on the screen.
     * @param tables The number of tables in String format to be displayed.
     */
    public void setRestTables(String tables){
        ((TextView)findViewById(R.id.RestaurantTablesDetailText)).setText(tables);
    }

    /***
     * It displays the name of the street of the selected restaurant on the screen.
     * @param street The street name to be displayed.
     */
    public void setRestAddressStreet(String street){
        ((TextView)findViewById(R.id.RestaurantAddressStreetText)).setText(street);
    }

    /**
     * It displays the number of the street of the selected restaurant on the screen.
     * @param num The number to be displayed.
     */
    public void setRestAddressNumber(String num){
        ((TextView)findViewById(R.id.RestaurantAddressNumberText)).setText(num);
    }

    /**
     * It displays the postal code of the selected restaurant on the screen.
     * @param zip The postal code to be displayed.
     */
    public void setRestZip(String zip){
        ((TextView)findViewById(R.id.RestaurantAddressZCText)).setText(zip);
    }

    /**
     * It displays the city of the selected restaurant on the screen.
     * @param city The city to be displayed.
     */
    public void setRestAddressCity(String city){
        ((TextView)findViewById(R.id.RestaurantCityText)).setText(city);
    }

    /**
     * It displays an alert message with
     * the title "title" and the message "message".
     * @param title The title of the message
     * @param message The content of the message.
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(RestaurantDetailsActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    private static boolean initialized = false;

    /**
     *Creates the layout and initializes the activity.
     *Initializes the view model and passes the view to the presenter.
     *Retrieves the ID of the restaurant we want to display details for from the calling activity.
     *Handles button clicks on the screen by calling the appropriate activities.
     *If the activity is being re-initialized after being shut down, the savedInstanceState Bundle
     *contains the most recent data supplied in onSaveInstanceState, otherwise, it's null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_restaurant_details);
        viewModel = new ViewModelProvider(this).get(RestaurantDetailsViewModel.class);
        viewModel.getPresenter().setView(this);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            RestaurantId = extras.getInt("RestaurantId");
        }
        viewModel.getPresenter().setRestaurant(RestaurantId);
        viewModel.getPresenter().setDetails();

        findViewById(R.id.ExtractStatsButton).setOnClickListener(new View.OnClickListener() {//Όταν πατηθεί το κουμπί εξαγωγής στατιστικών
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onExtractStats();
            }
        });
        findViewById(R.id.GoBack2).setOnClickListener(new View.OnClickListener(){ // Όταν πατηθεί το κουμπί επιστροφής στην αρχική σελίδα
            @Override
            public void onClick(View v){viewModel.getPresenter().OnBack();}
        });
        findViewById(R.id.addChefButton).setOnClickListener(new View.OnClickListener(){ //Όταν πατηθεί το κουμπί προσθήκης νέου μάγειρα στο εστιατόριο
            @Override
            public void onClick(View v){viewModel.getPresenter().onAddChef();}
        });
    }

    /**
     * It gets called s othat we return to the previous activity
     */
    public void goBack(){
        finish();
    }

    /**
     * It's called when the statistics export button is pressed, and it calculates the statistics for the restaurant.
     */

    public void extractStats(){
        Intent intent = new Intent(RestaurantDetailsActivity.this, StatisticsActivity.class);
        intent.putExtra("RestaurantId",RestaurantId);
        startActivity(intent);
    }

    /**
     * It's called when the button for adding a new chef to the restaurant is pressed, and it passes the ID of the restaurant.
     */
    public void addChef(){
        Intent intent = new Intent(RestaurantDetailsActivity.this, AddChefActivity.class);
        intent.putExtra("RestaurantId",RestaurantId);
        startActivity(intent);
    }
}