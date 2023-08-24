# ***Use case title*:** Restaurant Registration

<br/>

## ***Primary Actor* :** Owner

<br/>

## ***StakeHolders* :**  Owner: Wants to quickly and easily register their restaurant in the application so that customers and chefs can use it.

## Users (customers and chefs): Want to be able to use the application without difficulty to complete orders.

<br/>

## ***Preconditions* :** The owner must be logged into the application and have the necessary information to register the restaurant.

<br/>

## ***Basic Flow :***

## 1. The owner opens the restaurant management application.
## 2. The owner enters the restaurant's details (restaurant phone, address, restaurant name, phone).

## 3. The owner selects the confirmation option for the details.
## 4. The owner enters the dish options and their corresponding prices in the restaurant's menu.

## 5. The owner enters the available tables in the restaurant.

<br/>

## ***Alternative flow* :**

## 2α Non-acceptable value has been entered in one of the fields.

> ## 1. The system displays the appropriate error message.

> ## 2. Repeat step 2.

## 2β. Not all required fields have been completed.

> ## 1.An error message is displayed on the customer's screen.
>
> ## 2. Repeat step 2

<br/>
<br/>
<br/>
<br/>
<br/>

# ***Use case title*:** Adding Employee

<br/>

## ***Primary Actor* :** Owner

## ***StakeHolders* :** Owner: Wants to easily add employees to the restaurant's system.
## Chef: Wants the owner to find their account so they can be added to the system and manage orders.

## ***Preconditions* :** The owner must be logged into the application. Employees must have registered in the application, and the owner must know their details to add them to the system.

## ***Basic Flow* :**
## 1. The owner selects the "Add Employee" option in the application.

## 2.The owner enters the new employee's details to find them (name, surname, tax identification number, etc.).

## 3. The owner selects the permissions they want to grant to the new employee.

## 4. The owner confirms the addition of the new employee.

## 5. The system adds the new employee's account to the application.

## ***Alternative flow* :**

## 2α. The owner has not entered all the necessary details to find the employee.

> ## 1. The system displays the appropriate error message to the owner.

> ## 2. Repeat step 2.

## 2β.The owner does not have the correct employee details.

> ## 1. The system displays the appropriate error message to the owner.

> ## 2. Repeat step 2
## For each new employee added by the owner, repeat step 1.

<br/>
<br/>
<br/>
<br/>
<br/>


# ***Use case title* :** Exporting statistical data

<br/>

## ***Primary Actor* :** Owner

## ***StakeHolders* :** Owner: Wants to be able to generate the restaurant's statistics at any time and have access to them.
    
## ***Preconditions* :** The owner must be logged into the application. Data must have been entered for generating statistics.

# **Α) Calculating Monthly Average Revenues**
## ***Basic Flow* :**
## 1. The owner selects the "Export Monthly Revenues" option.

## 2. The application retrieves data from the order system.

## 3. The application searches for all orders made in the first month of the current year (based on the registration date of the business) and calculates the total revenue.

## 4. The application displays the total revenue for each month divided by the number of months.
## 5. The system presents the results on the owner's screen.

## ***Alternative flow* :**
## 2α. The system crashes during data retrieval.
> ##    1. Repeat step 1, returning to that flow.

## 2β. There is no data available for retrieval due to lack of completed orders (e.g., a new business or recent registration in the application).
> ## 1.Return to step 1 of the option.

# **Β) Calculating Annual Revenues**
## ***Basic flow* :**
## 1. The owner selects the "Export Annual Revenues" option.

## 2. The application retrieves data from the order system.

## 3. The application searches for all orders made in the current year (based on the registration date of the business), stored in lists by year.

## 4. The application adds all orders together (a sum field updated with each new order in the same year) and displays the total revenue for that year based on the order date.

## 5. The system presents the results on the owner's screen.

## ***Alternative flow* :**
## 2α. The system crashes during data retrieval.  
> ##    1. Repeat step 1, returning to that flow.

## 2β. There is no data available for retrieval due to lack of completed orders (e.g., a new business or recent registration in the application).
> ## 1.Return to step 1 of the option.


# **C)Calculating Average Expenses per Customer Order**
## ***Basic Flow* :**
## 1. The owner selects the "Calculate Average Expenses per Customer Order" option.

## 2. The application retrieves data from the order system.

## 3. The application operates as follows: It iterates through a list of total orders for the current year, adds the total cost to a variable.
## 4. The application then divides the total expenses by the total number of orders (as each order is made by one customer), resulting in the average expenses per customer order.
## 5. The system presents the results on the owner's screen.

