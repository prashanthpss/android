package com.gaana1mb.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int playerActive = 1; //1=red,2=yellow
    int gameStates[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int winningCombinations[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    Boolean gameActive = true;
    int playerWon = 0;
    int count = 0;


    public void playAgain(View view) {

        playerActive = 1; //1=red,2=yellow
        for (int i = 0; i < gameStates.length; i++) {
            gameStates[i] = 0;
        }
        gameActive = true;
        playerWon = 0;
        count = 0;

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.winningLayout);
        linearLayout.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    public void dropCoin(View view) {
        ImageView coin = (ImageView) view;
        count++;

        if (gameStates[Integer.parseInt(coin.getTag().toString())] == 0 && gameActive) {


            coin.setTranslationY(-1000f);
            gameStates[Integer.parseInt(coin.getTag().toString())] = playerActive;

            if (playerActive == 1) {
                playerActive = 2;
                coin.setImageResource(R.drawable.red_coin);
                coin.animate().translationYBy(1000f).setDuration(400);

            } else {
                playerActive = 1;
                coin.setImageResource(R.drawable.yellow_coin);
                coin.animate().translationYBy(1000f).setDuration(400);

            }


            for (int winningCombination[] : winningCombinations) {

                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.winningLayout);
                String winningMsg = "";

                if (gameStates[winningCombination[0]] != 0 && gameStates[winningCombination[0]] == gameStates[winningCombination[1]]
                        && gameStates[winningCombination[1]] == gameStates[winningCombination[2]]) {
                    gameActive = false;
                    playerWon = gameStates[winningCombination[0]];
                    if (playerWon == 1) {
                        winningMsg = "Red has Won!";
                    } else {
                        winningMsg = "Yellow has Won!";
                    }

                    TextView winningTextView = (TextView) findViewById(R.id.winningLabel);
                    winningTextView.setText(winningMsg);

                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout.setTranslationX(-1000f);
                    linearLayout.animate().translationXBy(1000f).setDuration(1000);

                } else if (playerWon==0 && count == 9) {
                    gameActive = false;
                    winningMsg = "It's a Tie!";

                    TextView winningTextView = (TextView) findViewById(R.id.winningLabel);
                    winningTextView.setText(winningMsg);

                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout.setTranslationX(-1000f);
                    linearLayout.animate().translationXBy(1000f).setDuration(1000);

                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
