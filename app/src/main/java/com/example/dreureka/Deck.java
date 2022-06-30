package com.example.dreureka;


public class Deck {

    //instance variables
    private int count;
    private Card data[] = new Card[70];

    public Deck(){
        shuffle();
    }
    public Deck(Card card){
        data[count] = card;
        count++;
    }
    // pushes the new card into the deck
    public void push(Card addMe) {
        data[count] = addMe;
        count++;
    }
    //return the size
    public int size() {
        return count;
    }
    //returns the if the deck is full
    public boolean isFull() {
        return (count == data.length);
    }
    public Card pop() {
        count--;
        return data[count];
    }
    // return the top card
    public Card peek() {
        return data[count-1];
    }
    // returns if the deck is empty
    public boolean isEmpty() {
        return count == 0;
    }
    // clears the deck
    public void clear() {
        count = 0;
    }
    public void shuffle(){
        //TO DO: Make an array for each instance variable. A card's pieces are all in the same index
        int cardNum[] = new int [62];
        for(int i=0;i<61;i++){
            cardNum[i] = i;
        }
        //TO DO: Randomize the order of the arrays
        for (int i = 0; i < 100; i++) {
            int r1 = (int) (Math.random() * cardNum.length);
            int r2 = (int) (Math.random() * cardNum.length);
            int temp = cardNum[r1];
            cardNum[r1] = cardNum[r2];
            cardNum[r2] = temp;

        }
        count = 0;
        //TO DO: push all (now in random order) into the Deck
        for (int i = 0; i < cardNum.length; i++) {
            Card c = new Card(cardNum[i]);
            push(new Card(cardNum[i]));
        }
    }

}
