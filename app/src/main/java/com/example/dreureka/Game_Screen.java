package com.example.dreureka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Game_Screen extends AppCompatActivity {
    int row = 4;
    // 3 tubes for the 3 column
    Tube tube1 = new Tube();
    Tube tube2 = new Tube();
    Tube tube3 = new Tube();
    int score = 0;

    // stores the pictures for each column
    ImageView pics1[] = new ImageView[row];
    ImageView pics2[] = new ImageView[row];
    ImageView pics3[] = new ImageView[row];

    // held stores the ball that is currently on hold
    Ball held = null;

    // initializes the deck
    Deck deck = new Deck();
    //runs when the Activity starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);


        //hides the notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hides the navigation bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        //pushes the default values into the tubes
        tube1.push(new Ball('r'));
        tube1.push(new Ball('r'));
        tube2.push(new Ball('b'));
        tube2.push(new Ball('b'));
        tube3.push(new Ball('g'));
        tube3.push(new Ball('g'));

        // Grid layout for each of the columns
        GridLayout g1 = (GridLayout) findViewById(R.id.grid1);
        GridLayout g2 = (GridLayout) findViewById(R.id.grid2);
        GridLayout g3 = (GridLayout) findViewById(R.id.grid3);

        // dimensions of the balls shown in the columns
        int height = 45;
        int width = 45;

        // margins for the of the balls shown in the columns
        int topMargin = 5;
        int bottomMargin = 5;
        int rightMargin = 10;
        int leftMargin = 10;

        //for each of the girview this set the images

        //for g1
        int m = 0;
        for (int i = 0; i < row; i++) {
            pics1[m] = new ImageView(this);
            setpic(pics1[m], m, 1);
            pics1[m].setId(m);
            //LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(100, 100);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = leftMargin;
            layoutParams.rightMargin = rightMargin;
            layoutParams.topMargin = topMargin;
            layoutParams.bottomMargin = bottomMargin;
            layoutParams.width = width;
            layoutParams.height = height;
            //pics[m].setLayoutParams(parms);
            pics1[m].setLayoutParams(layoutParams);
            pics1[m].setScaleType(ImageView.ScaleType.FIT_XY);
            g1.addView(pics1[m]);
            m++;
        }
        // g2
        m = 0;
        for (int i = 0; i < row; i++) {
            pics2[m] = new ImageView(this);
            setpic(pics2[m], m, 2);
            pics2[m].setId(m);
            //LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(100, 100);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = leftMargin;
            layoutParams.rightMargin = rightMargin;
            layoutParams.topMargin = topMargin;
            layoutParams.bottomMargin = bottomMargin;
            layoutParams.width = width;
            layoutParams.height = height;
            //pics[m].setLayoutParams(parms);
            pics2[m].setLayoutParams(layoutParams);
            pics2[m].setScaleType(ImageView.ScaleType.FIT_XY);
            g2.addView(pics2[m]);
            m++;
        }
        //g3
        m = 0;
        for (int i = 0; i < row; i++) {
            pics3[m] = new ImageView(this);
            setpic(pics3[m], m, 3);
            pics3[m].setId(m);
            //LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(100, 100);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = leftMargin;
            layoutParams.rightMargin = rightMargin;
            layoutParams.topMargin = topMargin;
            layoutParams.bottomMargin = bottomMargin;
            layoutParams.width = width;
            layoutParams.height = height;
            //pics[m].setLayoutParams(parms);
            pics3[m].setLayoutParams(layoutParams);
            pics3[m].setScaleType(ImageView.ScaleType.FIT_XY);
            g3.addView(pics3[m]);
            m++;
        }
        display();
    }

    //hides the navigation bar
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    // set the pictures on the grid views
    public void setpic(ImageView i, int pos, int gridNum) {
        // calculate the 2d positions of the button clicked
        if (gridNum == 1) {
            i.setImageResource(tube1.getBallAt(Math.abs(pos - 3)).getPic());
        } else if (gridNum == 2) {
            i.setImageResource(tube2.getBallAt(Math.abs(pos - 3)).getPic());
        } else if (gridNum == 3) {
            i.setImageResource(tube3.getBallAt(Math.abs(pos - 3)).getPic());
        }

    }
    // runs when grid 1 is clicked
    //code inside the click1 is the same in click2 and click3
    // with just different grids
    public void click1(View view) {
        Log.d("tag","click1 ran");
        // Makes an animation
        Animation slideDown;
        Animation slideUp;
        slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        //gets the id of the heldItem
        ImageView heldItem = findViewById(R.id.heldItem);
        // runns if there is no ball selected
        if (held == null && !(tube1.isEmpty())) {
            Log.d("tag","first if ran");
            // get the top item in the stack
            held = tube1.pop();
            // set the image to the held item
            heldItem.setImageResource(held.getPic());
            heldItem.startAnimation(slideDown);
            //runs if there is a ball selected
        } else if (held != null) {
            Log.d("tag","second if ran");
            tube1.push(held);
            heldItem.startAnimation(slideUp);
            heldItem.setImageResource(R.drawable.empty);
            held = null;
            //error message
        } else {
            Log.d("tag","else ran");
            Toast.makeText(getApplicationContext(), "Not possible", Toast.LENGTH_SHORT).show();
        }
        //redaws after the changes have been made
        redraw();
    }
    // runs when grid 2 is clicked
    public void click2(View view) {
        // Makes an animation
        Animation slideDown;
        Animation slideUp;
        slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        ImageView heldItem = findViewById(R.id.heldItem);
        if (held == null && !(tube2.isEmpty())) {
            // get the top item in the stack
            held = tube2.pop();
            // set the image to the held item
            heldItem.setImageResource(held.getPic());
            heldItem.startAnimation(slideDown);
        } else if (held != null) {
            tube2.push(held);
            heldItem.startAnimation(slideUp);
            heldItem.setImageResource(R.drawable.empty);
            held = null;
        } else {
            Toast.makeText(getApplicationContext(), "Not possible", Toast.LENGTH_SHORT).show();
        }
        redraw();
    }
    // runs when grid 3 is clicked
    public void click3(View view) {
        // Makes an animation
        Animation slideDown;
        Animation slideUp;
        slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        ImageView heldItem = findViewById(R.id.heldItem);
        if (held == null && !(tube3.isEmpty())) {
            // get the top item in the stack
            held = tube3.pop();
            // set the image to the held item
            heldItem.setImageResource(held.getPic());
            heldItem.startAnimation(slideDown);
        } else if (held != null && !(tube3.isFull())) {
            tube3.push(held);
            heldItem.startAnimation(slideUp);
            heldItem.setImageResource(R.drawable.empty);
            held = null;
        } else {
            Toast.makeText(getApplicationContext(), "Not possible", Toast.LENGTH_SHORT).show();
        }
        redraw();
    }
    // redraw the grid
    public void redraw() {
        // draw the first column
        pics1[0].setImageResource(tube1.getBallAt(3).getPic());
        pics1[1].setImageResource(tube1.getBallAt(2).getPic());
        pics1[2].setImageResource(tube1.getBallAt(1).getPic());
        pics1[3].setImageResource(tube1.getBallAt(0).getPic());
        // draws the second column
        pics2[0].setImageResource(tube2.getBallAt(3).getPic());
        pics2[1].setImageResource(tube2.getBallAt(2).getPic());
        pics2[2].setImageResource(tube2.getBallAt(1).getPic());
        pics2[3].setImageResource(tube2.getBallAt(0).getPic());
        // draws the third comumn
        pics3[0].setImageResource(tube3.getBallAt(3).getPic());
        pics3[1].setImageResource(tube3.getBallAt(2).getPic());
        pics3[2].setImageResource(tube3.getBallAt(1).getPic());
        pics3[3].setImageResource(tube3.getBallAt(0).getPic());
        // shows the score
        TextView s = findViewById(R.id.scoreB);
        s.setText("Score: " + score);
    }
    // resets the grid
    public void reset(View view) {
        // clears all the columns
        tube1.clear();
        tube2.clear();
        tube3.clear();
        // pushes the default values of the into the columns
        tube1.push(new Ball('r'));
        tube1.push(new Ball('r'));
        tube2.push(new Ball('b'));
        tube2.push(new Ball('b'));
        tube3.push(new Ball('g'));
        tube3.push(new Ball('g'));
        held = null;
        ImageView heldItem = findViewById(R.id.heldItem);
        heldItem.setImageResource(R.drawable.empty);
        //resets the score
        score = 0;
        //redraws
        redraw();
    }
    // displays any changes
    public void display() {
        // if the deck is empty we display the next card
        if (!deck.isEmpty()) {
            Card c = deck.pop();
            ImageView i = (ImageView) findViewById(R.id.cardImage);
            i.setImageResource(c.getPic());
        }//if the deck is empty it shuffles and displays the next card
        else {
            deck.shuffle();
            display();
        }
    }
    //shows the next card
    public void next(View view) {
        display();
        score++;
        TextView s = findViewById(R.id.scoreB);
        s.setText("Score: " + score);
        // check if won
        if (win(score)) {
            Toast.makeText(getApplicationContext(), "Congrats, you win!", Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 2s = 2000ms
                    Toast.makeText(getApplicationContext(), "CLick reset to start again or keep on going", Toast.LENGTH_SHORT).show();
                }
            }, 5000);
        }
    }
    //shuffles the card
    public void shuffleClick(View view) {
        deck.shuffle();
        display();
    }
    // goes to the instructions screen
    public void ins(View view){
        Intent i = new Intent(this, intructions.class);
        startActivity(i);
    }
    // save the current position of the grid and the score
    public void save(View view) {
        //tries to save the
        try {
            // first three files for each of the columns and last file for extra information
            FileOutputStream out1 = openFileOutput("data1.txt", Activity.MODE_PRIVATE);
            FileOutputStream out2 = openFileOutput("data2.txt", Activity.MODE_PRIVATE);
            FileOutputStream out3 = openFileOutput("data3.txt", Activity.MODE_PRIVATE);
            FileOutputStream out4 = openFileOutput("data4.txt", Activity.MODE_PRIVATE);

            for(int i=0;i<tube1.size();i++) {
                out1.write(tube1.getBallAt(i).getColour());
            }
            for(int i=0;i<tube2.size();i++) {
                out2.write(tube2.getBallAt(i).getColour());
            }
            for(int i=0;i<tube3.size();i++) {
                out3.write(tube3.getBallAt(i).getColour());
            }
            out4.write(score);

            out1.flush();
            out1.close();
            out2.flush();
            out2.close();
            out3.flush();
            out3.close();

        }
        //catches if it does work
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // open the sved grid and the score
    public void open(View view) {
        // tries to open the files
        try{
            FileInputStream in1 = openFileInput("data1.txt");
            FileInputStream in2 = openFileInput("data2.txt");
            FileInputStream in3 = openFileInput("data3.txt");
            FileInputStream in4 = openFileInput("data4.txt");

            tube1.clear();
            tube2.clear();
            tube3.clear();

            add(tube1, in1.read());
            add(tube1, in1.read());
            add(tube1, in1.read());
            add(tube1, in1.read());
            Log.d("tag","size 1"+tube1.size());

            add(tube2, in2.read());
            add(tube2, in2.read());
            add(tube2, in2.read());
            add(tube2, in2.read());

            add(tube3, in3.read());
            add(tube3, in3.read());
            add(tube3, in3.read());
            add(tube3, in3.read());

            score = in4.read();

            redraw();
            held = null;
        }
        // catches if gets an error
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // add adds ball after reading from a file
    public void add(Tube t, int a){
        // e is for empty
        if(a==-1)
            t.push(new Ball('e'));
        else
            t.push(new Ball((char)a));
    }
    // checks if the user has won
    public Boolean win(int s) {
        // score is 10 then the user win
        if (s == 10) {
            return true;
        } else {
            return false;
        }
    }
    // Ai does a move also recusive
    public void help(View view) {
        // calles the helper method
        helper();
    }
    // to make the method recusive I have to remove the View view by making a new method
    public void helper(){
        // generates radom moves unit it finds one that is valid
        int r1, r2;
        do {
            r1 = (int) (Math.random() * 3) + 1;
            r2 = (int) (Math.random() * 3) + 1;
        } while (r1 == r2);
        Log.d("mytag", r1 + ", " + r2);

        if (r1 == 1 && !(tube1.isEmpty())) {
            switch (r2) {
                case 2:
                    if (!(tube2.isFull())&&!(tube1.isEmpty()))
                        tube2.push(tube1.pop());
                case 3:
                    if (!(tube3.isFull())&&!(tube1.isEmpty()))
                        tube3.push(tube1.pop());
            }
        } else if (r1 == 2 && !(tube2.isEmpty())) {
            switch (r2) {
                case 1:
                    if (!(tube1.isFull())&&!(tube2.isEmpty()))
                        tube1.push(tube2.pop());
                case 3:
                    if (!(tube3.isFull())&&!(tube2.isEmpty()))
                        tube3.push(tube2.pop());
            }
        } else if (r1 == 3 && !(tube3.isEmpty())) {
            switch (r2) {
                case 2:
                    if (!(tube2.isFull())&&!(tube1.isEmpty()))
                        tube2.push(tube3.pop());
                case 1:
                    if (!(tube1.isFull())&&!(tube1.isEmpty()))
                        tube1.push(tube3.pop());
            }
        }else{
            helper();
        }
        redraw();
    }
}
