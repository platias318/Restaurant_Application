package gr.aueb.softeng.view.Chef.HomePage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gr.aueb.softeng.domain.Order;
import gr.aueb.softeng.team08.R;

public class ChefHomePageRecyclerViewAdapter extends RecyclerView.Adapter<ChefHomePageRecyclerViewAdapter.ViewHolder>{
    private final List<Order> orders;
    private final ChefHomePageRecyclerViewAdapter.ItemSelectionListener listener;
    /**
     *Initializes the list of orders for the chef who has logged in.
     *Initializes the Listener object that will be used when the chef clicks on an order.
     *@param orders The orders of the chef.
     *@param listener The item selection listener object to be used.
     */
    public ChefHomePageRecyclerViewAdapter(List<Order> orders , ChefHomePageRecyclerViewAdapter.ItemSelectionListener listener){
        this.orders = orders;
        this.listener=listener;
    }
    /**
     *Passes the desired layout to the adapter for displaying the items of our list - the orders of the chef in our case.
     *@param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     *@param viewType The view type of the new View.
     *@return A new view holder object with the custom layout of orders.
     */
    @NonNull
    @Override
    public ChefHomePageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChefHomePageRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_item2 , parent, false));
    }
    /**
     *Sets the order ID to each order item in the specific TextView we have created, and sets its status.
     *Creates the On Click Listener which gets activated when a specific order is clicked.
     *@param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     *@param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ChefHomePageRecyclerViewAdapter.ViewHolder holder, int position) {
        final Order currentItem = orders.get(position);//////////////////
        holder.OrderId.setText("Id:"+String.valueOf(currentItem.getId()));
        holder.OrderState.setText("State:"+String.valueOf(currentItem.getOrderState()));
        holder.OrderId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.selectOrder(currentItem);
            }
        });

        holder.OrderState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.selectOrder(currentItem);
            }
        });
    }
     /**
     * Initializes the Text Views used in the above method.
     */
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView OrderId;
        public final TextView OrderState;
        public ViewHolder(View view)
        {
            super(view);
            OrderId = view.findViewById(R.id.DishNameTextView);
            OrderState = view.findViewById(R.id.DishQuantityTextView);

        }
        /**
         * @return Returns the ID and status of the order if called with System.out.print.
         */
        @Override
        public String toString() {
            return super.toString() + " '" + OrderId.getText() + "'"+ OrderState.getText();
        }
    }
    /**
     *
     * @return Returns the size of the list of orders.
     */
    @Override
    public int getItemCount() {
        return orders.size();
    }
    /**
     *calls the method we desire when an order is clicked.
     */
    public interface ItemSelectionListener
    {
        void selectOrder(Order order);
    }
}
