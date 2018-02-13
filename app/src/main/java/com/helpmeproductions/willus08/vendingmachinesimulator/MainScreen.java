package com.helpmeproductions.willus08.vendingmachinesimulator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainScreen extends AppCompatActivity {

    CustomerInformation customer;
    MachineInformation vendingMachine;
    TextView display;
    RecyclerView coinsView;
    CoinsAdapter adapter;
    List<String> coinsList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    DefaultItemAnimator animator;
    IntentFilter filter;
    private Receiver reciever;
    private CountDownTimer displaytimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        customer = new CustomerInformation();
        vendingMachine = new MachineInformation();
        vendingMachine.createMachine();

        display = findViewById(R.id.tvDisplay);
        coinsView = findViewById(R.id.rvCoins);

        coinsList.addAll(Arrays.asList(getResources().getStringArray(R.array.coins)));

        adapter = new CoinsAdapter(customer, coinsList);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        animator = new DefaultItemAnimator();

        coinsView.setAdapter(adapter);
        coinsView.setLayoutManager(layoutManager);
        coinsView.setItemAnimator(animator);

        displaytimer = new CountDownTimer(3000,1) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                resetDisplay();
            }
        };


    }


    @Override
    protected void onStart() {
        super.onStart();
        filter=new IntentFilter("useMoney");
        reciever=new Receiver();
        registerReceiver(reciever,filter);
        resetDisplay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(reciever);
    }

    private void useCoins(String coinName) {
        customer.useMoney(coinName);
        vendingMachine.insertCoin(coinName);
        int amount = vendingMachine.calculateCoinsInserted();
        if(amount < 100){
            display.setText("$ 0."+ amount);
        }else {
            display.setText("$ "+ amount/100 + "." + amount%100);
        }
        adapter = new CoinsAdapter(customer,coinsList);
        coinsView.setAdapter(adapter);
        displaytimer.start();

    }

    private void resetDisplay() {
        if (vendingMachine.checkIfExactChangeRequired()){
            display.setText("Exact Change Only");
        }else{
            display.setText("Insert Coins");
        }
    }

    public void optionChosen(View view) {
        String item="";
        int cost =0;
        switch (view.getId()){
            case R.id.btnCoke:
                item = Enums.items.COKE.toString();
                cost = Enums.items.COKE.getValue();
                break;
            case R.id.btnChips:
                item = Enums.items.CHIPS.toString();
                cost = Enums.items.CHIPS.getValue();
                break;
            case R.id.btnCandy:
                item = Enums.items.CANDY.toString();
                cost = Enums.items.CANDY.getValue();
                break;
            case R.id.btnReturn:
                customer.reciveMoney(vendingMachine.returnCurrency());
                adapter = new CoinsAdapter(customer,coinsList);
                coinsView.setAdapter(adapter);

        }
       if(!item.equals("")){
            if(vendingMachine.checkStock(item)){
               if(cost > vendingMachine.calculateCoinsInserted() ){
                   if(cost < 100){
                       display.setText("$ 0."+ cost);
                   }else {
                       display.setText("$ "+ cost/100 + "." + cost%100);
                   }
               }else {
                   display.setText("Vend");
                   customer.reciveItem(item);
                   customer.reciveMoney(vendingMachine.buyItem(item));
                   adapter = new CoinsAdapter(customer,coinsList);
                   coinsView.setAdapter(adapter);

               }
           }else {
               display.setText("Out of Stock");
           }
       }else {
           display.setText("Returned");
       }
       displaytimer.start();
    }

    class Receiver extends BroadcastReceiver{

       @Override
       public void onReceive(Context context, Intent intent) {
           useCoins(intent.getStringExtra("coinName"));
       }
   }


}
