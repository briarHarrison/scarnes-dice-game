package com.example.demouser.scarnesdice;

import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
    }

    /**
     * generates an appropriate random number and updates to the corrosponding graphic
     * @param numSides the number of sides that the dice have. 6 is standard.
     * @return the random roll.
     */
    private int rollDice(int numSides) {
        int roll = random.nextInt(numSides) + 1; //random.nextInt generates [0-bound), so this will be [1-bound]
        displayDiceImage(roll);
        return roll;
    }

    public void displayDiceImage(int roll){

        ImageView image = (ImageView) findViewById(R.id.diceImage);

        switch (roll){
            case 1:  image.setImageDrawable(getDrawable(R.drawable.dice1));
                break;
            case 2:  image.setImageDrawable(getDrawable(R.drawable.dice2));
                break;
            case 3:  image.setImageDrawable(getDrawable(R.drawable.dice3));
                break;
            case 4:  image.setImageDrawable(getDrawable(R.drawable.dice4));
                break;
            case 5:  image.setImageDrawable(getDrawable(R.drawable.dice5));
                break;
            case 6:  image.setImageDrawable(getDrawable(R.drawable.dice6));
                break;
        }
    }
}
