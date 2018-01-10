package com.example.demouser.scarnesdice;

import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import java.util.Random;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DiceActivity extends AppCompatActivity {
    private Button rollBtn;
    private Button holdBtn;
    private Button resetBtn;
    private int currentTurnScore = 0;
    private int playerScore;
    private int compScore;
    private TextView userScoreDisplay;
    private TextView compScoreDisplay;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        rollBtn = (Button) findViewById(R.id.rollButton);
        //onClick listener for rollBtn
        rollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int score = rollDice(6);
                if (score == 1) {
                    endPlayerTurn(1, 0); //if a 1 is rolled - end turn with 0 added points
                }
                else {
                    currentTurnScore += score; //increment current score
                }
            }
        });

        holdBtn = (Button) findViewById(R.id.holdButton);
        //onClickListener for holdBtn
        holdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endPlayerTurn(1, currentTurnScore);
            }
        });

        resetBtn = (Button) findViewById(R.id.resetButton);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerScore = 0;
                compScore = 0;
            }
        });

        //grabbing TextView from view
        userScoreDisplay = (TextView) findViewById(R.id.userScore);
        compScoreDisplay = (TextView) findViewById(R.id.compScore);
    }

    /**
     * Method to add up the player's current score and reset the score
     * @param player Human player is 1, computer is 2
     * @param score
     */
    protected void endPlayerTurn(int player, int score) {
        switch (player) {
            case 1:
                playerScore += currentTurnScore;
                updateScore(1, playerScore);
                break;
            case 2:
                compScore += currentTurnScore;
                updateScore(2, compScore);
                break;
        }
        //reset current turn score
        currentTurnScore = 0;
    }

    /**
     * Method to check if the game is over
     */
    protected boolean checkGameStatus () {
        if (playerScore >= 100 || compScore >= 100) {
            return true;
        }
        return false;
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

    /**
     * Method to update player's score
     * @param player
     * @param score
     */
    protected void updateScore (int player, int score) {
        switch (player) {
            case 1: //human player
                userScoreDisplay.setText("Your Score: " + playerScore);
                break;
            case 2: //computer player
                compScoreDisplay.setText("My Score: " + compScore);
                break;
        }
    }
}
