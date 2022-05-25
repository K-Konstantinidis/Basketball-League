package com.example.esake;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    DatabaseHelper helper;
    SQLiteDatabase db;
    List<Game> gamesList = new ArrayList<>();

    public DatabaseAdapter(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public List<Game> getAllGames(){
        String columns[] = {DatabaseHelper.KEY_ROWID, DatabaseHelper.KEY_NAME, DatabaseHelper.KEY_EMAIL};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns,null, null,null, null, null, null);
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_ROWID);
            int score1 = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_NAME);
            int score2 = cursor.getInt(index2);
//            String name = cursor.getString(index2);
//            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_EMAIL);
//            String email = cursor.getString(index3);
            Game game = new Game(score1, score2);
            gamesList.add(game);
        }
        return gamesList;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "mydb.db";
        private static final String TABLE_NAME = "contacts";
        // When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = 1;
        private static final String KEY_ROWID = "_id";
        private static final String KEY_NAME="name";
        private static final String KEY_EMAIL = "email";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_ROWID+" integer primary key autoincrement, "+ KEY_NAME + " text, "+ KEY_EMAIL+ " text)";
        private static final String DROP_TABLE = "drop table if exists "+ TABLE_NAME;
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Toast.makeText(context, "constructor called", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                //db.execSQL(CREATE_TABLE);
                Toast.makeText(context, "onCreate called", Toast.LENGTH_SHORT).show();
            }catch (SQLException e){
                Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                Toast.makeText(context, "onUpgrade called", Toast.LENGTH_SHORT).show();
                //db.execSQL(DROP_TABLE);
                //onCreate(db);
            }catch (SQLException e){
                Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
