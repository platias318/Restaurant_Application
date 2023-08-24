package gr.aueb.softeng.view.Customer.HomePage;

import android.view.View;

import androidx.fragment.app.Fragment;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import gr.aueb.softeng.dao.ChefDAO;
import gr.aueb.softeng.dao.CustomerDAO;
import gr.aueb.softeng.dao.OrderDAO;
import gr.aueb.softeng.dao.RestaurantDAO;
import gr.aueb.softeng.domain.Customer;
import gr.aueb.softeng.domain.Order;
import gr.aueb.softeng.domain.Restaurant;

public class CustomerHomepagePresenter {
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    private RestaurantDAO restaurantDAO;
    private CustomerHomepageView view;
    private Order currentOrder;
    private Customer customer;
    private Restaurant restaurant;

    private ArrayList<Order> orderHistory;

    /**
     * Initializes the Presenter in order to
     * perform necessary customer-related operations.
     * @param customerDAO an object of type CustomerDAO
     * @param orderDAO an object of type OrderDAO
     * @param restaurantDAO an object of type RestaurantDAO
     */
    public CustomerHomepagePresenter(CustomerDAO customerDAO, OrderDAO orderDAO,RestaurantDAO restaurantDAO)
    {
        this.customerDAO = customerDAO;
        this.orderDAO = orderDAO;
        this.restaurantDAO = restaurantDAO;
        orderHistory = new ArrayList<>();
    }

    /**
     * Sets the restaurant variable
     * by retrieving it from RestaurantDAO
     * using the given id.
     * @param restaurantId the id of the restaurant
     */
    public void setRestaurant(int restaurantId) {
        restaurant = restaurantDAO.find(restaurantId);
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }
    /**
     * Sets the customer variable
     * by retrieving it from CustomerDAO
     * using the given id.
     * @param id the id of the customer
     */
    public void setCustomer(int id) {
        this.customer = customerDAO.find(id);
    }

    /**
     * Sets the orderHistory list
     * by retrieving completed or cancelled orders
     * from orderDAO for the set customer
     * and adding them to the orderHistory list.
     */
    public void setOrderHistory()
    {
        orderHistory = new ArrayList<>();
        ArrayList<Order> orders = (ArrayList<Order>) orderDAO.findByCustomer(customer);
        if (customer!=null)
        {
            for (Order order : orders)
            {
                if (order.getOrderState() == Order.State.COMPLETED || order.getOrderState() == Order.State.CANCELLED ||!orders.contains(order))
                {
                    orderHistory.add(order);
                }
            }
        }
    }

    /**
     * Sets the currentOrder variable
     * with the order of the set customer
     * that is not completed or cancelled, if it exists.
     * (There cannot be more than one such order.)
     */
    public void setCurrentOrder()
    {
        ArrayList<Order> orders = (ArrayList<Order>) orderDAO.findByCustomer(customer);
        currentOrder = null;
        if (orders!=null) {
            for (Order order : orders) {
                if (order.getOrderState() != Order.State.COMPLETED && order.getOrderState() != Order.State.CANCELLED) {
                    currentOrder = order;
                }
            }
        }
    }
    public Order getCurrentOrder()
    {
        return currentOrder;
    }

    /**
     * Returns the customer.
     * @return The customer object of type Customer.
     */
    public Customer getCustomer() {
        return customer;
    }


    /**
     * Returns the orderHistory list.
     * @return An ArrayList<Order> containing past orders.
     */
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
    /**
     * Sets the view instance for the CustomerHomepageView interface.
     * @param view an instance of the view.
     */
    public void setView(CustomerHomepageView view) {
        this.view = view;
    }

    /**
     * Returns the instance of the view.
     * @return The instance of the CustomerHomepageView.
     */
    public CustomerHomepageView getView() {
        return view;
    }

    /**
     * Returns the details of a current order.
     * @return A String with details of the current order.
     */
    public String getCurrentOrderDetails() {
        String output = "";
        if(currentOrder != null)
        {
            output += "#"+currentOrder.getId()+"\n";
            output += currentOrder.getOrderState().toString()+"\n";
            output += currentOrder.getDate().getDayOfMonth() + " " + currentOrder.getDate().getMonth() +" " + currentOrder.getDate().getYear();
            output += "\n" + currentOrder.getDate().getHour() +":"+currentOrder.getDate().getMinute();
            output += "\n" + String.format("%.2f",currentOrder.getTotalCost()) + " €";
        }

        return output;
    }


    public void onCancel()
    {
        view.ShowConfirmationMessage();
    }


    /**
     * Performs the cancellation of the currentOrder,
     * leading to a layout change and updating the orderHistory list.
     * Also removes the currentOrder from the list of orders
     * being prepared by the chef.
     */
    public void cancel()
    {
        if(currentOrder!=null){
            currentOrder.setStateCancelled();
            currentOrder = null;
            chooseLayout();
            setOrderHistory();
       }
    }

    /**
     * Chooses the layout for the currentOrderPageFragment
     * based on whether there is a set active order or not.
     */
    public void chooseLayout() {

        if (currentOrder!=null)
        {
            view.showCurrentOrder();
        }
        else{

            view.showNoCurrentOrder();
        }
    }

    /**
     * Calls the redirectTopUp() function in the
     * activity through the CustomerHomepageView interface
     * to navigate to the top-up activity.
     */
    public void onTopUp() {
        view.redirectTopUp();
    }



    /**
     * Returns the maximum number of tables in the restaurant.
     * @return An integer indicating the total tables in the restaurant.
     */
    public int getRestaurantCapacity() {
        if (restaurant!=null) {
            return restaurant.getTotalTables();
        }
        else
        {
            return 0;
        }
    }

    /**
     * Returns false if there is an active order
     * for the selected table in the set restaurant,
     * otherwise returns true.
     * @param tableNumber the number of a table
     * @return true or false
     */
    public boolean checkTableAvailability(int tableNumber) {
        if (restaurant != null) {
            ArrayList<Order> orders = restaurant.getOrders();
            for (Order order : orders) {
                if (order.getOrderState() == Order.State.RECEIVED
                        && order.getTableNumber() == tableNumber) {
                    return false;
                }

            }
            return true;
        }
        return false;
    }

    public void onPlaceOrder() {
        view.showTableNumberPickerPopup();
    }
}

