package com.example.dreureka;

import android.widget.ImageView;

public class Card {
    //instance variables
    int cardNum;

    //holds all the picture for the cards
    int picNum[] = {R.drawable.p0, R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7,
            R.drawable.p8, R.drawable.p9, R.drawable.p10, R.drawable.p11, R.drawable.p12, R.drawable.p13, R.drawable.p14,
            R.drawable.p15, R.drawable.p16, R.drawable.p17, R.drawable.p18, R.drawable.p19, R.drawable.p20, R.drawable.p21,
            R.drawable.p22, R.drawable.p23, R.drawable.p24, R.drawable.p25, R.drawable.p26, R.drawable.p27, R.drawable.p28,
            R.drawable.p29, R.drawable.p30, R.drawable.p31, R.drawable.p32, R.drawable.p33, R.drawable.p34, R.drawable.p35,
            R.drawable.p36, R.drawable.p37, R.drawable.p39, R.drawable.p40, R.drawable.p41, R.drawable.p42, R.drawable.p43,
            R.drawable.p44, R.drawable.p45, R.drawable.p46, R.drawable.p47, R.drawable.p48, R.drawable.p49, R.drawable.p50,
            R.drawable.p51, R.drawable.p52, R.drawable.p53, R.drawable.p54, R.drawable.p55, R.drawable.p56, R.drawable.p57,
            R.drawable.p58, R.drawable.p59, R.drawable.p60, R.drawable.p61};

    //constructors
    public Card() {
        cardNum = (int) Math.random() * picNum.length - 1;
    }

    public Card(int CardNum) {
        cardNum = CardNum;
    }

    //accessors
    public int getCardNum() {
        return cardNum;
    }

    //return the picture
    public int getPic() {
        return picNum[cardNum];
    }

    public String toString() {
        return String.format("the card num is %d.", cardNum);
    }

    //Mutators
    public void setCardNum(int CardNum) {
        cardNum = CardNum;
    }


    //facilitators
    public boolean equals(Card card) {
        if (cardNum == card.getCardNum()) {
            return true;
        }
        return false;
    }

    // compare the card numbers
    public int compareTo(Card card) {
        if (cardNum > card.getCardNum()) {
            return 1;
        } else if (cardNum == card.getCardNum()) {
            return 0;
        }
        return -1;
    }

    // sets the pic
    public void setPic(ImageView picture) {
        picture.setImageResource(picNum[cardNum]);

    }
}
