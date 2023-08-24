package gr.aueb.softeng.view.Owner.AddChef;

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
 *This class gets called when a chef has to be added to a restaurant
 */
public class AddChefActivity extends AppCompatActivity implements AddChefView {

    public int restId;
    /**
     * Displays an alert message with
     * the title "title" and the message "message."
     * @param title The title of the message
     * @param message The content of the message
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(AddChefActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }

    /**
     * Displays a success message when the owner successfully adds the chef
     * and returns to the previous activity when the "OK" button is pressed.
     */
    public void showChefAddedMessage()
    {
        new AlertDialog.Builder(AddChefActivity.this)
                .setCancelable(true)
                .setTitle("Successful addition of the chef!")
                .setMessage("The provided details have been confirmed, and the chef \n has been successfully added to the restaurant.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).create().show();
    }

    private static boolean initialized = false;

    /**
     * Creates the layout and initializes the activity.
     * We initialize the view Model and pass the view to the presenter.
     * We retrieve the restaurant's ID from the activity that called us to add the chef.
     * We call the corresponding activities when the screen buttons are pressed.
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, then this Bundle contains the data it most
     * recently supplied in {@link #onSaveInstanceState}. <b><i>Note: Otherwise, it is null.</i></b>
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_add_chef);

        AddChefViewModel viewModel = new ViewModelProvider(this).get(AddChefViewModel.class);
        viewModel.getPresenter().setView(this);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            restId = extras.getInt("RestaurantId");
        }
        viewModel.getPresenter().setRestaurant(restId);

        findViewById(R.id.confirmChefButton1).setOnClickListener(new View.OnClickListener(){ //Όταν πατηθεί το κουμπί επιβεβαίωσης προσθήκης μάγειρσ
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onAddChefAccount();
            }

        });
        findViewById(R.id.gobackButton7).setOnClickListener(new View.OnClickListener(){ // Όταν πατηθεί το κουμπί της επιστροφής
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onBack();
            }
        });

    }
    /**
     * Creates a hash map where the keys are descriptions, like "username" or "phone number," of the chef's attributes,
     * and the values are the corresponding values of those attributes retrieved from the screen where the owner has entered
     * the chef's details.
     * @return We return this hash map containing the data entered on the screen.
     */
    public HashMap<String,String> getChefDetails(){
        HashMap<String,String> details = new HashMap<>();
        details.put("name",(((EditText)findViewById(R.id.ChefNameText1)).getText().toString().trim()));
        details.put("surname",(((EditText)findViewById(R.id.ChefSurnameText1)).getText().toString().trim()));
        details.put("username",(((EditText)findViewById(R.id.ChefUsernameText1)).getText().toString().trim()));
        details.put("telephone",(((EditText)findViewById(R.id.ChefTelephoneText1)).getText().toString().trim()));
        details.put("iban",(((EditText)findViewById(R.id.ChefIbanText1)).getText().toString().trim()));
        details.put("tin",(((EditText)findViewById(R.id.ChefTinText1)).getText().toString().trim()));
        return details;
    }
    /**
     *This method gets calls when we need to go back to the previous activity
     */
    public void goBack(){
        finish();
    }
}