package com.example.android.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String STATE_SCORETEAMA = "scoreTeamRed";
    static final String STATE_SCORETEAMB = "scoreTeamBlue";
    static final String STATE_INDEXA = "indexA";
    static final String STATE_INDEXB = "indexB";
    int scoreTeamRed = 0;
    int scoreTeamBlue = 0;
    int indexA = 0;
    int indexB = 0;
    int[] buttonsTeamRed = {R.id.red4, R.id.red3, R.id.red2, R.id.red1, R.id.red0};
    int[] buttonsTeamBlue = {R.id.blue4, R.id.blue3, R.id.blue2, R.id.blue1, R.id.blue0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCurrentTeam();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current score state
        savedInstanceState.putInt(STATE_SCORETEAMA, scoreTeamRed);
        savedInstanceState.putInt(STATE_SCORETEAMB, scoreTeamBlue);
        savedInstanceState.putInt(STATE_INDEXA, indexA);
        savedInstanceState.putInt(STATE_INDEXB, indexB);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        scoreTeamRed = savedInstanceState.getInt(STATE_SCORETEAMA);
        scoreTeamBlue = savedInstanceState.getInt(STATE_SCORETEAMB);
        indexA = savedInstanceState.getInt(STATE_INDEXA);
        indexB = savedInstanceState.getInt(STATE_INDEXB);
        setCurrentTeam();
        displayForTeamRed(scoreTeamRed);
        displayForTeamBlue(scoreTeamBlue);
    }

    /**
     * Displays the given score for Team Red.
     */
    public void addFourForTeamRed(View view) {
        scoreTeamRed += 4;
        nextRound();
        displayForTeamRed(scoreTeamRed);
    }

    public void addThreeForTeamRed(View view) {
        scoreTeamRed += 3;
        nextRound();
        displayForTeamRed(scoreTeamRed);
    }

    public void addTwoForTeamRed(View view) {
        scoreTeamRed += 2;
        nextRound();
        displayForTeamRed(scoreTeamRed);
    }

    public void addOneForTeamRed(View view) {
        scoreTeamRed += 1;
        nextRound();
        displayForTeamRed(scoreTeamRed);
    }

    public void addZeroForTeamRed(View view) {
        scoreTeamRed += 0;
        nextRound();
        displayForTeamRed(scoreTeamRed);
    }

    public void displayForTeamRed(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_red_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team Blue.
     */

    public void addFourForTeamBlue(View view) {
        scoreTeamBlue += 4;
        nextRound();
        displayForTeamBlue(scoreTeamBlue);
    }

    public void addThreeForTeamBlue(View view) {
        scoreTeamBlue += 3;
        nextRound();
        displayForTeamBlue(scoreTeamBlue);
    }

    public void addTwoForTeamBlue(View view) {
        scoreTeamBlue += 2;
        nextRound();
        displayForTeamBlue(scoreTeamBlue);
    }

    public void addOneForTeamBlue(View view) {
        scoreTeamBlue += 1;
        nextRound();
        displayForTeamBlue(scoreTeamBlue);
    }

    public void addZeroForTeamBlue(View view) {
        scoreTeamBlue += 0;
        nextRound();
        displayForTeamBlue(scoreTeamBlue);
    }

    public void displayForTeamBlue(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_blue_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Current team's turn and their buttons enabled/disabled. At the end of the 9th turn
     * both teams' buttons are disabled, and the winner is announced.
     */
    private void setCurrentTeam() {
        if ((indexA + indexB) % 2 != 0) {
            setButtonsEnabled(buttonsTeamRed, false, R.color.red, R.color.redTransparent);
            setButtonsEnabled(buttonsTeamBlue, true, R.color.blue, R.color.blueTransparent);
        } else {
            setButtonsEnabled(buttonsTeamRed, true, R.color.red, R.color.redTransparent);
            setButtonsEnabled(buttonsTeamBlue, false, R.color.blue, R.color.blueTransparent);
        }
        if (indexB == 9) {
            announceWinner();
            setButtonsEnabled(buttonsTeamRed, false, R.color.red, R.color.redTransparent);
            setButtonsEnabled(buttonsTeamBlue, false, R.color.blue, R.color.blueTransparent);
            displayReset("play again");
        }
    }

    /**
     * Set the team's buttons enabled/disabled, and update their colors accordingly.
     *
     * @param buttonsIds    Array of buttons' id.
     * @param enabled       Desired state of the buttons.
     * @param activeColor   The resource of the active color.
     * @param inactiveColor The resource of the inactive color.
     */
    public void setButtonsEnabled(int[] buttonsIds, boolean enabled, int activeColor, int inactiveColor) {
        for (int i = 0; i < buttonsIds.length; i++) {
            Button buttonView = (Button) findViewById(buttonsIds[i]);
            buttonView.setEnabled(enabled);
            buttonView.setBackgroundColor(getResources().getColor(enabled ? activeColor : inactiveColor));
        }
    }

    /**
     * Switch between teams.
     */
    private void nextRound() {
        if ((indexA + indexB) % 2 == 0) {
            indexA++;
        } else {
            indexB++;
        }
        setCurrentTeam();
    }

    /**
     * Announce winner.
     */
    private void announceWinner() {
        if (scoreTeamRed > scoreTeamBlue) {
            Toast.makeText(this, "A team won", Toast.LENGTH_LONG).show();
        } else if (scoreTeamRed < scoreTeamBlue) {
            Toast.makeText(this, "B team won", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Draw", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Reset the score for both teams.
     */
    public void displayReset(String reset) {
        Button resetView = (Button) findViewById(R.id.reset);
        resetView.setText(String.valueOf(reset));
    }

    public void resetScore() {
        scoreTeamRed = 0;
        scoreTeamBlue = 0;
        indexA = 0;
        indexB = 0;
        setButtonsEnabled(buttonsTeamRed, true, R.color.red, R.color.redTransparent);
        setButtonsEnabled(buttonsTeamBlue, false, R.color.blue, R.color.blueTransparent);
        displayForTeamRed(scoreTeamRed);
        displayForTeamBlue(scoreTeamBlue);
        displayReset("reset");
    }

    public void resetScoreButton(View view) {
        resetScore();
    }
}
