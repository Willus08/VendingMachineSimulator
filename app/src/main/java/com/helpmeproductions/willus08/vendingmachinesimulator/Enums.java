package com.helpmeproductions.willus08.vendingmachinesimulator;



public class Enums {
    public enum Currency {
        PENNY("Penny", 1),
        NICKEL("Nickel",5),
        DIME("Dime",10),
        QUARTER("Quarter",25),
        HALF_DOLLAR("Half Dollar", 50),
        DOLLAR("Dollar", 100);


        private String stringValue;
        private int intValue;
        Currency(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }

        public int getValue(){
            return intValue;
        }
    }

//    public enum dollars{
//        ONE("One" ,100),
//        FIVE("Five", 500),
//        TEN("Ten", 1000),
//        TWENTY("Twenty", 2000);
//
//        private String stringValue;
//        private int intValue;
//        dollars(String toString, int value) {
//            stringValue = toString;
//            intValue = value;
//        }
//
//        @Override
//        public String toString() {
//            return stringValue;
//        }
//
//        public int getValue(){
//            return intValue;
//        }
//    }

    public enum items{
        COKE("Coke", 100),
        CANDY("Candy",65),
        CHIPS("Chips",50);

        private String stringValue;
        private int intValue;
        items(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }

        public int getValue(){
            return intValue;
        }

    }

}
