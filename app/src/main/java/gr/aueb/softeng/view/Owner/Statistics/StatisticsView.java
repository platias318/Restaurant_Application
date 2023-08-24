package gr.aueb.softeng.view.Owner.Statistics;

public interface StatisticsView {
    /**
     * Displays an alert message with a title and message.
     * @param title The title of the message
     * @param message The content of the message
     */
    void showErrorMessage(String title, String message);
    /**
     * It gets called so that we return to the previous activity
     */
    void goBack();
    /**
     * Displays the average monthly income of the restaurant on the screen.
     * @param monthlyIncome The calculated monthly income amount in String format.
     */
    void setAVGMonthlyIncome(String monthlyIncome);
    /**
     * Displays the annual income of the restaurant on the screen.
     * @param yearlyIncome The calculated yearly income in String format.
     */
    void setYearlyIncome(String yearlyIncome);
    /**
     * Displays the average expenses per customer order on the screen.
     * @param orderExpenses The calculated expenses in String format.
     */
    void setAvgOrderExpenses(String orderExpenses) ;
    /**
     * Displays the average daily expenses on the screen.
     * @param revenue The calculated amount of average daily expenses in String format.
     */
    void setAVGDailyRevenue(String revenue);
    /**
     * Displays the percentage of canceled orders on the screen.
     * @param canc The calculated percentage of canceled orders.
     */
    void setOrderCancellationRate(String canc);
}
