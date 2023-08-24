package gr.aueb.softeng.view.Customer.PlaceOrder;

import java.time.LocalDateTime;
import java.util.ArrayList;

import gr.aueb.softeng.dao.CustomerDAO;
import gr.aueb.softeng.dao.OrderDAO;
import gr.aueb.softeng.dao.RestaurantDAO;
import gr.aueb.softeng.domain.Customer;
import gr.aueb.softeng.domain.Dish;
import gr.aueb.softeng.domain.Order;
import gr.aueb.softeng.domain.OrderLine;
import gr.aueb.softeng.domain.Restaurant;

public class PlaceOrderPresenter {
    PlaceOrderView view;

    private RestaurantDAO restaurantDAO;
    private Restaurant restaurant;

    private OrderDAO orderDAO;

    private Order order;

    private CustomerDAO customerDAO;

    Customer customer;

    public PlaceOrderPresenter(RestaurantDAO restaurantDAO,CustomerDAO customerDAO,OrderDAO orderDAO)
    {
        this.restaurantDAO = restaurantDAO;
        this.customerDAO = customerDAO;
        this.orderDAO = orderDAO;
    }

    public void setView(PlaceOrderView view) {
        this.view = view;
    }

    public PlaceOrderView getView() {
        return view;
    }

    /**
     * We retrieve the dishes of the restaurant so that we can print them
     * @return The restaurant dishes
     */
    public ArrayList<Dish> getDishes() {
        ArrayList<Dish> dishes = new ArrayList<>();
        if (restaurant!=null)
        {
            for (Dish dish : restaurant.getDishes())
            {
                dishes.add(dish);
            }
        }
        return dishes;
    }

    public void addOrderLine(int quantity,Dish dish) {
       if (dish!=null && quantity >0){
           order.addOrderLine(new OrderLine(quantity,dish));
       }
    }

    public void setRestaurant(int restaurantId) {
        restaurant = restaurantDAO.find(restaurantId);
    }

    /**
     * If we have dishes, we display a message that there are no dishes available. Otherwise,
     * the list of dishes is shown.
     */
    public void onChangeLayout() {
        if(getDishes().isEmpty())
        {
            view.showEmptyList();
        } else {
            view.showDishList();
        }
    }

    /**
     * We check if the initial order has orderLines, and if not, we don't perform any further actions.
     * Otherwise, we check if it is successfully added to the restaurant, and in this case, display a confirmation message.
     * If the order is not added successfully, it means the customer doesn't have the required balance,
     * and they are informed about this through a message.
     */
    public void onPlaceOrder() {
        if (!order.getOrderLines().isEmpty()) {
            if (order.getCustomer().getBalance()>=order.getTotalCost()) {
                view.ShowConfirmationMessage(new ConfirmationListener() {
                    @Override
                    public void onConfirmation(boolean confirmed) {
                        if (confirmed) {
                            restaurant.addOrder(order);
                            orderDAO.save(order);
                            view.goBack();
                        }
                    }
                });
            } else {
                view.insufficientFundsMessage();
            }
        }
    }

    public double getTotalCost() {
        return order.getTotalCost();
    }

    public void createOrder(int tableNumber) {
        order = new Order(tableNumber, LocalDateTime.now(),orderDAO.nextId(),customer);
    }

    public void setCustomer(int customerId) {
        customer = customerDAO.find(customerId);
    }

    public Order getOrder()
    {
        return order;
    }
    public Customer getCustomer(){
        return customer;
    }

    public void onCart() {
        view.redirectToCart(order.getOrderLines());
    }

    public void setOrderLines(ArrayList<OrderLine> modifiedOrderLines) {
        order.setOrderLines(modifiedOrderLines);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

}
