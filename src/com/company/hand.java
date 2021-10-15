package com.company;

public interface hand
{
    int Total();

    String toString();

    String toString(boolean isDealer, boolean hideHoleCard);

     void addCard(Card card);

     void resetHand();

     boolean check();
}