## ***Alternative flow* :**
## 2α. The system crashes during data retrieval.
> ##    1. Repeat step 1, returning to that flow.

## 2β. There is no data available for retrieval due to lack of completed orders (e.g., a new business or recent registration in the application).
> ## 1.Return to step 1 of the option.



# **D)Calculating Average Daily Revenues**
## ***Basic Flow* :**
## 1. The owner selects the "Calculate Average Daily Revenues" option.

## 2. The application retrieves data from the order system.

## 3. The application retrieves the total revenues for the current year up to "today".
## 4. The application divides the total revenues by the total number of days that have passed since the beginning of the year (where the start date isn't necessarily the first day of the year; it's the first day of using the application), calculating the average daily revenues for the current year.
## 5. The system presents the results on the owner's screen.

## ***Alternative flow* :**
## 2α. The system crashes during data retrieval.s 
> ##    1. Repeat step 1, returning to that flow.

## 2β. There is no data available for retrieval due to lack of completed orders (e.g., a new business or recent registration in the application).s
> ## 1.Return to step 1 of the option.

# **Ε) Calculating the Percentage of Canceled Orderss**
## ***Basic flow* :**
## 1. The owner selects the "Calculate Percentage of Canceled Orders" option.

## 2. The application retrieves data from the order system.

## 3. The application retrieves the counter of canceled orders for each year.
## 4.  The application retrieves the counter of total orders for each year.s
## 5.The application divides the canceled orders by the total orders and multiplies by 100 to calculate the percentage of canceled orders for each year.
>## This calculation is repeated for each year of app usage.s
## 6. The system presents the results on the owner's screen.

## ***Alternative flow* :**
## 2α. The system crashes during data retrieval.
> ##    1.  Repeat step 1, returning to that flow.

## 2β. There is no data available for retrieval due to lack of completed orders (e.g., a new business or recent registration in the application).s
> ## 1.Return to step 1 of the option.s

<br/>
<br/>
<br/>
<br/>
<br/>

# ***Use case title*:** Order Management

<br/>

## ***Primary Actor* :** Customer

## ***StakeHolders* :** Customer: Wants to be able to easily and quickly submit orders through the system.

## Chef: Wants to receive orders promptly from customers.

## ***Preconditions* :** The customer must be logged into the application.
# **Α) Submitting an Order**
## ***Basic flow* :**
## 1. The customer is redirected to the order page.s
## 2. The customer selects the "Add Order" option (button '+') on the order page
## 3. The system displays the restaurant's menu with dish options.

## 4. For each dish, the system displays their respective prices.

## 5. The customer selects the dishes they want and specifies the quantity for each dish.

## 6. The customer selects the "Submit Order" option with the required amount of money.

## 7. Confirmation message of successful order submission.

</br>

## It is assumed that each customer can submit only one order at a time until it is completed. Afterwards, a new customer or the same customer can submit a new order from the beginning.

</br>

## ***Alternative flow* :**
## 6α. The customer does not have the required amount of money for the order they want to submit.
> ## 1. A message appears informing the customer about the insufficient amount of money they have.
> ## 2. The customer returns to step 3.s

<br/>

# **Β) Canceling an Order**
## ***Basic flow* :**
## 1. The customer decides to cancel the current order.
## 2.  The customer chooses the "Cancel Order" option.s
## 3. The customer confirms that they want to cancel the order.s
## 4. Confirmation message of successful order cancellation.
## 5. The process returns to step 2 for possible new actions.

<br/>

# **C)Order Trackings**
## ***Basic flow* :**
## 1. The customer tracks the status of an order.
## 2. The customer selects the order.
## 3. The customer views the details of the order.
## 4. The customer can choose to return to the order page.s

<br/>

# **D) View order history**
## ***Basic flow* :**
## 1. The customer chooses to view their order history.
## 2. They select the "Order History" tab.
 ## 3. They are redirected to the order history page.s

<br/>

# **E)  Reload E-Wallet Balances**
## ***Basic flow* :**
## 1. The customer chooses to reload their electronic wallet balance.s
## 2. The user selects the "Top-up" option.
## 3. They are redirected to the balance reload page.
## 4. They choose one of the available top-up amounts.
## 5. The banking transaction is successful.s

## **Alternative flow**
## 5α. The transaction fails.
>## 1. The user is informed about the transaction failure.s
>## 2. The process returns to step 2.

<br/>

# ***Use case title*:** User register

<br/>

## ***Primary Actor* :**User (Chefs, Customers, and Owner)

## ***StakeHolders* :**

## Owners: Wants to have an account to manage their restaurant and register it in the application.s

