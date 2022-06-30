package com.example.dreureka;


import android.widget.ImageView;

public class Ball {

    // instance variable
    private char colour;

    //constructors
    public Ball(){
        colour = 'e';
    }
    public Ball(char color)
    {
        this.setColour(color);
    }

    //accessors
    public char getColour(){
        return colour;
    }
    public int getPic(){
        if (colour == 'r')
            return R.drawable.red_ball;
        else if(colour == 'b')
            return R.drawable.blue_ball;
        else if(colour == 'g')
            return R.drawable.green_ball;
        else if(colour == 'e')
            return R.drawable.empty;
        return -1;
    }
    public String toString(){
        return String.format("the colour of the ball is %s.",colour);
    }

    //Mutator
    public void setColour(char color){
        colour = color;
    }
    // sets the picture of the ball to the image view
    public void setPic(ImageView view){
        if (colour == 'r')
            view.setImageResource(R.drawable.red_ball);
        else if(colour == 'b')
            view.setImageResource(R.drawable.blue_ball);
        else if(colour == 'g')
            view.setImageResource(R.drawable.green_ball);
        else if(colour == 'e')
            view.setImageResource(R.drawable.empty);
    }

    //facilitators
    public boolean equals(Ball ball){
        if(colour==ball.getColour()){
            return true;
        }
        return false;
    }
    //compares the colour of the balls
    public int compareTo(Ball ball){
        if(colour>ball.getColour()){
            return 1;
        }else if(colour == ball.getColour()){
            return 0;
        }
        return -1;
    }
}
