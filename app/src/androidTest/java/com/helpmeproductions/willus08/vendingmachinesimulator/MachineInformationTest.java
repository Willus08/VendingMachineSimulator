package com.helpmeproductions.willus08.vendingmachinesimulator;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MachineInformationTest {
    private Enums sets;
    private Enums.Currency money;
    private Enums.items items;
    private MachineInformation vendingMachine;
    String dollar;
    int dollarValue;
    String halfDollar;
    String chips;
    Map<String,Integer> returnedMoney;
    @Before
    public void setup(){
        vendingMachine= new MachineInformation();
        vendingMachine.createMachine();
        vendingMachine.stockCoins();
        vendingMachine.stockItems();
         dollar = Enums.Currency.DOLLAR.toString();
         dollarValue = Enums.Currency.DOLLAR.getValue();
         halfDollar = Enums.Currency.HALFDOLLAR.toString();
        chips = Enums.items.CHIPS.toString();
        returnedMoney = new HashMap<>();
    }

    @Test
    public void machineShouldReturnCoins(){
        vendingMachine.insertCoin(dollar);
        returnedMoney = vendingMachine.returnCurrency();
        assertEquals(true,returnedMoney.containsKey(dollar));
    }

    @Test
    public void machineShouldMakeChange(){
        vendingMachine.insertCoin(dollar);
        vendingMachine.takeMoney();
        returnedMoney = vendingMachine.calculateReturn(chips,dollarValue);
        assertEquals(true,returnedMoney.containsKey(halfDollar));
    }

}