## Customer:Wants to have an account to use the application for ordering food.s

## Chef : Wants to have an account to use the application for managing orders.s

## ***Preconditions* :**The user has the application installed on their device and knows the necessary details.

# **A) Customer registration**
## ***Basic flow* :**

## 1. The user selects the "Sign up" option on the page.

## 2. The user is redirected to the customer registration page.

## 3. The user enters their personal information (Name, email, phone, etc.).s

## 4. The user enters their banking details for reloading their e-wallet.

## 5. The user creates a password for logging in.

## 6. The user is informed that their account was created successfully.s

</br>

## After registration, the user is redirected to the login page.

</br>

## ***Alternative flow* :**
## 3α. Entering invalid values in the fields of personal information.
>## 1. An error message is displayed on the customer's screen.
>## 2. The process returns to step 3.


## 4α. Entering invalid values in the fields of banking details.s

> ## 1. An error message is displayed on the customer's screen.
>
> ## 2.The process returns to step 4.

## 5α.  The password entered doesn't meet the required criteria.

> ## 1. A message is displayed informing the user that the password is weak, along with a list of required criteria.
>
> ## 2.The process returns to step 5.


# **Β) Employee Registrations**
## ***Basic flow* :**
## 1. The user selects the "Sign up for personnel" option on the login page.
## 2.The user is redirected to the personnel registration page.
## 3.  The user enters their personal information (Name, AFM, email, phone, date of birth).s
## 4.The user enters their IBAN for receiving their salary.
## 5.The user creates a password for logging in.

## 6.The user is informed that the creation of their employee account is pending confirmation by the owner.

## ***Alternative flow* :**
## 3α. Entering invalid values in the fields of personal information.
> ## 1. An error message is displayed on the user's screen.s
> ## 2. The process returns to step 2.
## 4α. Entering incorrect IBAN
> ## 1. An error message is displayed on the user's screen.
> ## 2. The process returns to step 4.
## 5α.  The entered password doesn't meet the required criteria.

> ## 1. A message is displayed informing the user that the password is weak, along with a list of required criteria.s
>
> ## 2. The process returns to step 5.

# **Γ) Owner Registration**
## ***Basic flow* :**
## 1. The user selects the "Sign up for manager" option on the login page.
## 2. The user is redirected to the owner registration page.
## 3. The user enters their personal information (Name, AFM, email, phone, date of birth).
## 4. The user enters their IBAN for receiving their salary.s
## 5. The user creates a password for logging in.

## 6. The user is informed that their owner account was created successfully.

## ***Alternative flow* :**
## 3α. Entering invalid values in the fields of personal information.s
> ## 1. An error message is displayed on the user's screen.
> ## 2. The process returns to step 2.
## 4α.Entering incorrect IBANs
> ## 1. An error message is displayed on the user's screen.s
> ## 2. The process returns to step 4.
## 5α. The entered password doesn't meet the required criteria.

> ## 1. A message is displayed informing the user that the password is weak, along with a list of required criteria.
>
> ## 2.  The process returns to step 5.s

<br/>
<br/>

# **D) User Login to the Application**
## ***Basic flow* :**
## 1.The user enters their login details (Username and password).s
## 2. The user selects the "Log in" option.
## 3. The correctness of the provided details is verified.
## 3. The correctness of the provided details is verified.
## 4. The user successfully logs in.

## ***Alternative flow* :**
## 4α. The entered details are incorrect.s
> ## 1. An error message is displayed on the user's screen.
> ## 2. The process returns to step 1.
# ***Use case title*:** Order Processing

<br/>

## ***Primary Actor* :** Cook

## ***StakeHolders* :**

## Customer: Wants to track the progress of their order transparently.

## Cook: Wants to process the order step by step.

## ***Preconditions* :** The cook is logged in to the application, and the application notifies the cook about new orders.

## ***Basic flow* :**
## 1. The cook is redirected to the orders page.

## 2. The cook is informed about the new order (order status: received).

## 3. The cook selects the "order selection" option when they start preparing it.s

## 4. The payment for the completed order is automatically processed (order status: completed).

## 5. The application sends a notification to the customer about the successful payment.s

## 6. Relevant details of the completed order are saved to a file.

## ***Alternative flow* :**
## 2α. The cook hasn't received any new orders.

## 4α.  The order is canceled by the customer.

> ## 1. The cook is informed that there are no new orders through a notification.
>
> ## 2. The flow returns to step 3.

## 5α. An error occurs during the automated payment process of the order.

> ## 1. The flow returns to step 6.

> ## The order's basic state transitions are "received" and "completed." This means that the order's status cannot change to any subsequent state.
