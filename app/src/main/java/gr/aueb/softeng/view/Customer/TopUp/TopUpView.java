package gr.aueb.softeng.view.Customer.TopUp;

import gr.aueb.softeng.view.View;

public interface TopUpView extends View {


    /**
     *We set the textView so that is shows the required money
     */
    void setBalance(String balance);

    int getCustomerId();
}
