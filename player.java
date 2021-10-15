package com.company;

public class player implements hand{

    private String playerName;
    private int money_in_bank;
    private int betting_amount;
    private blackJackHand hand;

    // Constructor function
    public player()
    {
        money_in_bank = 100;
        hand = new blackJackHand();
    }

    // Tell how much money the player has
    public int get_Money_in_bank()
    {
        return this.money_in_bank;
    }

    // subtracts the amount from player if they bust
    public void player_goes_Bust()
    {
        this.money_in_bank -= this.betting_amount;
        this.betting_amount = 0;
    }

    // Adds the amount if they win
    public void player_Wins_game()
    {
        this.money_in_bank += this.betting_amount;
        this.betting_amount = 0;
    }

    // Remove money if they lose
    public void player_losses_game()
    {
        this.money_in_bank -= this.betting_amount;
        this.betting_amount = 0;
    }

    // If player no longer wishes to play
    public void removeFromGame()
    {

        this.money_in_bank = -1;
    }

    // Reset the money to 0
    public void reset_money_in_Bank()
    {
        this.money_in_bank = 0;
    }

    // Set blackjack bet condition of 1.5 times the money they bet
    public void blackjackBet()
    {
        money += bet * 1.5;
        bet = 0;
    }

    // If player pushes this means they are not betting
    public void push()
    {
        bet = 0;
    }

    // set the player bets
    public void set_Bet_amount(int newBet)
    {
        this.betting_amount = newBet;
    }

    // Gets a player's bet
    public int get_Bet_amount()
    {
        return this.betting_amount;
    }

    // Sets a player's name
    public void set_player_Name(String name1)
    {
        this.playerName = name1;
    }

    // Gets a player's name
    public String get_player_Name()
    {
        return this.playerName;
    }

    // Gets a player's hand total
    public int getTotal()
    {
        return hand.Total();
    }



    // Adds a card to a player's hand
    public void addCard(Card card)
    {
        hand.addCard(card);

    }

    // Tells what cards the player has
    public String toString()
    {
        String str = "Cards are:" +
    }
    public String getHandString()
    {
        String str = "Cards:" + hand.toString();
        return str;
    }

    // Resets player hand
    public void resetPlayerHand()
    {
        hand.resetHand();
    }

}
