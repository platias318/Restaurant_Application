package gr.aueb.softeng.view.Chef.HomePage;

import java.util.ArrayList;

import gr.aueb.softeng.dao.ChefDAO;
import gr.aueb.softeng.dao.OrderDAO;

import gr.aueb.softeng.domain.Chef;
import gr.aueb.softeng.domain.Order;


public class ChefHomePagePresenter {
    ChefHomePageView view;

    private ArrayList<Order> orderList;
    private ChefDAO chefDAO;
    private OrderDAO orderDAO;

    private Chef chef;
    /**
     *Initializes the Presenter in order to be able to add/find the chef and their orders.
     *@param chefDAO an object containing the static list of chefs, allowing addition/retrieval of chefs.
     *@param orderDAO an object containing the static list of orders, allowing addition/retrieval of orders for a specific chef.
     */
    public ChefHomePagePresenter(ChefDAO chefDAO, OrderDAO orderDAO){
        this.chefDAO=chefDAO;
        this.orderDAO=orderDAO;
    }
    /**
     *Populates the list of orders with the orders of the specific chef who is logged in.
     *Removes orders that have been completed each time it is called.
 */
    public void setOrderList(){
        ArrayList<Order> orders = chef.getOrders();
        orderList = new ArrayList<>();
        int i=0;
        for(Order order: orders){
            if (order.getOrderState() == Order.State.RECEIVED)
            {
                orderList.add(order);
            }
        }
    }
    /**
     *Sets our view object to utilize the methods of its interface.
     *@param view An instance of the view.
     */
    public void setView(ChefHomePageView view){
        this.view=view;
    }
    /**
     * @return returns the chef DAO with the chefs that we received as a parameter from the constructor of the class.
     */
    public ChefDAO getChefDAO(){
        return this.chefDAO;
    }
    /**
     * @return returns the order DAO with the orders that we received as a parameter from the constructor of the class.
     */
    public OrderDAO getOrderDAO(){
        return this.orderDAO;
    }
    /**
     *Initializes the chef based on the object retrieved from the DAO and the unique ID we provide.
     *@param chefId is the chef ID of a specific chef.
     */
    public void setChef(int chefId) {
        chef = chefDAO.find(chefId);
    }

    /**
     *Returns the list of orders for the specific chef.
     *@return the list of active orders.
     */
    public ArrayList<Order> getOrderList(){
        return orderList;
    }

    /**
     * Calls the method of the view that changes the appearance of the screen based on whether the list of the owner's restaurants is empty or not.
     */
    public void onChangeLayout(){
        if (orderList.isEmpty()) {
            view.ShowNoOrders();
        }
        else {
            view.ShowOrders();
        }
    }
    /**
     * Calls the method of the view that takes us to the previous activity that invoked our current activity.
     */
    public void onBack(){
        view.goBack();
    }

    /**
     * We return the initialized view object above.
     * @return An instance of the view.
     */
    public ChefHomePageView getView(){
        return this.view;
    }

    /**
     *We return the chef of the restaurant that was set above.
     *@return The instance of the chef.
     */
    public Chef getChef(){
        return this.chef;
    }
}
