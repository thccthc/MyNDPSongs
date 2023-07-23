package sg.edu.rp.c346.id22011587.myndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "song.db";

    private static final String TABLE_NAME = "song";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public void insertSong(String title, String singers, String year, String stars){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the title as value
        values.put(COLUMN_TITLE, title);
        // Store the column name as key and the singers as value
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        // Insert the row into the TABLE_SONG
        db.insert(TABLE_NAME, null, values);
        // Close the database connection
        db.close();
    }
    public ArrayList<String> getSongContent() {
        // Create an ArrayList that holds String objects
        ArrayList<String> songs = new ArrayList<String>();
        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS};
        // Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null, null);

        // moveToFirst() moves to first row, null if no records
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row
            //  and returns true; moveToNext() returns false
            //  when no more next row to move to
            do {
                // Add the task content to the ArrayList object
                //  getString(0) retrieves first column data
                //  getString(1) return second column data
                //  getInt(0) if data is an integer value
                songs.add(cursor.getString(1));
                songs.add(cursor.getString(2));
                songs.add(cursor.getString(3));
                songs.add(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return songs;
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_NAME +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create table(s) again
        onCreate(db);
    }

    public List<Song> getAllSongs() {
        List<Song> songList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                // Retrieve the values of the columns from the current row of the cursor
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                // Create a new Song object with the retrieved values
                Song song = new Song(title, singers, year, stars);
                songList.add(song);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return songList;
    }
    public List<Song> getSongsWithRating(int rating) {
        List<Song> songList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        String selection = COLUMN_STARS + " = ?";
        String[] selectionArgs = {String.valueOf(rating)};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                // Retrieve the values of the columns from the current row of the cursor
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                // Create a new Song object with the retrieved values
                Song song = new Song(title, singers, year, stars);
                songList.add(song);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return songList;
    }

    // Method to update a song in the database
    public void updateSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", song.getTitle());
        values.put("singers", song.getSingers());
        values.put("year", song.getYear());
        values.put("stars", song.getStars());

        // Update the song using the ID as a condition
        db.update("songs", values, "id=?", new String[]{String.valueOf(song.get_Id())});
        db.close();
    }

    // Method to delete a song from the database
    public void deleteSong(int songId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("songs", "id=?", new String[]{String.valueOf(songId)});
        db.close();
    }
}
