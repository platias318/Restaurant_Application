package gr.aueb.softeng.view.Owner.HomePage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gr.aueb.softeng.domain.Restaurant;
import gr.aueb.softeng.team08.R;

public class OwnerHomePageRecyclerViewAdapter extends RecyclerView.Adapter<OwnerHomePageRecyclerViewAdapter.ViewHolder>{
    private final List<Restaurant> restaurants;
    private final ItemSelectionListener listener;

    /**
     *It initializes the list with the restaurants of the owner who has logged in.
     *It initializes the Listener object to be used when the user clicks on a restaurant.
     *@param restaurants the owner's restaurants
     *@param listener the item selection listener object to be used
     */
    public OwnerHomePageRecyclerViewAdapter(List<Restaurant> restaurants, ItemSelectionListener listener){
        this.restaurants = restaurants;
        this.listener=listener;
    }

    /**
     * Περνάει στον adapter το layout που θέλουμε να εμφανιστούν τα αντικείμενα της λίστας μας - τα εστιατόρια του ιδιοκτήτη στην περίπτωσή μας
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return νέο αντικείμενο view holder με το custom layout των εστιατορίων
     */
    @NonNull
    @Override
    public OwnerHomePageRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list_item , parent, false));
    }

    /**
     *It sets the layout to the adapter for displaying our list items - the owner's restaurants in our case.
     *@param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     *@param viewType The view type of the new View.
     *@return A new view holder object with the custom restaurant layout.
     */
    @Override
    public void onBindViewHolder(@NonNull OwnerHomePageRecyclerViewAdapter.ViewHolder holder, int position) {
        final Restaurant currentItem = restaurants.get(position);
            holder.restName.setText("Name:"+String.valueOf(currentItem.getRestaurantName()));
            holder.restName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.selectRestaurant(currentItem);
                }
            });
    }

    /**
     * It initializes the Text View we used in the method above
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
         * @return It returns the restaurant's name if called with System.out.print.
         */
        @Override
        public String toString() {
            return super.toString() + " '" + restName.getText() + "'";
        }
    }

    /**
     *
     * @return Returns the size of the list containing the restaurants
     */
    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    /**
     * Calls the method we want when the restaurant gets pressed
     */
    public interface ItemSelectionListener
    {
        void selectRestaurant(Restaurant restaurant);
    }
}


