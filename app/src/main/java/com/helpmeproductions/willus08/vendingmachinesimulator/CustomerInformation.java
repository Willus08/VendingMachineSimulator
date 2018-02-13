package com.helpmeproductions.willus08.vendingmachinesimulator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CustomerInformation {
    private Map<String,Integer> coinsInPocket;
    private Map<String,Integer> itemsBought;

    CustomerInformation() {
        coinsInPocket = new HashMap<>();
        itemsBought = new HashMap<>();
        fillPockets();
    }

    // this will stock random amounts of currency that the customer has
    private void fillPockets() {

        for (int i = 0; i < 50 ; i++) {
            switch (new Random().nextInt(5)){
                case 1:
                    if(coinsInPocket.containsKey(Enums.Currency.QUARTER.toString())){
                        coinsInPocket.put(Enums.Currency.QUARTER.toString(),
                                coinsInPocket.get(Enums.Currency.QUARTER.toString())+1);
                    }else {
                        coinsInPocket.put(Enums.Currency.QUARTER.toString(),1);
                    }
                    break;
                case 2:
                    if(coinsInPocket.containsKey(Enums.Currency.DIME.toString())){
                        coinsInPocket.put(Enums.Currency.DIME.toString(),
                                coinsInPocket.get(Enums.Currency.DIME.toString())+1);
                    }else {
                        coinsInPocket.put(Enums.Currency.DIME.toString(),1);
                    }
                    break;
                case 3:
                    if(coinsInPocket.containsKey(Enums.Currency.NICKEL.toString())){
                        coinsInPocket.put(Enums.Currency.NICKEL.toString(),
                                coinsInPocket.get(Enums.Currency.NICKEL.toString())+1);
                    }else {
                        coinsInPocket.put(Enums.Currency.NICKEL.toString(),1);
                    }
                    break;
                case 4:
                    if(coinsInPocket.containsKey(Enums.Currency.HALF_DOLLAR.toString())){
                        coinsInPocket.put(Enums.Currency.HALF_DOLLAR.toString(),
                                coinsInPocket.get(Enums.Currency.HALF_DOLLAR.toString())+1);
                    }else {
                        coinsInPocket.put(Enums.Currency.HALF_DOLLAR.toString(),1);
                    }
                    break;
                case 5:
                    if(coinsInPocket.containsKey(Enums.Currency.DOLLAR.toString())){
                        coinsInPocket.put(Enums.Currency.DOLLAR.toString(),
                                coinsInPocket.get(Enums.Currency.DOLLAR.toString())+1);
                    }else {
                        coinsInPocket.put(Enums.Currency.DOLLAR.toString(),1);
                    }
                    break;
                default:
                    i--;
            }

        }

    }

    // this will take coins from the customer so they will be used in the machine
    public void useMoney(String money){
        coinsInPocket.put(money,coinsInPocket.get(money)-1);
    }

    // once money is returned from the machine it will go back to the users pocket
    public void reciveMoney(Map<String,Integer> rCoins){
        for (String key: rCoins.keySet()) {
            if(coinsInPocket.containsKey(key)){
                coinsInPocket.put(key,coinsInPocket.get(key) + rCoins.get(key));
            }else {
                coinsInPocket.put(key,rCoins.get(key));
            }
        }
    }

    public void  reciveItem(String item){
        if (itemsBought.containsKey(item)){
            itemsBought.put(item,itemsBought.get(item)+1);
        }else {
            itemsBought.put(item,1);
        }
    }

    public Map<String, Integer> getCoinsInPocket() {
        return coinsInPocket;
    }

    public Map<String, Integer> getItemsBought() {
        return itemsBought;
    }
}
