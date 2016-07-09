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
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Comparator;

import ca.gbc.mobile.natalliaisayenka.matchit.db.ScoreDataSource;


public class ScoreActivity extends Activity {

    public static boolean DEBUG = false;

    private ScoreDataSource scoreDataSource;

    ArrayList <String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    ListView listView ;

    final private Comparator<String> comp = new Comparator<String>() {
        public int compare(String e1, String e2) {
            return e1.toString().compareTo(e2.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        listView = (ListView) findViewById(R.id.listViewScore);

        scoreDataSource = new ScoreDataSource(this);
        scoreDataSource.open();
        listItems.addAll(scoreDataSource.getScores());
        scoreDataSource.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItems);

        adapter.sort(comp);

        listView.setAdapter(adapter);

        if (getParent()!=null){
            getParent().finish();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.score, menu);
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



    public void okMessage(View view) {


        Intent intent = new Intent(this, MainActivity.class);
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
