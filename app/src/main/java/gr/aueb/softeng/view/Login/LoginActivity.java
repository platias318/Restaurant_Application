package gr.aueb.softeng.view.Login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import gr.aueb.softeng.memoryDao.MemoryInitializer;
import gr.aueb.softeng.team08.R;
import gr.aueb.softeng.view.Chef.HomePage.ChefHomePageActivity;
import gr.aueb.softeng.view.Customer.ChooseRestaurant.ChooseRestaurantActivity;
import gr.aueb.softeng.view.Owner.HomePage.OwnerHomePageActivity;
import gr.aueb.softeng.view.SignUp.SignUpCustomer.SignUpCustomerActivity;
import gr.aueb.softeng.view.SignUp.SignUpOwner.SignUpOwnerActivity;
import gr.aueb.softeng.view.SignUp.SignUpPersonel.SignUpPersonelActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {
    /**
     * It displays an alert message with
     * the title "title" and the message "message."
     * @param title The title of the message
     * @param message The content of the message
     */
    public void showErrorMessage(String title, String message)
    {
        new AlertDialog.Builder(LoginActivity.this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null).create().show();
    }
    /**
     * It displays a success message when the customer successfully logs into their account
     * and is directed to the Home Page activity when the "OK" button is pressed.
     */
    public void showCustomerFoundMessage(int id)
    {
        new AlertDialog.Builder(LoginActivity.this)
                .setCancelable(true)
                .setTitle("Congratulations")
                .setMessage("The details you provided are correct")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        redirectToCustomerPage(id);
                    }
                }).create().show();
    }
    /**
     * It displays a success message when the customer successfully logs into their account
     * and is directed to the Home Page activity when the "OK" button is pressed.
     */
    public void showOwnerFoundMessage(int id)
    {
        new AlertDialog.Builder(LoginActivity.this)
                .setCancelable(true)
                .setTitle("Congratulations")
                .setMessage("The details you provided are correct")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        redirectToOwnerHomePage(id);
                    }
                }).create().show();
    }
    /**
     * It displays a success message when the chef successfully logs into their account
     * and is directed to the Home Page activity when the "OK" button is pressed.
     */

    public void showChefFoundMessage(int id)
    {
        new AlertDialog.Builder(LoginActivity.this)
                .setCancelable(true)
                .setTitle("Congratulations")
                .setMessage("The details you provided are correct")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        redirectToChefHomePage(id);
                    }
                }).create().show();
    }
    private LoginViewModel viewModel;

    private static boolean initialized = false;
    /**
     It creates the layout and initializes the activity.
     * We initialize the view Model and pass the view to the presenter.
     * We call the corresponding activities when the screen buttons are pressed.
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, then this Bundle contains the data it most
     * recently supplied in {@link #onSaveInstanceState}. <b><i>Note: Otherwise, it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);

        //USED FOR DEBUGGING
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        //REMOVE LATER

        LoginViewModel viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.getPresenter().setView(this);
        if (savedInstanceState == null){
            Intent intent = getIntent();

        }

        findViewById(R.id.SignUpCustomerButton).setOnClickListener(new View.OnClickListener() { //το κουμπί όταν θέλει να εγγραφτεί νέος πελάτης στην εφαμοργή
            public void onClick(View v) {
                viewModel.getPresenter().onSignup();
            }
        });

        findViewById(R.id.SignUpPersonelButton).setOnClickListener(new View.OnClickListener() { // το κουμπί όταν θέλει να εγγραφτεί νέος μάγειρας στην εφαμοργή
            public void onClick(View v) {
                viewModel.getPresenter().onSignupPersonel();
            }
        });

        findViewById(R.id.SignUpOwnerButton).setOnClickListener(new View.OnClickListener() {// το κουμπί όταν θέλει να εγγραφτεί νέος ιδιοκτήτης στην εφαρμογή
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().onSignupOwner();
            }
        });

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() { // το κουμπί σύνδεσης στην εφαρμογή
            @Override
            public void onClick(View v) {
                viewModel.getPresenter().authenticate();

            }
        });
    }

    /**
     *This method receives the name that the user has typed in the Username field
     * @return Returns this string
     */

    public String ExtractUsername()
    {
      return ((EditText)findViewById(R.id.usernameText)).getText().toString().trim();

    }

    /**
     *This method receives the password that the user has typed in the PassWord field
     * @return Returns this string
     */
    public String ExtractPassword()
    {
        return ((EditText)findViewById(R.id.passwordText)).getText().toString().trim();
    }

    /**
     *This method gets called when the customer register button gets pressed
     */
    public void signup(){
        Intent intent = new Intent(LoginActivity.this, SignUpCustomerActivity.class);
        startActivity(intent);
    }
    /**
     *This method get called when the chef register button gets pressed
     */
    public void signupPersonel() {
        Intent intent = new Intent(LoginActivity.this, SignUpPersonelActivity.class);
        startActivity(intent);
    }
    /**
     *his method gets called when the owner register button gets pressed
     */
    public void signupOwner() {
        Intent intent = new Intent(LoginActivity.this, SignUpOwnerActivity.class);
        startActivity(intent);
    }

    /**
     * This method redirects the user to the customer page if their credentials have been correctly authenticated.
     * Additionally, it passes the customer's ID as extra information to the intent.
     * @param customerId The ID of the customer who clicked the login button.
     */

    public void redirectToCustomerPage(int customerId){
        Intent intent = new Intent(LoginActivity.this, ChooseRestaurantActivity.class);
        intent.putExtra("CustomerId",customerId);
        startActivity(intent);
    }
    /**
     * This method redirects the user to the chef's page if their credentials have been correctly authenticated.
     * Additionally, it passes the chef's ID as extra information to the intent.
     * @param chefId The ID of the chef who clicked the login button.
     */
    public void redirectToChefHomePage(int chefId){
            Intent intent = new Intent(LoginActivity.this, ChefHomePageActivity.class);
            intent.putExtra("ChefId",chefId);
            startActivity(intent);
    }
    /**
     *This method redirects the user to the owner's page if their credentials have been correctly authenticated.
     * Additionally, it passes the owner's ID as extra information to the intent.
     * @param ownerId The ID of the owner who clicked the login button.
     */
    public void redirectToOwnerHomePage(int ownerId){
        Intent intent = new Intent(LoginActivity.this, OwnerHomePageActivity.class);
        intent.putExtra("OwnerId",ownerId);
        startActivity(intent);
    }

}