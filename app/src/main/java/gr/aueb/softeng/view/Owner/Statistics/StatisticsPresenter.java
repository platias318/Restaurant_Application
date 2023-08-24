package gr.aueb.softeng.view.Owner.Statistics;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import gr.aueb.softeng.dao.OwnerDAO;
import gr.aueb.softeng.dao.RestaurantDAO;
import gr.aueb.softeng.domain.Order;
import gr.aueb.softeng.domain.Restaurant;

public class StatisticsPresenter {
    private RestaurantDAO restaurantDAO;
    private OwnerDAO ownerDAO;
    private Restaurant restaurant;
    private StatisticsView view;

    /**
     *Initializes the owner DAO and the restaurant DAO to enable storing and retrieving
     *restaurants and owners from our statistical list.
     *@param ownerDAO
     *@param restaurantDAO
     */

    public StatisticsPresenter(OwnerDAO ownerDAO, RestaurantDAO restaurantDAO){
        this.ownerDAO=ownerDAO;
        this.restaurantDAO = restaurantDAO;
    }

    /**
     * Initializes the view from which we will use the methods of its interface.
     * @param view An instance of the view
     */
    public void setView(StatisticsView view)
    {
        this.view = view;
    }

    /**
     * Finds the restaurant object from the DAO's list of restaurants using the unique restaurant ID we're searching for.
     * @param restaurantId The ID of the restaurant whose details we want to display
     */
    public void setRestaurant(int restaurantId) {
        restaurant = restaurantDAO.find(restaurantId);
    }
    /**
     * It gets called when we want to return to the home screen
     */
    public void OnBack(){
        view.goBack();
    }

    /**
     * Calculates the annual income of the restaurant for the current year, obtained from the 'now' variable.
     * @return Returns the calculated sum in the calling method.
     */
    public double calcYearlyIncome(){
        double sum=0.0;
        LocalDateTime now = LocalDateTime.now();
        for(Order order:restaurant.getOrders()){
            if(order.getDate().getYear()== now.getYear() && order.getOrderState()== Order.State.COMPLETED){
                sum+=order.getTotalCost();
            }
        }
        return sum;
    }

    /**
     Calculates the average monthly income of the restaurant for the current year by dividing
     the total income by the number of different months in which orders were made.
     @return Returns the total sum divided by the number of different months with orders. If the number of months is 0, it returns 0.
     */
    public double calcAvgMonthlyIncome(){
        LocalDateTime now = LocalDateTime.now();
        double totalIncome = 0.0;
        Set<Integer> monthsWithOrders = new HashSet<>();

        for (Order order : restaurant.getOrders()) {
            LocalDateTime orderDate = order.getDate();
            if (orderDate.getYear() == now.getYear() && order.getOrderState()== Order.State.COMPLETED) {
                totalIncome += order.getTotalCost();
                int month = orderDate.getMonthValue();
                monthsWithOrders.add(month);
            }
        }
        int totalMonths = monthsWithOrders.size();
        if(totalMonths!=0) {
            return totalIncome / totalMonths;
        }else{
            return 0;
        }
    }

    /**

     Calculates the average expenses of each customer's order by summing up all the costs of orders and dividing by the number of orders. We assume
     that each order is made by a single customer and we are interested in the expenses of each order, which is equivalent to each customer.

     @return Returns the above sum. If there are no orders, it returns 0.
     */

    public double calcAvgOrderExpenses(){
        double cost=0.0;
        int counter=0;
        LocalDateTime now = LocalDateTime.now();
        for (Order order : restaurant.getOrders()) {
            LocalDateTime orderDate = order.getDate();
            if (orderDate.getYear() == now.getYear() && order.getOrderState()== Order.State.COMPLETED) {
                cost += order.getTotalCost();
                counter++;
            }
        }
        if(counter!=0){
            return cost/counter;
        }else{
            return 0;
        }
    }

    /**
     * Calculates the average daily income of the restaurant.
     * @return Returns the sum divided by the total number of days when orders are placed.
     * If the number of days is zero, it returns 0.
     */
    public double calcAvgDailyRevenue(){
        double totalIncome = 0.0;
        int totalDays = 0;
        LocalDateTime now = LocalDateTime.now();

        for (int month = 1; month <= 12; month++) {
            for (Order order : restaurant.getOrders()) {
                LocalDateTime orderDate = order.getDate();
                if (orderDate.getYear() == now.getYear() && orderDate.getMonthValue() == month && order.getOrderState()== Order.State.COMPLETED ) {
                    totalIncome += order.getTotalCost();
                    totalDays++;
                }
            }
        }
        if(totalDays!=0) {
            return totalIncome / totalDays;
        }else{
            return 0;
        }

    }

    /**
     Calculates the percentage of canceled orders for the restaurant.
     @return Returns the specific percentage.
     If there are no orders, it returns 0.
     */
    public double calcCancelRate(){
        LocalDateTime now = LocalDateTime.now();
        int totalOrders = 0;
        int cancelledOrders = 0;

        for (Order order : restaurant.getOrders()) {
            LocalDateTime orderDate = order.getDate();
            if (orderDate.getYear() == now.getYear()) {
                totalOrders++;
                if (order.getOrderState()== Order.State.CANCELLED) {
                    cancelledOrders++;
                }
            }
        }
        if(totalOrders!=0) {
           return((double) cancelledOrders / totalOrders * 100);
        }else{
            return 0;
        }
    }

    /**
     *
     * This method calls the view's methods to pass the results of the
     * statistical calculations, using the functions described above.
     */
    public void calculateStats(){
        double yearlyIncome= calcYearlyIncome();
        double AvgMonthlyIncome = calcAvgMonthlyIncome();
        double AvgOrderExpenses = calcAvgOrderExpenses();
        double calcCancelRate=calcCancelRate();
        double AvgDailyRevenue = calcAvgDailyRevenue();


        view.setYearlyIncome(String.valueOf(yearlyIncome));


        view.setAVGMonthlyIncome(String.valueOf(AvgMonthlyIncome));

        view.setAvgOrderExpenses(String.valueOf(AvgOrderExpenses));



        view.setAVGDailyRevenue(String.valueOf(AvgDailyRevenue));

        view.setOrderCancellationRate(String.valueOf(calcCancelRate));
    }
    public StatisticsView getView(){
        return this.view;
    }
    public Restaurant getRestaurant(){
        return this.restaurant;
    }
}
