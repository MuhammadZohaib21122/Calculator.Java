package com.example.mycalculater;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "calculator.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_HISTORY = "history";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_RESULT = "result";
    private static final String COLUMN_EXPRESSION = "expression";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }

    public void addHistoryItem(HistoryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RESULT, item.getResult());
        values.put(COLUMN_EXPRESSION, item.getExpression());
        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }


    public List<HistoryItem> getAllHistoryItems() {
        List<HistoryItem> historyItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_HISTORY, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String result = cursor.getString(cursor.getColumnIndex(COLUMN_RESULT));
                @SuppressLint("Range") String expression = cursor.getString(cursor.getColumnIndex(COLUMN_EXPRESSION));
                historyItems.add(new HistoryItem(id, result, expression));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return historyItems;
    }
    public void deleteHistoryItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HISTORY, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_HISTORY + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RESULT + " TEXT , " +
                COLUMN_EXPRESSION + " TEXT)";
        db.execSQL(createTable);
    }
}
