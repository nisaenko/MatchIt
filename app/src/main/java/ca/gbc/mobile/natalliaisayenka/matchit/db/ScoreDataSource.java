/**************************************************
 * Natallia Isayenka
 * 100744884
 * created: 21-Oct-2014
 * lastEdit: 21-Oct-2014
 **************************************************/


package ca.gbc.mobile.natalliaisayenka.matchit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import ca.gbc.mobile.natalliaisayenka.matchit.model.Player;

public class ScoreDataSource {

    private SQLiteDatabase database;
    private ScoreDBHelper dbHelper;

    private String[] allColumns =
            {
                    ScoreDBHelper.COLUMN_ID,
                    ScoreDBHelper.COLUMN_NAME,
                    ScoreDBHelper.COLUMN_TIME
            };

    public ScoreDataSource(Context context) {
        dbHelper = new ScoreDBHelper(context);
    }

    public void open() {

        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        database = null;
        dbHelper.close();
    }

    public void addScore(Player player){

        ContentValues values = new ContentValues();
        values.put(ScoreDBHelper.COLUMN_NAME,
                player.getName());
        values.put(ScoreDBHelper.COLUMN_TIME,
                player.getTimeInMills());

        long insertId =
                database.insert(
                        ScoreDBHelper.TABLE_SCOREBOARD,
                        null,
                        values);

    }

    public LinkedList<String> getScores(){
        LinkedList<String> scoresCollection = new LinkedList<String>();
        Cursor c = database.query(
                ScoreDBHelper.TABLE_SCOREBOARD,
                allColumns,
                null,null,null,null,null
        );
        c.moveToFirst();
        while (!c.isAfterLast()){
            //Comment c = cursor2comment(cursor);
            //comments.add(c);

            scoresCollection.add(String.valueOf(TimeUnit.MILLISECONDS.toMinutes(c.getLong(c.getColumnIndex("time"))))+":"+
                    String.valueOf(TimeUnit.MILLISECONDS.toSeconds(c.getLong(c.getColumnIndex("time")))-
                            TimeUnit.MILLISECONDS.toMinutes(c.getLong(c.getColumnIndex("time")))*60)+":"+
                    String.valueOf(TimeUnit.MILLISECONDS.toMillis(c.getLong(c.getColumnIndex("time")))-
                            TimeUnit.MILLISECONDS.toSeconds(c.getLong(c.getColumnIndex("time")))*1000)+
                    " "+c.getString(c.getColumnIndex("name")));

            c.moveToNext();
        }
        c.close();
        return scoresCollection;
    }
}
