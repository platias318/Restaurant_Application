package gr.aueb.softeng.view.Owner.AddRestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

import gr.aueb.softeng.team08.R;

/**
 *This class gets called when the owner wants to add a new restaurant to the system
 */
public class AddRestaurantActivity extends AppCompatActivity implements AddRestaurantView {
    /**
     * It displays an alert-type message with:
     * title as the title and message as the message.
     * @param title The title of the message.
     * @param message The content of the message.
     */
    public void showErrorMessage(String title, String message) {
        new AlertDialog.Builder(AddRestaurantActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * It displays a success message when the property successfully adds the new restaurant
     * and returns to the previous activity when the OK button is pressed.
     */
    public void showRestaurantAddedMessage()
    {
        new AlertDialog.Builder(AddRestaurantActivity.this)
                .setCancelable(true)
                .setTitle("Successful restaurant addition.")
                .setMessage("The restaurant has been successfully added to the owner's list.!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).create().show();
    }

    private static boolean initialized = false;
    private int ownerId;
    /**
     * It creates the layout and initializes the activity.
     * We initialize the View Model and pass the view to the presenter.
     * We retrieve the owner's ID we want to add to the restaurant from the activity that called us.
     * We invoke the activities when the screen buttons are pressed.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, then this Bundle contains the
     * data it most recently supplied in {@link #onSaveInstanceState}. <b><i>Note: Otherwise, it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_add_restaurant);
        AddRestaurantViewModel viewModel = new ViewModelProvider(this).get(AddRestaurantViewModel.class);
        viewModel.getPresenter().setView(this);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            ownerId = extras.getInt("OwnerId");
        }
        viewModel.getPresenter().setOwner(ownerId);

        findViewById(R.id.CreateRestaurantButton).setOnClickListener(new View.OnClickListener(){ // Όταν πατηθεί το κουμπί δημιουργίας του εστιατορίου
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onCreateRestaurant();
            }

        });
        findViewById(R.id.gobackButton8).setOnClickListener(new View.OnClickListener(){ // Όταν πατηθεί το κουμπί επιστροφής στην προηγούμενη σελίδα
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onBack();
            }
        });
    }

    /**
     * It creates a hash map in which we have the description as the key, for example, if it's "name" or the restaurant's phone number,
     * and the value is the value of the key, which we take from the screen that the owner has entered
     * the restaurant's details to be added.
     * @return We return this hash map with the screen's data.
     */
    public HashMap<String,String> getRestDetails(){
        HashMap<String,String> details = new HashMap<>();
        details.put("name",(((EditText)findViewById(R.id.RestaurantNameText)).getText().toString().trim()));
        details.put("telephone",(((EditText)findViewById(R.id.RestaurantTelephoneText)).getText().toString().trim()));
        details.put("streetName",(((EditText)findViewById(R.id.StreetNameText)).getText().toString().trim()));
        details.put("streetNumber",(((EditText)findViewById(R.id.StreetNumberText)).getText().toString().trim()));
        details.put("zc",(((EditText)findViewById(R.id.ZcText)).getText().toString().trim()));
        details.put("city",(((EditText)findViewById(R.id.CityText)).getText().toString().trim()));
        details.put("total_tables",(((EditText)findViewById(R.id.TotalTablesText)).getText().toString().trim()));
        return details;
    }
    /**
     * It gets called when we want to return to the previous activity
     */
    public void goBack(){
        finish();
    }
}