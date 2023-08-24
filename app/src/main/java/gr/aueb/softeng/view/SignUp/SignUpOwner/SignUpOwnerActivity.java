package gr.aueb.softeng.view.SignUp.SignUpOwner;

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
 * This class is called when attempting to add a new owner to the application.
 */
public class SignUpOwnerActivity extends AppCompatActivity implements SignUpOwnerView {
    /**
     * Displays an alert message with a title and message.
     * @param title The title of the message
     * @param message The content of the message
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(SignUpOwnerActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }
    /**
     * Displays a success message when the owner successfully creates their account
     * and returns to the previous activity when the OK button is pressed
     */
    @Override
    public void showAccountCreatedMessage()
    {
        new AlertDialog.Builder(SignUpOwnerActivity.this)
                .setCancelable(true)
                .setTitle("Successful account creation")
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
     *Creates the layout and initializes the activity
     *Initializes the view model and passes the view to the presenter
     *Calls activities when the screen buttons are pressed
     (@param savedInstanceState If the activity is being re-initialized after
     (previously being shut down then this Bundle contains the data it most
     (recently supplied in {@link #onSaveInstanceState}. <b><i>Note: Otherwise it is null.</i></b>
     *@Override
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_sign_up_owner);

        SignUpOwnerViewModel viewModel = new ViewModelProvider(this).get(SignUpOwnerViewModel.class);
        viewModel.getPresenter().setView(this);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            }

        findViewById(R.id.CreateAccOwnerButton).setOnClickListener(new View.OnClickListener(){ // το κουμπί για να δημιουργηθεί ο λογαριασμός
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onCreateOwnerAccount();
            }

        });
        findViewById(R.id.gobackButton2).setOnClickListener(new View.OnClickListener(){// το κουμπί για να επιστρέψει πίσω
            @Override
            public void onClick(View v){
                viewModel.getPresenter().onBack();
            }
        });
    }
    /**
     * Creates a hash map in which we use the description (e.g., "username" or "phone number") as the key
     * and the value is the corresponding value obtained from the screen entered by the owner.
     * @return Returns this hash map containing the screen's data.
     */
    public HashMap<String,String> getOwnerDetails(){
        HashMap<String,String> details = new HashMap<>();
        details.put("name",(((EditText)findViewById(R.id.OwnerNameText)).getText().toString().trim()));
        details.put("surname",(((EditText)findViewById(R.id.OwnerSurnameText)).getText().toString().trim()));
        details.put("username",(((EditText)findViewById(R.id.OwnerUsernameText)).getText().toString().trim()));
        details.put("email",(((EditText)findViewById(R.id.OwnerEmailText)).getText().toString().trim()));
        details.put("telephone",(((EditText)findViewById(R.id.OwnerTelephoneText)).getText().toString().trim()));
        details.put("iban",(((EditText)findViewById(R.id.OwnerIbanText)).getText().toString().trim()));
        details.put("tin",(((EditText)findViewById(R.id.OwnerTinText)).getText().toString().trim()));
        details.put("password",(((EditText)findViewById(R.id.OwnerPasswordText)).getText().toString().trim()));
        return details;
    }
    /**
     * Called to return to the previous Activity.
     */
    public void goBack(){
        finish();
    }


}