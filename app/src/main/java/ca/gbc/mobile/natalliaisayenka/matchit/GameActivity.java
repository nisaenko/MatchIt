/**************************************************
 * Natallia Isayenka
 * 100744884
 * created: 29-Sep-2014
 * lastEdit: 5-Oct-2014
 **************************************************/

package ca.gbc.mobile.natalliaisayenka.matchit;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import ca.gbc.mobile.natalliaisayenka.matchit.model.Deck;
import ca.gbc.mobile.natalliaisayenka.matchit.model.Pair;
import ca.gbc.mobile.natalliaisayenka.matchit.widget.CardView;


public class GameActivity extends Activity {

    public static boolean DEBUG = false;

    private int mc_counter = 0;
    private int firstid = 0;
    private int secondid = 0;
    private Boolean mc_isfirst = false;

    private int correctcounter = 0;

    private Deck deck;
    private boolean gameStopped;


    private Handler customHandler = new Handler();
    private TextView timerValue;
    private long startTime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    private int previousPosition;


    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    private void initGame() {
        // init Deck
        deck = new Deck();

        //set gameStopped status to false, game is started there
        gameStopped = false;

        deck.init();
        deck.shuffle();

        CardView currentCardview;

        for (int i = 0; i < 12; i++) {

            currentCardview = (CardView) findViewById(deck.getResourceByID(i));
            deck.setCardViewItem(i, currentCardview);
            currentCardview.setBackgroundResource(deck.gedImgResourceByID(i, 0));
            currentCardview.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int i = 0;
                    for (int n = 0; n < 12; n++) {
                        if (deck.getResourceByID(n) == view.getId())
                            i = n;
                    }

                    doClickAction(view, i);

                }
            });
        }

    }

    private void enableGamePane() {
        for (CardView b : deck.getCardViews()) {

            b.setEnabled(true);

        }

    }

    private void disableGamePane() {
        for (CardView b : deck.getCardViews()) {

            b.setEnabled(false);

        }
    }

    private void doClickAction(View v, int i)

    {
        if (i == previousPosition){
            return;
        }
        previousPosition = i;

        v.setBackgroundResource(deck.gedImgResourceByID(i, 1));
        mc_isfirst = !mc_isfirst;

        // disable all buttons
        for (CardView b : deck.getCardViews()) {
            b.setEnabled(false);
        }

        if (mc_isfirst) {
            // turning the first card

            firstid = i;
            // re enable all except this one
            for (CardView b : deck.getCardViews()) {
                if (b.getId() != firstid) {
                    b.setEnabled(true);
                }
            }

        } else {
            // turning the second card
            secondid = i;

            doPlayMove();
        }

    }

    private void doPlayMove() {

        mc_counter++;

        if (deck.gedImgResourceByID(firstid, 1) - deck.gedImgResourceByID(secondid, 1) == 0) {
            // correct
            Pair pair = new Pair();
            pair.setFirst(deck.getCardByID(firstid));
            pair.setSecond(deck.getCardByID(secondid));
            deck.getPairs().add(pair);

            waiting(200);
            deck.getCardViewByID(firstid).setVisibility(View.INVISIBLE);
            deck.getCardViewByID(secondid).setVisibility(View.INVISIBLE);
            correctcounter++;

        } else {

            waiting(400);
        }

        // re-enable and turn cards back
        for (CardView b : deck.getCardViews()) {
            if (b.getVisibility() != View.INVISIBLE) {
                b.setEnabled(true);
                b.setBackgroundResource(R.drawable.memory_back);
                for (int i = 0; i < 12; i++) {
                    deck.getCardViewByID(i).setBackgroundResource(deck.gedImgResourceByID(i, 0));
                }
            }
        }



        if (correctcounter > 5) {

            Intent iSc = new Intent(getApplicationContext(), EnterScoreActivity.class);
            iSc.putExtra("ca.gbc.mobile.NatalliaIsayenka.SCORE", mc_counter);
            iSc.putExtra("ca.gbc.mobile.NatalliaIsayenka.TIME", timerValue.getText());
            iSc.putExtra("ca.gbc.mobile.NatalliaIsayenka.TIMEMILLS",timeInMilliseconds);
            //iSc.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            iSc.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(iSc);

        }

    }

    public static void waiting(int n) {
        long t0, t1;
        t0 = System.currentTimeMillis();
        do {
            t1 = System.currentTimeMillis();
        } while ((t1 - t0) < (n));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
        timerValue = (TextView) findViewById(R.id.chronometer);

        initGame();

        if (getParent()!=null){
            getParent().finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
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

    public void stopResumeMessage(View view) {
        //Chronometer chronometer =  (Chronometer) findViewById(R.id.chronometer);
        if (gameStopped) {
            //start timer there
            enableGamePane();
            startTime = SystemClock.uptimeMillis();
            customHandler.postDelayed(updateTimerThread, 0);


        } else {
            //stop timer there
            disableGamePane();
            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);


        }

        gameStopped = !gameStopped;
    }

    public void mainMenuMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        initGame();
        if (DEBUG) {
            Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (DEBUG)
            Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);

            int mins = secs / 60;

            secs = secs % 60;

            int milliseconds = (int) (updatedTime % 1000);

            timerValue.setText("" + mins + ":"

                    + String.format("%02d", secs) + ":"

                    + String.format("%03d", milliseconds));

            customHandler.postDelayed(this, 0);

        }

    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
