package gr.aueb.softeng.view.SignUp.SignUpCustomer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import java.util.HashMap;


import gr.aueb.softeng.team08.R;

/**
 * This class is called when the "Add New Customer" button is clicked to add a new customer to the system.
 */
public class SignUpCustomerActivity extends AppCompatActivity implements SignUpCustomerView {
    /**
     * Displays an alert message with the given title and message content.
     * @param title The title of the message
     * @param message The content of the message
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(SignUpCustomerActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }
    /**
     *
     * Displays a success message when the customer successfully creates their account and returns
     * the previous activity when the OK button is pressed.
     */
    public void showAccountCreatedMessage()
    {
        new AlertDialog.Builder(SignUpCustomerActivity.this)
                .setCancelable(true)
                .setTitle("Successful account creation.")
                .setMessage("The account has been successfully created")
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
     *
     * Creates the layout and initializes the activity. We initialize the view model and pass
     * the view to the presenter. We invoke activities when the buttons on the screen are pressed.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_sign_up);

        SignUpCustomerViewModel viewModel = new ViewModelProvider(this).get(SignUpCustomerViewModel.class);
        viewModel.getPresenter().setView(this);
        if (savedInstanceState == null){
            Intent intent = getIntent();

        }
        findViewById(R.id.CreateAccButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onCreateAccount();//μέθοδος που καλείται όταν είναι να δημιουργηθεί το account του χρήστη
            }

        });
        findViewById(R.id.gobackButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onBack();
            } // μέθοδος επιστροφής στην προηγούμενη οθόνη
        });
    }
    /**
     *
     * Creates a hash map where the keys are descriptions such as "username" or "phone number" of the customer's registration details, and the values are the corresponding
     * values entered by the customer on the screen.
     * @returns this hash map containing the screen's data.
     */
    public HashMap<String,String> getDetails(){
        HashMap<String,String> details = new HashMap<>();
        details.put("name",(((EditText)findViewById(R.id.CustomerNameText)).getText().toString().trim()));
        details.put("surname",(((EditText)findViewById(R.id.CustomerSurnameText)).getText().toString().trim()));
        details.put("username",(((EditText)findViewById(R.id.CustomerUsernameText)).getText().toString().trim()));
        details.put("email",(((EditText)findViewById(R.id.CustomerEmailText)).getText().toString().trim()));
        details.put("telephone",(((EditText)findViewById(R.id.CustomerTelephoneText)).getText().toString().trim()));
        details.put("cardNumber",(((EditText)findViewById(R.id.CardNumberText)).getText().toString().trim()));
        details.put("cardHolderName",(((EditText)findViewById(R.id.CardHolderNameText)).getText().toString().trim()));
        details.put("cvv",(((EditText)findViewById(R.id.CVVText)).getText().toString().trim()));
        details.put("password",(((EditText)findViewById(R.id.CustomerPasswordText)).getText().toString().trim()));
        return details;
    }

    /**
     * Called to return to the previous Activity.
     */
    public void goBack(){
        finish();
    }


}