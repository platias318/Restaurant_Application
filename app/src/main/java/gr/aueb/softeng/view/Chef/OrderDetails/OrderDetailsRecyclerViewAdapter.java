package gr.aueb.softeng.view.Chef.OrderDetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gr.aueb.softeng.domain.OrderLine;
import gr.aueb.softeng.team08.R;


public class OrderDetailsRecyclerViewAdapter extends RecyclerView.Adapter<OrderDetailsRecyclerViewAdapter.ViewHolder> {
    private final List<OrderLine> orderLines;
    /**
     *Initializes the list of Order Lines for the specific order.
     *@param orderLines The list of order lines for the selected order by the chef.
     */
    public OrderDetailsRecyclerViewAdapter(List<OrderLine> orderLines){
        this.orderLines = orderLines;
    }
    /**
     *Passes the desired layout to the adapter for displaying the objects in our list - the order lines in our case.
     *@param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     *@param viewType The view type of the new View.
     *@return A new view holder object with the custom layout of order lines.
     */
    @NonNull
    @Override
    public OrderDetailsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderDetailsRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderline_list_item, parent, false));
    }
    /**
     *Sets the name of each order line's dish in the specific TextView we've created, and the quantity of the dish in another TextView.
     *@param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     *@param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull OrderDetailsRecyclerViewAdapter.ViewHolder holder, int position) {
        final OrderLine currentItem = orderLines.get(position);
        holder.DishName.setText(String.valueOf(currentItem.getDish().getDishName()));
        holder.DishQuantity.setText("Quantity:"+String.valueOf(currentItem.getQuantity()));

    }
    /**
     * Initializes the Text Views used in the above method.
     */
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView DishName;
        public final TextView DishQuantity;
        public ViewHolder(View view)
        {
            super(view);
            DishName = view.findViewById(R.id.DishNameTextView);
            DishQuantity = view.findViewById(R.id.DishQuantityTextView);

        }
        /**
         *
         * @return Returns the name and quantity of the dish when called with System.out.print.
         */
        @Override
        public String toString() {
            return super.toString() + " '" + DishName.getText() + "'"+ DishQuantity.getText();
        }
    }

    /**
     * Returns the size of the list of order lines.
     * @return the size of the order lines list.
     */
    @Override
    public int getItemCount() {
        return orderLines.size();
    }
}
