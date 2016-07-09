/**************************************************
 * Natallia Isayenka
 * 100744884
 * created: 29-Sep-2014
 * lastEdit: 21-Oct-2014
 **************************************************/


package ca.gbc.mobile.natalliaisayenka.matchit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ca.gbc.mobile.natalliaisayenka.matchit.db.ScoreDataSource;
import ca.gbc.mobile.natalliaisayenka.matchit.model.Player;


public class EnterScoreActivity extends Activity {

    public static boolean DEBUG = false;

    private Player player = new Player();

    private int score;

    private ScoreDataSource scoreDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_score);
        if (getParent()!=null){
            getParent().finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.enter_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override protected void onStart() {
        super.onStart();

        setContentView(R.layout.activity_enter_score);

        score = getIntent().getIntExtra("ca.gbc.mobile.NatalliaIsayenka.SCORE",99);

        player.setTime(getIntent().getStringExtra("ca.gbc.mobile.NatalliaIsayenka.TIME"));
        player.setTimeInMills(getIntent().getLongExtra("ca.gbc.mobile.NatalliaIsayenka.TIMEMILLS",0));

        TextView scoreTextView = (TextView) findViewById(R.id.scoreLabel);
        scoreTextView.setText("Your Score: " + player.getTime());


    }


    public void okMessage(View view) {



        player.setName((((TextView) findViewById(R.id.editText)).getText()).toString());



        scoreDataSource = new ScoreDataSource(this);
        scoreDataSource.open();
        scoreDataSource.addScore(player);
        scoreDataSource.close();

        Intent intent = new Intent(this, ScoreActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
