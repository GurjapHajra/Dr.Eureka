package com.example.dreureka;

public class Tube {

    //instance variables
    private int count;
    private Ball data[] = new Ball[4];

    //constructors
    public Tube(){

        for(int i=0;i<4;i++){
            data[i]= new Ball();
        }
        count = 0;
    }
    public Tube(Ball ball){
        data[count] = ball;
        count++;
        for(int i = count;i<4;i++){
            data[i] = new Ball();
        }
    }

    //accessors
    public int size() {
        return count;
    }
    //return if the tube is full
    public boolean isFull(){
        return(count == data.length);
    }
    //returns the ball at ith possition
    public Ball getBallAt(int i){
        return data[i];
    }
    // return the top ball
    public Ball peek(){
        return data[count-1];
    }
    // return if the tube is empty
    public Boolean isEmpty(){
        if(count==0){
            return true;
        }
        return false;
    }
    //mutator
    public Ball pop(){
        count--;
        Ball returnthis = data[count];
        data[count] = new Ball();
        return returnthis;
    }
    // pushes a new ball in the tube
    public void push(Ball ball){
        data[count] = ball;
        if(ball.getColour()!='e')
            count++;
    }
    // clears the tube
    public void clear(){
        count = 0;
        for(int i=0;i<4;i++){
            data[i]= new Ball();
        }
    }
}
