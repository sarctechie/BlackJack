package com.company;

public class Card
{

    public char suite;
    public int value;

    //constructor
    public Card(char suite, int value) {
        this.suite = suite;
        this.value = value;
    }

    public String toString() {
        return getSuiteName() + " " + this.value;
    }

    //return what suite the card is
    public String getSuiteName() {
        String suite;
        if (this.suite == 'H') {
            suite = "Hearts";
        }
        else if (this.suite == 'S') {
            suite = "Spades";
        }
        else if (this.suite == 'C') {
            suite = "Clubs";
        }
        else if (this.suite == 'D') {
            suite = "Diamonds";
        } else {
            suite = "Unknown";
        }
        return suite;
    }

    public char getSuiteDesignator() {
        return suite;
    }

    public String cardValue(){
        String cardStat = " ";
        if (this.value == 1) {
            cardStat = "Ace";
        }
        else if (this.value == 2) {
            cardStat = "Two";
        }
        else if (this.value == 3) {
            cardStat = "Three";
        }
        else if (this.value == 4) {
            cardStat = "Four";
        }
        else if (this.value == 5) {
            cardStat = "Five";
        }
        else if (this.value == 6) {
            cardStat = "Six";
        }
        else if (this.value == 7) {
            cardStat = "Seven";
        }
        else if (this.value == 8) {
            cardStat = "Eight";
        }
        else if (this.value == 9) {
            cardStat = "Nine";
        }
        else if (this.value == 10) {
            cardStat = "Ten";
        }
        else if (this.value == 11) {
            cardStat = "Jack";
        }
        else if (this.value == 12) {
            cardStat = "Queen";
        }
        else if (this.value == 13) {
            cardStat = "King";
        }
        return cardStat;
    }

    public int getValue() {
        return this.value;
    }

    //Compare the suites of a card
    public boolean compareSuite(Card card){
        return this.suite == card.getSuiteDesignator();
    }

    public boolean compareValue(Card card){
        return this.value == card.getValue();
    }

    public boolean compareTo(Card card){
        return this.suite == card.getSuiteDesignator() && this.value == card.getValue();
    }


}