package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BlackjackGame bjg = new BlackjackGame();

        bjg.startGame();
        do {
            bjg.shuffle();
            bjg.getBets();
            bjg.deal();
            bjg.displayHand();
            bjg.checkWinningCondition();
            bjg.getUserMove();
            bjg.dealerMove();
            bjg.adjustBalance();
            bjg.displayBankBalance();
            bjg.discardHand();
        } while (bjg.nextMatch());
    }
}
