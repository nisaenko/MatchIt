/**************************************************
 * Natallia Isayenka
 * 100744884
 * created: 21-Oct-2014
 * lastEdit: 21-Oct-2014
 **************************************************/

package ca.gbc.mobile.natalliaisayenka.matchit.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ScoreDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME
            = "score";
    public static final int DATABASE_VERSION = 2;


    //tables and columns
    public static final String TABLE_SCOREBOARD = "scoreboard";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIME = "time";


    //create statement
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS "
                    + TABLE_SCOREBOARD
                    + "("+COLUMN_ID
                    + " integer primary key autoincrement, "
                    +COLUMN_NAME+" varchar(45), "+COLUMN_TIME+" longinteger);";

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     *
     */
    public ScoreDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w(ScoreDBHelper.class.getName(),
                "Create database " + DATABASE_NAME
                        + " version " + DATABASE_VERSION);
        db.execSQL(CREATE_TABLE);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ScoreDBHelper.class.getName(),
                "Upgrade database from version "+oldVersion+
                        " to version "+newVersion);

        db.execSQL("Drop table if exists "+TABLE_SCOREBOARD);
        onCreate(db);
    }
}
