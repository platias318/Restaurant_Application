package gr.aueb.softeng.view.Owner.RestaurantDetails;



public interface RestaurantDetailsView {
 /**
  *Displays an alert message with the given title and message.
  *@param title The title of the message
  *@param message The content of the message
  */
    void showErrorMessage(String title, String message);
   /**
   * It gets called so that we return to the previous activity
   */
    void goBack();
   /**
   * It displays the restaurant name that was selected on the screen
   * @param The name that will be displayed
   */
     void setRestName(String name);
    /**
     *It displays the id of the restaurant that was selected on the screen
     *@param id the id that will be displayed
     */

     void setRestId(String id);
    /**
    * It displays the number of tables the restaurant has that was selected on the screen
    * @param The number of tables in string form
    */
     void setRestTables(String tables);
    /**
    * It displays the street name of the restaurant that was selected on the screen
    * @param street the street that will be displayed
    */
     void setRestAddressStreet(String street);
    /**
    * It displays the street of the restaurant that was selected on the screen
    * @param num the number that will be displayed
    */
     void setRestAddressNumber(String num);
    /**
     * Displays the postal code of the selected restaurant on the screen.
    * @param The zip code that will be displayd
    */
     void setRestZip(String zip);
    /**
     * Displays the city of the selecten restaurant on the screen
     * @param city the city we want to be displayed
     */
     void setRestAddressCity(String city);
    /**
     * Called when the statistics export button is pressed, and it calculates the statistics of the selected restaurant.
    */
     void extractStats();
    /**
     * Called when the button for adding a new chef to the restaurant is pressed, and it passes the restaurant's id as a parameter.
    */
     void addChef();
}
