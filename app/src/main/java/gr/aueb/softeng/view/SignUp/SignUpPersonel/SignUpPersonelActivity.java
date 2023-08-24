package gr.aueb.softeng.view.SignUp.SignUpPersonel;

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
 *This class is called when attempting to add a new chef to the application.
 */
public class SignUpPersonelActivity extends AppCompatActivity implements SignUpPersonelView {
    /**
     * Displays an alert message with
     * * title title and message message.
     * @param title The title of the message
     * @param message The content of the message
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(SignUpPersonelActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }
    /**
     * Displays a success message when the cook successfully creates their account
     * and returns to the previous activity when the OK button is pressed
     */
    @Override
    public void showAccountCreatedMessage()
    {
        new AlertDialog.Builder(SignUpPersonelActivity.this)
                .setCancelable(true)
                .setTitle("Successful account creation.")
                .setMessage("The account has been successfully created.")
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
     The translation of the sentences you provided from Greek to English is: “Creates the layout and initializes the activity
     We initialize the view Model and pass the view to the presenter
     We call the activities when the screen buttons are pressed
     @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it
     most recently supplied in {@link #onSaveInstanceState}. <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_sign_up_personel);

        SignUpPersonelViewModel viewModel = new ViewModelProvider(this).get(SignUpPersonelViewModel.class);
        viewModel.getPresenter().setView(this);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
        }
        findViewById(R.id.createAccButton).setOnClickListener(new View.OnClickListener(){ // το κουμπί δημιουργίας του account
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onCreateChefAccount();
            }

        });
        findViewById(R.id.gobackButton3).setOnClickListener(new View.OnClickListener(){ // το κουμπί επιστροφής στην προηγόυμενη οθόνη
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onBack();
            }
        });
    }
    /**
     * The translation of the sentences you provided from Greek to English is: “Creates a hash map in which we have as a key the description, for example, if it is the cook’s username or phone
     * and as value we have the value of the key which we get from the screen where the cook has entered their registration details
     * @return We return this Hash Map with the data of the screen.
     */
    public HashMap<String,String> getChefDetails(){
        HashMap<String,String> details = new HashMap<>();
        details.put("name",(((EditText)findViewById(R.id.ChefNameText)).getText().toString().trim()));
        details.put("surname",(((EditText)findViewById(R.id.ChefSurnameText)).getText().toString().trim()));
        details.put("username",(((EditText)findViewById(R.id.ChefUsernameText)).getText().toString().trim()));
        details.put("email",(((EditText)findViewById(R.id.ChefEmailText)).getText().toString().trim()));
        details.put("telephone",(((EditText)findViewById(R.id.ChefTelephoneText)).getText().toString().trim()));
        details.put("iban",(((EditText)findViewById(R.id.ChefIbanText)).getText().toString().trim()));
        details.put("tin",(((EditText)findViewById(R.id.ChefTinText)).getText().toString().trim()));
        details.put("password",(((EditText)findViewById(R.id.ChefPasswordText)).getText().toString().trim()));
        return details;
    }
    /**
     * Called to return to the previous Activity
     */
    public void goBack(){
        finish();
    }




}