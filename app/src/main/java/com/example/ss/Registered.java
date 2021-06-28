package com.example.ss;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Registered {
    public static final String KEY_ROWID="_id";
    public static final String KEY_NAME="person_name";
    private final String DATABASE_NAME="RegisterDB";
    private final String DATABASE_TABLE="RegisterTable";
    private final int DATABASE_VERSION=1;

    private DBHelper ourHelper;
    private final Context ourcontext;
    private SQLiteDatabase ourDatabase;

    public Registered(Context context) {
        ourcontext=context;
    }
    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlCode = "CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY, " +
                    KEY_NAME + " TEXT NOT NULL); " ;

            db.execSQL(sqlCode);
        }
    }



    public Registered open() throws SQLException {
        ourHelper = new DBHelper(ourcontext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public long createEntry(String roll,String name) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID,roll);
        cv.put(KEY_NAME, name);

        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getdata() {
        Log.d("Insidegetaregisterdb ","Thread is "+Thread.currentThread());
        String[] columns = new String[]{KEY_ROWID, KEY_NAME};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";
        int iRowID = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRowID) + ": " + c.getString(iName) + "\n";
        }
        c.close();
        return result;
    }
    public long deleteEntry(String rowId) {
        return ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=?", new String[]{rowId});
    }

    public long updateEntry(String rowId, String name) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID,rowId);
        cv.put(KEY_NAME, name);
        return ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID + "=?", new String[]{rowId});
    }
    public String fetchData(String arg1) {
        String[] columns = new String[]{KEY_ROWID, KEY_NAME};
        Cursor cursor=
                ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"=?",new String[]{arg1},null,null,null);
        String result = "";
        int iRowID = cursor.getColumnIndex(KEY_ROWID);
        int iName = cursor.getColumnIndex(KEY_NAME);

        int cntr=0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String bbb=cursor.getString(iRowID);
            if(bbb.equals(arg1)) {
                cntr++;
                result = result + cursor.getString(iName);
                break;
            }
        }
        if(cntr==0)
        {
            result="";
        }
        cursor.close();
        return result;
    }
}
