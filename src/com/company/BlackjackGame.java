package com.company;
import java.util.Scanner;

public class BlackjackGame implements cardGame {
	
	private Scanner input = new Scanner(System.in);
	private int userBJ; 
	private player[] regularplayer;
	private Deck deckBJ;
	private Dealer dealerBJ = new Dealer();
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[36m";
	public static final String someStuff = "\u001B[47m";
	public static final String ANSI_BLACK = "\u001B[30m";

	// Starts game and displays the rules
	public void startGame(){
		String names;
		System.out.println(ANSI_YELLOW+"Welcome to the game of Blackjack! Here are the rules"+ANSI_RESET);
		System.out.println("");
		System.out.println(ANSI_BLUE+"To start each round, the Dealer deals two cards to each player in alternating sequence.");
		System.out.println("Both of the cards that the Player is dealt are kept face up and known to both the Dealer and the Player.");
		System.out.println("The first card the Dealer is dealt is kept face up and known to both the Player and the Dealer; the second is kept face down and only known to the Dealer");
		System.out.println("The objective of the game is to accumulate a hand of cards that equals 21 (Blackjack!) or a hand that has a card value greater than your opponents without exceeding 21."+ANSI_RESET);

		
		// Gets the amount of players and creates them
		do {
			System.out.print(someStuff+ANSI_BLACK+"Please enter the number of players (max 6) ");
			userBJ = input.nextInt();
			

		} while (userBJ > 6 || userBJ < 0);

		regularplayer = new player[userBJ];
		deckBJ = new Deck();

		// Asks for player names and assigns them
		for (int i = 0; i < userBJ; i++) {
			System.out.print("Please enter the name of player " + (i + 1));
			names = input.next();
			regularplayer[i] = new player();
			regularplayer[i].setName(names);
		}
	}
	
	// Shuffles the deck
	public void shuffle(){
		deckBJ.shuffle();
		
	}

	// Gets the bets from the players
	public void getBets(){
		int betValue;
		
		for (int i =0; i < userBJ; i++) {  	
			if (regularplayer[i].getMoney() > 0) {
			do {
				System.out.print("Please enter the bet amount of " + regularplayer[i].getName()  + (" (1-" + regularplayer[i].getMoney()) + ")? " );
				betValue = input.nextInt();
				regularplayer[i].setBet(betValue);
			} while (!( betValue > 0 && betValue <= regularplayer[i].getMoney()));
			System.out.println(""+ANSI_RESET);
			}

		}

	}
	
	// Deals the cards to the players and dealer
	public void deal(){
		for (int j = 0; j < 2; j++) {
			for (int i =0; i < userBJ; i++) {
				if(regularplayer[i].getMoney() > 0)
				{
				regularplayer[i].addCard(deckBJ.nextCard());
				}
			}
			dealerBJ.addCard(deckBJ.nextCard());
		}
	}
	
	// Initial check for dealer or player Blackjack
	public void checkWinningCondition(){
		//System.out.println();

		if (dealerBJ.isBlackjack() ) {
			System.out.println("Dealer has BlackJack!");
			for (int i =0; i < userBJ; i++) {
				if (regularplayer[i].getTotal() == 21 ) {
					System.out.println(regularplayer[i].getName() + " pushes");
					regularplayer[i].push();
				} else {
					System.out.println(regularplayer[i].getName() + " loses");
					regularplayer[i].playerBust();
				}	
			}
		} else {
			if (dealerBJ.check() ) {
				System.out.println("Dealer checks and does not have a BlackJack");
			}

			for (int i =0; i < userBJ; i++) {
				if (regularplayer[i].getTotal() == 21 ) {
					System.out.println(regularplayer[i].getName() + " has blackjack!");
					regularplayer[i].blackjackBet();
				}
			}
		}		
	}
	
	// This code takes the user commands to hit or stand
	public void getUserMove() {
		String command;
		char c;
		for (int i = 0; i < userBJ; i++) {
			if ( regularplayer[i].getBet() > 0 ) {
				System.out.println();
				System.out.println(regularplayer[i].getName() + " has " + regularplayer[i].getHandString());

				do {
					do {
						System.out.print(regularplayer[i].getName() + " (H)it or (S)tand? ");
						command = input.next();
						c = command.toUpperCase().charAt(0);
					} while ( ! ( c == 'H' || c == 'S' ) );
					if ( c == 'H' ) {
						regularplayer[i].addCard(deckBJ.nextCard());
						System.out.println(regularplayer[i].getName() + " has " + regularplayer[i].getHandString());
					}
				} while (c != 'S' && regularplayer[i].getTotal() <= 21 );
			}
		}
	}
	
	// Code for the dealer to play
	public void dealerMove() {
		boolean isSomePlayerStillInTheGame = false;
		for (int i =0; i < userBJ && isSomePlayerStillInTheGame == false; i++){
			if (regularplayer[i].getBet() > 0 && regularplayer[i].getTotal() <= 21 ) {
				isSomePlayerStillInTheGame = true;
			}
		}
		if (isSomePlayerStillInTheGame) {
			dealerBJ.dealerPlay(deckBJ);
		}
	}
	
	// This code calculates all possible outcomes and adds or removes the player bets
	public void adjustBalance() {
		System.out.println();

		for (int i = 0; i < userBJ; i++) {
			if (regularplayer[i].getBet() > 0 ) {
				if( regularplayer[i].getTotal() > 21 ) {
					System.out.println(regularplayer[i].getName() + " has busted");
					regularplayer[i].playerBust();
				} else if ( regularplayer[i].getTotal() == dealerBJ.Total() ) {
					System.out.println(regularplayer[i].getName() + " has pushed");
					regularplayer[i].push();
				} else if ( regularplayer[i].getTotal() < dealerBJ.Total() && dealerBJ.Total() <= 21 ) {
					System.out.println(regularplayer[i].getName() + " has lost");
					regularplayer[i].loss();
				} else if (regularplayer[i].getTotal() == 21) {
					System.out.println(regularplayer[i].getName() + " has won with blackjack!");
					regularplayer[i].blackjackBet();
				} else {
					System.out.println(regularplayer[i].getName() + " has won");
					regularplayer[i].playerWin();
					
				}
			}
		}

	}

	// This prints the players hands
	public void displayHand() {
		for (int i = 0; i < userBJ; i++) {
			if(regularplayer[i].getMoney() > 0)
			{
			System.out.println(regularplayer[i].getName() + " has " + regularplayer[i].getHandString());
			}
		}
		System.out.println("Dealer has " + dealerBJ.getHandString(true, true));
	}
	
	// This prints the players banks and tells the player if s/he is out of the game
	public void displayBankBalance() {
		for (int i = 0; i < userBJ; i++) {
			if(regularplayer[i].getMoney() > 0)
			{
			System.out.println(regularplayer[i].getName() + " has " + regularplayer[i].getMoney());
			}
			if(regularplayer[i].getMoney() == 0)
			{
			System.out.println(regularplayer[i].getName() + " has " + regularplayer[i].getMoney() + " and is out of the game.");
			regularplayer[i].removeFromGame();
			}
		}
	}

	// This code resets all hands
	public void discardHand() {
		for (int i = 0; i < userBJ; i++) {
			regularplayer[i].resetPlayerHand();
		}
		dealerBJ.resetHand();

	}
	
	// This decides to force the game to end when all players lose or lets players choose to keep playing or not
	public boolean nextMatch() {
		String command;
		char c;
		Boolean playState = true;
		if(endGame()) {
			playState = false;	
		}
		else {
			do {
				System.out.println("");
				System.out.print("Do you want to play again (Y)es or (N)o? ");
				command = input.next();
				c = command.toUpperCase().charAt(0);
			} while ( ! ( c == 'Y' || c == 'N' ) );
			if(c == 'N')
			{
				playState = false;
			}
		}
		return playState;
	}
	
	// This says true or false to forcing the game to end
	public boolean endGame() {
		boolean end = false;
		int endCount = 0;
		
		for (int i = 0; i < userBJ; i++) {
			if(regularplayer[i].getMoney() == -1)
			{
				endCount++;
			}
		}
		if(endCount == userBJ)
		{
			end = true;
		}
		if(end)
		{
			System.out.println("");
			System.out.println("All players have lost and the game ends.");
		}
		
		return end;
	}
	
	// This is the endgame code for when all players are out of the game or players decide to stop playing
		public void finishMatch() {
			int endAmount;
			String endState = " no change.";
			System.out.println("");
			for (int i = 0; i < userBJ; i++) {
				if(regularplayer[i].getMoney() == -1)
				{
					regularplayer[i].resetBank();
				}
				endAmount = regularplayer[i].getMoney() - 100;
				if(endAmount > 0)
				{
					endState = " gain of ";
				}
				else if(endAmount < 0)
				{
					endState = " loss of ";
				}
				System.out.println(regularplayer[i].getName() + " has ended the game with " + regularplayer[i].getMoney() + ".");
				if(endState != " no change.")
				{
				System.out.println("A" + endState + Math.abs(endAmount) + ".");
				}
				else
				{
				System.out.println("No change from their starting value.");	
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("");
			System.out.println("Thank you for playing!");
		}


} //End class