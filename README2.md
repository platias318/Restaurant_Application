# Restaurant System Description

> * Author: Κωνσταντίνος Πλατιάς 
<br/>

> ### The system to be designed concerns an application for the digital transformation of a restaurant. The actors of this system are the owner of the restaurant, the chefs, and the customers. Initially, the owner registers in the system for their restaurant and enters the restaurant's details, menu, and available tables. The application will display a suitable QR code for each table, which can be printed and placed on the corresponding tables by the restaurant owner. Additionally, the chefs and customers need to register with their details in the system, creating an account. Customers have an account with an electronic wallet, while chefs have an account with special permissions granted by the owner as employees.The application will allow customers to manage their orders through an active orders page and a completed orders page (Order History). They can navigate the restaurant's menu with dish options and their respective prices by scanning the QR code available in print form on the table using their mobile phones. This way, they can place an order from their mobile phones, specifying the quantity of each dish they want from the menu.Each order submitted by a customer will appear on the mobile/tablet screen of the chef, who will mark the start and completion of its preparation through the application. Furthermore, customers can view their orders and track their progress through the application, and they can cancel them if desired, provided they have not been completed yet. Once the chef marks the completion of an order, the corresponding amount will be automatically deducted from the customer's electronic wallet, and the customer will be notified of the transaction completion (assuming the customer is committed to paying upon order submission, and if there is no cancellation, the transaction is automatically completed). Then, the waiter delivers the order. All orders are stored in a file so that the application can later generate reports and statistical data accessible to the owner. Specifically, it will calculate monthly and yearly revenues, the average expenses made by each customer, the average daily revenues, and the percentage of canceled orders.

# System Requirements

> ### The system we will implement has various requirements that need to be taken into account. Among these are the existence of a registration process for the restaurant owner, the capability for each owner to input the restaurant's details, menu, and tables, as well as the generation of different QR codes for each table. Additionally, registration for chefs and customers is deemed necessary.Moreover, it is essential for customers to be able to view the menu through the QR code they scan, have the option to select the desired quantity and dish they want. They should also be able to place their order through the application, provided they have sufficient funds in their electronic wallet, monitor the order details and progress, or even cancel the order.The chefs must be able to view the users' orders and update the status of each order. Lastly, the system should provide the capability to store all order information (cost, time, bill, table) for each order so that necessary statistical data can be generated and accessed by the restaurant owners.

<br/>

## [ ***Use case diagram for restaurant***](docs/uml/requirements/use_case.png)

## [***.uxf file***](docs/markdown/uml/requirements/restaurant_use_case.uxf)

<br/>

![](docs/uml/requirements/use_case.png)
