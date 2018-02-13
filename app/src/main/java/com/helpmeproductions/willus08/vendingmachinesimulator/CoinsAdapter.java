package com.helpmeproductions.willus08.vendingmachinesimulator;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CoinsAdapter extends RecyclerView.Adapter<CoinsAdapter.ViewHolder>{
 CustomerInformation customer;
   List<String> coinsList = new ArrayList<>();

    public CoinsAdapter(CustomerInformation customer, List<String> coinsList) {
        this.customer = customer;
        this.coinsList = coinsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_coins, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String coin = coinsList.get(position);
        if(customer.getCoinsInPocket().containsKey(coin)){

            holder.coinsLeft.setText(""+customer.getCoinsInPocket().get(coin));
            switch (Enums.Currency.valueOf(coin.toUpperCase())){
                case NICKEL:
                    Picasso.with(holder.itemView.getContext())
                            .load(R.drawable.nickel_image)
                            .into(holder.coinImage);
                  //  holder.coinImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.nickel_image));
                    break;
                case DIME:
                    Picasso.with(holder.itemView.getContext())
                            .load(R.drawable.dime_image)
                            .into(holder.coinImage);
                    //holder.coinImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.dime_image));
                    break;
                case QUARTER:
                    Picasso.with(holder.itemView.getContext())
                            .load(R.drawable.quarter_image)
                            .into(holder.coinImage);
                   // holder.coinImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.quarter_image));
                    break;
                case HALFDOLLAR:
                    Picasso.with(holder.itemView.getContext())
                            .load(R.drawable.half_dollar_image)
                            .into(holder.coinImage);
                   // holder.coinImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.half_dollar_image));
                    break;
                case DOLLAR:
                    Picasso.with(holder.itemView.getContext())
                            .load(R.drawable.dollar_image)
                            .into(holder.coinImage);
                    //holder.coinImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.dollar_image));
                    break;
            }
        }else{
            holder.coinsLeft.setText(""+0);
            holder.coinImage.setColorFilter(Color.BLACK);
            Picasso.with(holder.itemView.getContext())
                    .load(R.drawable.dollar_image)
                    .into(holder.coinImage);
            // set image to a shadow to show you no longer have any of that coin
        }
        holder.coinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("useMoney");
                intent.putExtra("coinName",coin);
                holder.itemView.getContext().sendBroadcast(intent);
                if(customer.getCoinsInPocket().containsKey(coin)){
                 holder.coinsLeft.setText(""+customer.getCoinsInPocket().get(coin));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return coinsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView coinsLeft;
        ImageView coinImage;
        public ViewHolder(View itemView) {
            super(itemView);
            coinsLeft = itemView.findViewById(R.id.tvCoinCount);
            coinImage = itemView.findViewById(R.id.ivCoinImage);
        }
    }
}
