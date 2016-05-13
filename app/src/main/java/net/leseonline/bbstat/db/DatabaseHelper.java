package net.leseonline.bbstat.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mlese on 5/10/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bbstat.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TEAMS);
        db.execSQL(CREATE_TABLE_PLAYERS);
        db.execSQL(CREATE_TABLE_TEAMS_PLAYERS);
        db.execSQL(CREATE_TABLE_GAMES);
        db.execSQL(CREATE_TABLE_GAME_STATS);
        db.execSQL(CREATE_TABLE_STATS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + BBStatContract.TeamEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BBStatContract.PlayerEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BBStatContract.TeamPlayerEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BBStatContract.GameEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BBStatContract.GameStatEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BBStatContract.BasketballStatEntry.TABLE_NAME);

        onCreate(db);
    }

    private static final String CREATE_TABLE_TEAMS = "CREATE TABLE " +
            BBStatContract.TeamEntry.TABLE_NAME + "(" +
            "_id INTEGER PRIMARY KEY," +
            BBStatContract.TeamEntry.COLUMN_NAME_TEAM_NAME + " TEXT)";

    private static final String CREATE_TABLE_PLAYERS = "CREATE TABLE " +
            BBStatContract.PlayerEntry.TABLE_NAME + "(" +
            "_id INTEGER PRIMARY KEY," +
            BBStatContract.PlayerEntry.COLUMN_NAME_PLAYER_FIRST_NAME + " TEXT," +
            BBStatContract.PlayerEntry.COLUMN_NAME_PLAYER_LAST_NAME + " TEXT)";

    private static final String CREATE_TABLE_TEAMS_PLAYERS = "CREATE TABLE " +
            BBStatContract.TeamPlayerEntry.TABLE_NAME + "(" +
            "_id INTEGER PRIMARY KEY," +
            BBStatContract.TeamPlayerEntry.COLUMN_NAME_TEAM_ID + " INTEGER," +
            BBStatContract.TeamPlayerEntry.COLUMN_NAME_PLAYER_ID + " INTEGER)";

    private static final String CREATE_TABLE_GAMES = "CREATE TABLE " +
            BBStatContract.GameEntry.TABLE_NAME + "(" +
            "_id INTEGER PRIMARY KEY," +
            BBStatContract.GameEntry.COLUMN_NAME_TEAM1_ID + " INTEGER," +
            BBStatContract.GameEntry.COLUMN_NAME_TEAM2_ID + " INTEGER," +
            BBStatContract.GameEntry.COLUMN_NAME_TEAM1_SCORE + " INTEGER," +
            BBStatContract.GameEntry.COLUMN_NAME_TEAM2_SCORE + " INTEGER," +
            BBStatContract.GameEntry.COLUMN_NAME_GAME_CREATED + " DATETIME)";

    private static final String CREATE_TABLE_GAME_STATS = "CREATE TABLE " +
            BBStatContract.GameStatEntry.TABLE_NAME + "(" +
            "_id INTEGER PRIMARY KEY," +
            BBStatContract.GameStatEntry.COLUMN_NAME_GAME_ID + " INTEGER," +
            BBStatContract.GameStatEntry.COLUMN_NAME_STAT_ID + " INTEGER)";

    private static final String CREATE_TABLE_STATS = "CREATE TABLE " +
            BBStatContract.BasketballStatEntry.TABLE_NAME + "(" +
            "_id INTEGER PRIMARY KEY," +
            BBStatContract.BasketballStatEntry.COLUMN_NAME_PLAYER_ID + " INTEGER," +
            BBStatContract.BasketballStatEntry.COLUMN_NAME_TWO_POINTS + " INTEGER," +
            BBStatContract.BasketballStatEntry.COLUMN_NAME_TWO_POINT_ATTEMPTS + " INTEGER," +
            BBStatContract.BasketballStatEntry.COLUMN_NAME_THREE_POINTS + " INTEGER," +
            BBStatContract.BasketballStatEntry.COLUMN_NAME_THREE_POINT_ATTEMPTS + " INTEGER," +
            BBStatContract.BasketballStatEntry.COLUMN_NAME_FREE_THROWS + " INTEGER," +
            BBStatContract.BasketballStatEntry.COLUMN_NAME_FREE_THOW_ATTEMPTS + " INTEGER," +
            BBStatContract.BasketballStatEntry.COLUMN_NAME_ASSISTS + " INTEGER," +
            BBStatContract.BasketballStatEntry.COLUMN_NAME_FOULS + " INTEGER," +
            BBStatContract.BasketballStatEntry.COLUMN_NAME_REBOUNDS + " INTEGER)";

    public long addTeam(String name) {
        long rowid = -1;

        try {
            ContentValues values = new ContentValues();
            values.put(BBStatContract.TeamEntry.COLUMN_NAME_TEAM_NAME, name);

            SQLiteDatabase db = this.getWritableDatabase();

            rowid = db.insert(BBStatContract.TeamEntry.TABLE_NAME, null, values);

            db.close();
        }catch (Exception ex) {
            ex.printStackTrace();
            rowid = -1;
        }

        return rowid;
    }

    public Cursor getTeams() {
        String query = "select * from " + BBStatContract.TeamEntry.TABLE_NAME + " order by " + BBStatContract.TeamEntry.COLUMN_NAME_TEAM_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(query, null);
    }
}
