package gr.aueb.softeng.view.Chef.OrderDetails;

import java.util.ArrayList;

import gr.aueb.softeng.dao.ChefDAO;
import gr.aueb.softeng.dao.OrderDAO;
import gr.aueb.softeng.domain.Chef;
import gr.aueb.softeng.domain.Order;
import gr.aueb.softeng.domain.OrderLine;

public class OrderDetailsPresenter {
    OrderDetailsView view;

    private ArrayList<OrderLine> orderLineList;
    private ChefDAO chefDAO;
    private OrderDAO orderDAO;
    private Chef chef;

    private Order order;
    /**
     *Initializes the Presenter in order to be able to add/find the chef and their orders.
     *@param chefDAO An object containing the static list of chefs, allowing addition/retrieval of chefs.
     *@param orderDAO An object containing the static list of total orders, allowing addition/retrieval of orders for a specific chef.
     */
    public OrderDetailsPresenter(ChefDAO chefDAO, OrderDAO orderDAO){
        this.chefDAO=chefDAO;
        this.orderDAO=orderDAO;
    }

    /**
     * Populates the list with the Order Lines of the specific order.
     */
    public void setOrderLineList() {
        orderLineList = order.getOrderLines();
    }

    /**
     *Sets our view object to utilize the methods of its interface.
     *@param view An instance of the view.
     */
    public void setView(OrderDetailsView view){
        this.view=view;
    }

    /**
     *Returns the chef DAO that we received as a parameter above.
     *@return The instance of the DAO.
     */
    public ChefDAO getChefDAO(){
        return this.chefDAO;
    }
    /**
     *Returns the order DAO that we received as a parameter above.
     *@return The instance of the DAO.
     */
    public OrderDAO getOrderDAO(){
        return this.orderDAO;
    }

    /**
     * Βρίσκει τον μάγειρα της παραγγελίας μέσω του dao Και μέσω του μοναδικού chef id που περνάμε
     * @param chefId το μοναδικό id του μάγειρα
     */
    public void setChef(int chefId) {
        chef = chefDAO.find(chefId);
    }

    /**
     *Finds the chef of the order through the DAO using the unique chef ID that we pass.
     *@param chefId The unique ID of the chef.
     */
    public void setOrder(int orderId){
        order= orderDAO.find(orderId);
    }

    /**
     *Returns the list of Order Lines for the order.
     *@return The list of Order Lines.
     */
    public ArrayList<OrderLine> getOrderLineList(){
        return this.orderLineList;
    }

    /**
     * Calls the methods of the view that display the elements of the order on the screen.
     */
    public void setOrderDetails(){
        view.setOrderId(String.valueOf(order.getId()));
        view.setOrderState(String.valueOf(order.getOrderState()));
        view.setTableNumber(String.valueOf(order.getTableNumber()));
        view.setDate(String.valueOf(order.getDate().getDayOfMonth()+" "+order.getDate().getMonth()+" "+order.getDate().getYear() +" Time:"+ order.getDate().getHour()) + ":"+String.valueOf(order.getDate().getMinute()));
    }

    /**
     *Called when the "complete order" button is pressed.
     *Checks if the status is not canceled and if it's not already completed.
     *If conditions are met, changes the status to completed and calls the method showOrderCompletedMessage to display the success message.
     */
    public void onCompleted() {
        if (order.getOrderState() != Order.State.CANCELLED && order.getOrderState() != Order.State.COMPLETED) {
            order.setStateCompleted(); // η μέθοδος αυτή αφαιρεί και τα λεφτά απο τον πελάτη
            view.setOrderState(String.valueOf(order.getOrderState()));
            view.showOrderCompletedMessage();
        }
    }
    /**
     * Calls the method of the view that takes us back to the previous activity that called us.
     */
    public void OnBack(){
        view.goBack();
    }

    /**
     *Returns the view object that we created earlier.
     *@return The instance of the object.
     */
    public OrderDetailsView getView(){
        return this.view;
    }

    /**
     *Returns the chef object that was set above.
     *@return The instance of the object.
     */
    public Chef getChef(){
        return this.chef;
    }

    /**
     *Returns the order object that was created earlier.
     *@return The instance of the object.
     */
    public Order getOrder(){
        return this.order;
    }

    /**
     *Depending on whether the caller of the activity is a customer or a chef, this method displays or hides the "Set Completed" button, which should only be shown if the caller is a chef.
     *@param isCustomer True if the caller is a customer, or False if it's a chef.
     */
    public void chooseLayout(boolean isCustomer) {
        if (isCustomer)
        {
            view.hideCompletionButton();
        }
        else
        {
            view.showCompletedButton();
        }
    }
}
