package com.helpmeproductions.willus08.vendingmachinesimulator;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
   List<String> itemsList = new ArrayList<>();
  CustomerInformation customer;

    public ItemsAdapter(CustomerInformation customer, List<String> itemsList) {
        this.itemsList = itemsList;
        this.customer = customer;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_coins, parent, false);

        return new ItemsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = itemsList.get(position);

        if(customer.getItemsBought().containsKey(item)){
           switch (Enums.items.valueOf(item.toUpperCase())){
               case COKE:
                   Picasso.with(holder.itemView.getContext())
                           .load(R.drawable.coke_image)
                           .into(holder.itemImage);
                   break;
               case CANDY:
                   Picasso.with(holder.itemView.getContext())
                           .load(R.drawable.candy_image)
                           .into(holder.itemImage);
                   break;
               case CHIPS:
                   Picasso.with(holder.itemView.getContext())
                           .load(R.drawable.chips_image)
                           .into(holder.itemImage);
                   break;

           }
            holder.itemCount.setText("" + customer.getItemsBought().get(item));

        }

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemCount;
        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.ivCoinImage);
            itemCount = itemView.findViewById(R.id.tvCoinCount);

        }
    }
}
