package com.helpmeproductions.willus08.vendingmachinesimulator;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MachineInformation {
    private Map<String,Integer> currencyPosseed; // the Currency in the machine
    private Map<String,Integer> itemsPossesed; // the items in the machine
    private Map<String,Integer> currencyInserted;
    private boolean exactChangeMode = false;

    public void createMachine(){
        currencyPosseed = new HashMap<>();
        itemsPossesed = new HashMap<>();
        currencyInserted = new HashMap<>();
        stockItems();
        stockCoins();
    }

    // this will put random amounts of each item in the machine once it is made
    public void stockItems() {
        int itemsNumber = new Random().nextInt(50);
        for (int i = 0; i <itemsNumber ; i++) {
            switch (new Random().nextInt(3)+1){
                case 1:
                    if(itemsPossesed.containsKey("Coke")){
                        itemsPossesed.put("Coke",itemsPossesed.get("Coke")+1);
                    }else {
                        itemsPossesed.put("Coke",1);
                    }
                    break;
                case 2:
                    if(itemsPossesed.containsKey("Candy")){
                        itemsPossesed.put("Candy",itemsPossesed.get("Candy")+1);
                    }else {
                        itemsPossesed.put("Candy",1);
                    }
                    break;
                case 3:
                    if(itemsPossesed.containsKey("Chips")){
                        itemsPossesed.put("Chips",itemsPossesed.get("Chips")+1);
                    }else {
                        itemsPossesed.put("Chips",1);
                    }
                    break;
                default:
                    i--;
            }

        }

    }

    // this will stock random amounts of Currency into the machine when made
    public void stockCoins() {

        for (int i = 0; i <50 ; i++) {
            switch (new Random().nextInt(5)+1){
                case 1:
                    if(currencyPosseed.containsKey(Enums.Currency.QUARTER.toString())){
                        currencyPosseed.put(Enums.Currency.QUARTER.toString(),
                                currencyPosseed.get(Enums.Currency.QUARTER.toString())+1);
                    }else {
                        currencyPosseed.put(Enums.Currency.QUARTER.toString(),1);
                    }
                    break;
                case 2:
                    if(currencyPosseed.containsKey(Enums.Currency.DIME.toString())){
                        currencyPosseed.put(Enums.Currency.DIME.toString(),
                                currencyPosseed.get(Enums.Currency.DIME.toString())+1);
                    }else {
                        currencyPosseed.put(Enums.Currency.DIME.toString(),1);
                    }
                    break;
                case 3:
                    if(currencyPosseed.containsKey(Enums.Currency.NICKEL.toString())){
                        currencyPosseed.put(Enums.Currency.NICKEL.toString(),
                                currencyPosseed.get(Enums.Currency.NICKEL.toString())+1);
                    }else {
                        currencyPosseed.put(Enums.Currency.NICKEL.toString(),1);
                    }
                    break;
                case 4:
                    if(currencyPosseed.containsKey(Enums.Currency.HALFDOLLAR.toString())){
                        currencyPosseed.put(Enums.Currency.HALFDOLLAR.toString(),
                                currencyPosseed.get(Enums.Currency.HALFDOLLAR.toString())+1);
                    }else {
                        currencyPosseed.put(Enums.Currency.HALFDOLLAR.toString(),1);
                    }
                    break;
                case 5:
                    if(currencyPosseed.containsKey(Enums.Currency.DOLLAR.toString())){
                        currencyPosseed.put(Enums.Currency.DOLLAR.toString(),
                                currencyPosseed.get(Enums.Currency.DOLLAR.toString())+1);
                    }else {
                        currencyPosseed.put(Enums.Currency.DOLLAR.toString(),1);
                    }
                    break;
                default:
                    i--;
            }

        }
        checkRemainingCoins();

    }

    // this will return all the currency the customer has put into the machine
    // if they change their mind and dont want to purchase anything
    public Map<String,Integer> returnCurrency(){
        Map<String,Integer> temp = new HashMap<>();
        for (String key: currencyInserted.keySet()) {
            temp.put(key,currencyInserted.get(key));
        }
        currencyInserted.clear();
        return temp;
    }

    // this will check if the item is in stock to be purchesed
    public boolean checkStock(String item){
        if (itemsPossesed.containsKey(item)){
            return itemsPossesed.get(item) > 0;
        }
        else return false;
    }

    // adds the inserted coins to the insertedCurrency map
    public void insertCoin(String curency){
        if (currencyInserted.containsKey(curency)){
            currencyInserted.put(curency, currencyInserted.get(curency)+1);
        }
        else {
            currencyInserted.put(curency,1);
        }
    }

    // this will check the remaining coins in the machine to see if wee need to be in exactchange mode
    public void checkRemainingCoins() {

        if(currencyPosseed.containsKey(Enums.Currency.QUARTER.toString())) {
            if (currencyPosseed.get(Enums.Currency.QUARTER.toString()) < 4){
                exactChangeMode = true;
            }
        }else {
            exactChangeMode = true;
        }

        if(currencyPosseed.containsKey(Enums.Currency.NICKEL.toString())) {
            if (currencyPosseed.get(Enums.Currency.NICKEL.toString()) < 4){
                exactChangeMode = true;
            }
        }else {
            exactChangeMode = true;
        }

        if(currencyPosseed.containsKey(Enums.Currency.DIME.toString())) {
            if (currencyPosseed.get(Enums.Currency.DIME.toString()) < 4){
                exactChangeMode = true;
            }
        }else {
            exactChangeMode = true;
        }
    }

    // checks to see if the machine is in exact change mode
    public boolean checkIfExactChangeRequired(){
        return exactChangeMode;
    }

    //checks how much has been inserted so far so it can be displayed
    public int calculateCoinsInserted(){
        int amount = 0;
        for (String key: currencyInserted.keySet()) {

            switch (Enums.Currency.valueOf(key.toUpperCase())) {
                case NICKEL:
                    amount += Enums.Currency.NICKEL.getValue() * currencyInserted.get(key);
                    break;
                case DIME:
                    amount += Enums.Currency.DIME.getValue() * currencyInserted.get(key);
                    break;
                case QUARTER:
                    amount += Enums.Currency.QUARTER.getValue() * currencyInserted.get(key);
                    break;
                case HALFDOLLAR:
                    amount += Enums.Currency.HALFDOLLAR.getValue() * currencyInserted.get(key);
                    break;
                case DOLLAR:
                    amount += Enums.Currency.DOLLAR.getValue() * currencyInserted.get(key);
                    break;
            }

        }
      return amount;
    }

    public Map<String,Integer> buyItem(String item){
        int paid = calculateCoinsInserted();
        itemsPossesed.put(item, itemsPossesed.get(item)-1);
        takeMoney();
        Map<String,Integer> temp = new HashMap<>();
        temp =  calculateReturn(item,paid);
        return temp;

    }

    // this will take the money inserted into the machine fully
    public void takeMoney(){

            for (String key: currencyInserted.keySet()) {

                if(currencyPosseed.containsKey(key)){
                    currencyPosseed.put(key, currencyPosseed.get(key)+ currencyInserted.get(key));
                }else{
                    currencyPosseed.put(key, currencyInserted.get(key));
                }

            }
            currencyInserted.clear();
    }

    // will calculate what money needs to be returned based on the value
    public Map<String,Integer> calculateReturn(String item, int paid){
        int cost=0;
        Map<String, Integer> rCoins = new HashMap<>();
        switch(Enums.items.valueOf(item.toUpperCase())){
            case COKE:
                cost = Enums.items.COKE.getValue();
                break;
            case CANDY:
                cost = Enums.items.CANDY.getValue();
                break;
            case CHIPS:
                cost = Enums.items.CHIPS.getValue();
                break;
        }

        int amountToReturn = paid - cost;
        while (amountToReturn > 0){
            if (amountToReturn - Enums.Currency.DOLLAR.getValue() >= 0) {
            if(currencyPosseed.containsKey(Enums.Currency.DOLLAR.toString())
                    && currencyPosseed.get(Enums.Currency.DOLLAR.toString()) > 0) {


                        if (rCoins.containsKey(Enums.Currency.DOLLAR.toString())) {
                            rCoins.put(Enums.Currency.DOLLAR.toString(),
                                    rCoins.get(Enums.Currency.DOLLAR.toString()) + 1);
                        } else {
                            rCoins.put(Enums.Currency.DOLLAR.toString(), 1);
                        }

                        amountToReturn -= Enums.Currency.DOLLAR.getValue();
                        currencyPosseed.put(Enums.Currency.DOLLAR.toString(),
                                currencyPosseed.get(Enums.Currency.DOLLAR.toString())-1);

                }
            }else if ( amountToReturn - Enums.Currency.HALFDOLLAR.getValue() >=0) {
                if(currencyPosseed.containsKey(Enums.Currency.HALFDOLLAR.toString())
                    && currencyPosseed.get(Enums.Currency.HALFDOLLAR.toString()) > 0){

                    if (rCoins.containsKey(Enums.Currency.HALFDOLLAR.toString())) {
                        rCoins.put(Enums.Currency.HALFDOLLAR.toString(),
                                rCoins.get(Enums.Currency.HALFDOLLAR.toString()) + 1);
                    } else {
                        rCoins.put(Enums.Currency.HALFDOLLAR.toString(), 1);
                    }
                    amountToReturn -= Enums.Currency.HALFDOLLAR.getValue();
                    currencyPosseed.put(Enums.Currency.HALFDOLLAR.toString(),
                            currencyPosseed.get(Enums.Currency.HALFDOLLAR.toString())-1);
                }
            }else  if ( amountToReturn - Enums.Currency.QUARTER.getValue() >=0){
                if(currencyPosseed.containsKey(Enums.Currency.QUARTER.toString())
                        && currencyPosseed.get(Enums.Currency.QUARTER.toString()) > 0){

                        if (rCoins.containsKey(Enums.Currency.QUARTER.toString())){
                            rCoins.put(Enums.Currency.QUARTER.toString(),
                                    rCoins.get(Enums.Currency.QUARTER.toString())+1);
                        }else{
                            rCoins.put(Enums.Currency.QUARTER.toString(),1);
                        }
                        amountToReturn -= Enums.Currency.QUARTER.getValue();
                        currencyPosseed.put(Enums.Currency.QUARTER.toString(),
                                currencyPosseed.get(Enums.Currency.QUARTER.toString())-1);
                    }
            }else if ( amountToReturn - Enums.Currency.DIME.getValue() >=0) {
                if(currencyPosseed.containsKey(Enums.Currency.DIME.toString())
                            && currencyPosseed.get(Enums.Currency.DIME.toString()) > 0){

                            if (rCoins.containsKey(Enums.Currency.DIME.toString())) {
                                rCoins.put(Enums.Currency.DIME.toString(),
                                        rCoins.get(Enums.Currency.DIME.toString()) + 1);
                            } else {
                                rCoins.put(Enums.Currency.DIME.toString(), 1);
                            }
                            amountToReturn -= Enums.Currency.DIME.getValue();
                            currencyPosseed.put(Enums.Currency.DIME.toString(),
                                    currencyPosseed.get(Enums.Currency.DIME.toString())-1);
                        }
            }else if(currencyPosseed.containsKey(Enums.Currency.NICKEL.toString())
                                && currencyPosseed.get(Enums.Currency.NICKEL.toString()) > 0){
                            if ( amountToReturn - Enums.Currency.NICKEL.getValue() >=0){
                                if (rCoins.containsKey(Enums.Currency.NICKEL.toString())){
                                    rCoins.put(Enums.Currency.NICKEL.toString(),
                                            rCoins.get(Enums.Currency.NICKEL.toString())+1);
                                }else{
                                    rCoins.put(Enums.Currency.NICKEL.toString(),1);
                                }
                                amountToReturn -= Enums.Currency.NICKEL.getValue();
                                currencyPosseed.put(Enums.Currency.NICKEL.toString(),
                                        currencyPosseed.get(Enums.Currency.NICKEL.toString())-1);

                            }
                        }
        }
        checkRemainingCoins();

        return rCoins;
    }

}
