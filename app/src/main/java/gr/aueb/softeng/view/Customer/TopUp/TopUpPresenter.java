package gr.aueb.softeng.view.Customer.TopUp;


import gr.aueb.softeng.dao.CustomerDAO;
import gr.aueb.softeng.domain.Customer;

public class TopUpPresenter {
    private TopUpView view;
    private CustomerDAO customerDAO;
    private Customer customer;
    public TopUpPresenter(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void setView(TopUpView view) {
        this.view = view;
    }

    public TopUpView getView() {
        return view;
    }

    public void setCustomer()
    {
        customer = customerDAO.find(view.getCustomerId());
    }

    public Customer getCustomer() {
        return customer;
    }


    /**
     * If the customer's instance is not null, we display their balance,
     * otherwise, we show an error message.
     */
    public void setLayout() {
        if (customer!=null)
        {
            String balance = String.format("%.2f",customer.getBalance());
            view.setBalance(balance + " €");
        }
        else
        {
            view.setBalance("ERROR");
        }

    }

    /**
     * If the customer's instance is not null,
     * we add a monetary amount to it and
     * call SetLayout() to update
     * the displayed balance on the screen.
     */

    public void onTopUp(double amount) {
        if(customer!=null) {
            customer.topUp(amount);
            setLayout();
        }
    }
}
