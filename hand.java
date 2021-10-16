package com.company;

public interface hand
{
    int Total();

    String toString();

    String toString(boolean a, boolean b);

     void addCard(Card card);

     void resetHand();

     boolean check();
}