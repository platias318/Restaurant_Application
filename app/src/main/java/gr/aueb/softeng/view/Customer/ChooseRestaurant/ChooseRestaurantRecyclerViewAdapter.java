package gr.aueb.softeng.view.Customer.ChooseRestaurant;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;
        import java.util.List;

        import gr.aueb.softeng.domain.Restaurant;
        import gr.aueb.softeng.team08.R;

public class ChooseRestaurantRecyclerViewAdapter extends RecyclerView.Adapter<ChooseRestaurantRecyclerViewAdapter.ViewHolder>{
    private final List<Restaurant> restaurants;
    private final ChooseRestaurantRecyclerViewAdapter.RestaurantSelectionListener listener;
    /**
     * Initialized the list with the available restaurants
     * Initializes the Listener object that is gonna be used when the customer presses on to a specific order
     * @param restaurants the available restaurants
     * @param listener the item selection listener obejct that we are going to use.
     */
    public ChooseRestaurantRecyclerViewAdapter(ArrayList<Restaurant> restaurants, RestaurantSelectionListener listener){
        this.restaurants = restaurants;
        this.listener=listener;
    }
    /**
     * It passes to the adapter the layout we want our list items to appear - the available restaurants in our case.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new view holder object that contains the custom layout of the restaurants
     */
    @NonNull
    @Override
    public ChooseRestaurantRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChooseRestaurantRecyclerViewAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list_item , parent, false));
    }


    /*
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ChooseRestaurantRecyclerViewAdapter.ViewHolder holder, int position) {
        final Restaurant currentItem = restaurants.get(position);
        holder.restName.setText(String.valueOf(currentItem.getRestaurantName()));
        holder.restName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.selectRestaurant(currentItem);
            }
        });
    }
    /**
     * Initializes the Text Views that we used in the method above
     */
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView restName;
        public ViewHolder(View view)
        {
            super(view);
            restName = view.findViewById(R.id.RestaurantName);
        }
        /**
         * @return The order details are called with System.out.print.
         */
        @Override
        public String toString() {
            return super.toString() + " '" + restName.getText() + "'";
        }
    }
    /**
     *
     * @return  returns the size of the list containing the restaurants
     */
    @Override
    public int getItemCount() {
        return restaurants.size();
    }
    /**
     * Calls the method that we want when a restuarant has selected
     */
    public interface RestaurantSelectionListener
    {
        void selectRestaurant(Restaurant restaurant);
    }
}


