package blackjack;
import java.util.Scanner;


public class BlackjackGame {
	
	private Scanner input = new Scanner(System.in);
	private int userBJ; 
	private Player[] regularplayer;
	private Deck deckBJ;
	private Dealer dealerBJ = new Dealer();

	// Starts game and displays the rules
	public void startBJGame(){
		String names;
		System.out.println("Welcome to the game of Blackjack! Here are the rules");
		System.out.println("");
		System.out.println("	To start each round, the Dealer deals two cards to each player in alternating sequence.");
		System.out.println("	Both of the cards that the Player is dealt are kept face up and known to both the Dealer and the Player.");
		System.out.println("	The first card the Dealer is dealt is kept face up and known to both the Player and the Dealer; the second is kept face down and only known to the Dealer");
		System.out.println("	The objective of the game is to accumulate a hand of cards that equals 21 (Blackjack!) or a hand that has a card value greater than your opponents without exceeding 21.");
		System.out.println("");
		System.out.println("");
		
		// Gets the amount of players and creates them

		System.out.print("Please enter the number of players (max 6) ");
		userBJ = input.nextInt();
		while (userBJ < 0 && userBJ > 6)
		{
			System.out.println("Please enter the valid number of players. Players must be less than 6 and more than 0.");
			userBJ = input.nextInt();
		}

		regularplayer = new Player[userBJ];
		deckBJ = new Deck();

		// Asks for player names and assigns them
		for (int i = 0; i < userBJ; i++) {
			System.out.print("Please enter the name of player " + " " + (i + 1));
			names = input.next();
			regularplayer[i] = new Player();
			regularplayer[i].set_player_Name(names);
		}

	}
	
	// Shuffles the deck
	public void shuffle_deck()
	{
		deckBJ.deck_shuffle();

	}

	// Gets the bets from the players

	public void get_bets_from_players()
	{
		int bet;
		for (int i =0; i < userBJ; i++)
		{
			if (regularplayer[i].get_Money_in_bank() > 0)
			{
				do
				{
					System.out.println( regularplayer[i].getName() + " " + "Please kindly enter the bet amount. " + "Your bet should be less than  " + regularplayer[i].get_Money_in_bank());
					bet = input.nextInt();
					regularplayer[i].set_Bet_amount(bet);
				} while (!( bet > 0 && bet <= regularplayer[i].get_Money_in_bank()));
				System.out.println("\n");
			}
		}
	}

	
	// Deals the cards to the players and dealer
	public void deal_cards_to_palyers()
	{
		for (int j = 0; j < 2; j++)
		{
			for (int i =0; i < userBJ; i++)
			{
				if(regularplayer[i].get_Money_in_bank() > 0)
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
					regularplayer[i].bust();
				}	
			}
		} else {
			if (dealerBJ.peek() ) {
				System.out.println("Dealer peeks and does not have a BlackJack");
			}

			for (int i =0; i < userBJ; i++) {
				if (regularplayer[i].getTotal() == 21 ) {
					System.out.println(regularplayer[i].getName() + " has blackjack!");
					regularplayer[i].blackjack();
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
					regularplayer[i].bust();
				} else if ( regularplayer[i].getTotal() == dealerBJ.calculateTotal() ) {
					System.out.println(regularplayer[i].getName() + " has pushed");
					regularplayer[i].push();
				} else if ( regularplayer[i].getTotal() < dealerBJ.calculateTotal() && dealerBJ.calculateTotal() <= 21 ) {
					System.out.println(regularplayer[i].getName() + " has lost");
					regularplayer[i].loss();
				} else if (regularplayer[i].getTotal() == 21) {
					System.out.println(regularplayer[i].getName() + " has won with blackjack!");
					regularplayer[i].blackjack();
				} else {
					System.out.println(regularplayer[i].getName() + " has won");
					regularplayer[i].win();
					
				}
			}
		}

	}

	// This prints the players hands
	public void displayHand() {
		for (int i = 0; i < userBJ; i++) {
			if(regularplayer[i].getBank() > 0)
			{
			System.out.println(regularplayer[i].getName() + " has " + regularplayer[i].getHandString());
			}
		}
		System.out.println("Dealer has " + dealerBJ.getHandString(true, true));
	}
	
	// This prints the players banks and tells the player if s/he is out of the game
	public void displayBankBalance() {
		for (int i = 0; i < userBJ; i++) {
			if(regularplayer[i].getBank() > 0)
			{
			System.out.println(regularplayer[i].getName() + " has " + regularplayer[i].getBank());
			}
			if(regularplayer[i].getBank() == 0)
			{
			System.out.println(regularplayer[i].getName() + " has " + regularplayer[i].getBank() + " and is out of the game.");
			regularplayer[i].removeFromGame();
			}
		}
	}

	// This code resets all hands
	public void discardHand() {
		for (int i = 0; i < userBJ; i++) {
			regularplayer[i].clearHand();
		}
		dealerBJ.clearHand();

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
	//endGame is changed to is_Game_Finished
	public boolean is_Game_Finished() {
		boolean finish = false;
		int Count = 0;

		for (int i = 0; i < userBJ; i++)
		{
			if(regularplayer[i].get_Money_in_bank() == -1)
			{
				Count++;
			}
		}
		if(Count == userBJ)

		{
			finish = true;
			System.out.println("All players have lost and the game ends.");
		}

		return finish;
	}


	// This is the endgame code for when all players are out of the game or players decide to stop playing
//	finishMatch changed to finish game
	public void finish_game() {
		int amount_left;
		String endState = " no change.";
		System.out.println("");
		for (int i = 0; i < userBJ; i++) {
			if(regularplayer[i].get_Money_in_bank() == -1)
			{
				regularplayer[i].reset_money_in_Bank();
			}
			amount_left = regularplayer[i].get_Money_in_bank() - 100;
			if(amount_left > 0)
			{

				System.out.println(regularplayer[i].get_player_Name() + "has profit of " + " " + amount_left + " and has ended the game with " + regularplayer[i].get_Money_in_bank() + ".");
			}
			else if(amount_left < 0)
			{
				System.out.println(regularplayer[i].get_player_Name() + "has loss of " + " " + Math.abs(amount_left) + " and has ended the game with " + regularplayer[i].get_Money_in_bank() + ".");

			}

			else if(amount_left == 0)
			{
				System.out.println(regularplayer[i].get_player_Name() + "has no loss or profit and has ended the game with " + regularplayer[i].get_Money_in_bank() + ".");

			}

		}
//		System.out.println("");
//		System.out.println("");
		System.out.println("Thank you for playing the game. Hope you enjoyed the game!");
	}


} //End class