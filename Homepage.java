package com.company;

import java.util.Scanner;

public class Homepage {
	public static void startGame() {
    	Scanner inputFromUser = new Scanner(System.in);
    	String userInput = "0";
    	System.out.println("Press 1 for Blackjack or 2 for ET.");
    	userInput = inputFromUser.nextLine();
    	if (userInput.equals("1")) {
        do {
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
    }while (userInput.equals(""));
        }
    	else if (userInput.equals("2")) {
            do {
            	TriantaEna TE = new TriantaEna();
            	 TE.startGame();
            	        do {
            	            TE.shuffle();
            	            TE.getBets();
            	            TE.deal();
            	            TE.displayHand();
            	            TE.checkWinningCondition();
            	            TE.getUserMove();
            	            TE.dealerMove();
            	            TE.adjustBalance();
            	            TE.displayBankBalance();
            	            TE.discardHand();
            	        } while (TE.nextMatch());
        }while (userInput.equals(""));
    	    }
    	else {
    		System.out.println("please enter EITHER 1 or 2 ");
    		startGame();
    	
    	}

	}}
