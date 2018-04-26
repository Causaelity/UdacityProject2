package net.drunkdogs.scorekeeper;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Setup variables and constants
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    final int TOUCHDOWN = 6;
    final int FIELDGOAL = 3;
    final int EXTRAPOINT = 1;
    final int SAFTEYPOINT =2;

    // Used for UndoList
    ArrayList<Pair> undoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the screen to portrait only. also could add to AndroidManifest
        // android:screenOrientation="portrait"
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * This following methods will update the score and display
     */
    public void touchdownTeamA(View view){
        scoreTeamA += TOUCHDOWN;
        addToUndoList(TOUCHDOWN, "TeamA");
        displayForTeamA(scoreTeamA);
    }

    public void fieldGoalTeamA(View view){
        scoreTeamA += FIELDGOAL;
        addToUndoList(FIELDGOAL, "TeamA");
        displayForTeamA(scoreTeamA);
    }

    public void extraPointTeamA(View view){
        scoreTeamA += EXTRAPOINT;
        addToUndoList(EXTRAPOINT, "TeamA");
        displayForTeamA(scoreTeamA);
    }

    public void safetyTeamA(View view){
        scoreTeamA += SAFTEYPOINT;
        addToUndoList(SAFTEYPOINT, "TeamA");
        displayForTeamA(scoreTeamA);
    }

    public void touchdownTeamB(View view){
        scoreTeamB += TOUCHDOWN;
        addToUndoList(TOUCHDOWN, "TeamB");
        displayForTeamB(scoreTeamB);
    }

    public void fieldGoalTeamB(View view){
        scoreTeamB += FIELDGOAL;
        addToUndoList(FIELDGOAL, "TeamB");
        displayForTeamB(scoreTeamB);
    }

    public void extraPointTeamB(View view){
        scoreTeamB += EXTRAPOINT;
        addToUndoList(EXTRAPOINT, "TeamB");
        displayForTeamB(scoreTeamB);
    }

    public void safetyTeamB(View view){
        scoreTeamB += SAFTEYPOINT;
        addToUndoList(SAFTEYPOINT, "TeamB");
        displayForTeamB(scoreTeamB);
    }


    // Reset everything
    public void resetScore(View view){
        scoreTeamA = 0;
        scoreTeamB = 0;
        undoList.clear();
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    /**
     * Add last score and team to the stack
     * A Pair is also known as a Tuple in other languages
     */
    private void addToUndoList (int value, String team) {
        Pair<Integer, String> pair = new Pair<>(value, team);
        undoList.add(pair);
    }

    /**
     * If UndoList isn't empty pop the last value and update value for the applicable team
     * If the stack is empty let the user know there's nothing to Undo
     */
    public void removeLastScore(View view) {
        if (!undoList.isEmpty()) {

            int lastIndex = undoList.size() - 1;
            Pair<?,?> undoPair =  undoList.get(lastIndex);
            undoList.remove(lastIndex);

            // Determine team A or B
            if (undoPair.second.equals("TeamA")) {
                scoreTeamA -= (Integer) undoPair.first;
                displayForTeamA(scoreTeamA);
            } else if (undoPair.second.equals("TeamB")) {
                scoreTeamB -= (Integer) undoPair.first;
                displayForTeamB(scoreTeamB);
            }

        } else {
            // array is empty, let user know there's nothing left to Undo
            Toast toast = Toast.makeText(getApplicationContext(), "Nothing to Undo", Toast.LENGTH_SHORT);

            // Use below code to customize notification colors if desired
//            View toastView = toast.getView();
//            toastView.setBackgroundColor(Color.GREEN);
//            TextView text = toastView.findViewById(android.R.id.message);
//            text.setTextColor(Color.WHITE);

            toast.setGravity(Gravity.CENTER, 0, -210);
            toast.show();
        }
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

}
