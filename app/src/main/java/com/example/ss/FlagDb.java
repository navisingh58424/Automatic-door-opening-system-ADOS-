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

public class FlagDb {
    public static final String KEY_ROWID="_id";
    public static final String KEY_FLAG="person_mail";
    private final String DATABASE_NAME="FlagerrrrDB";
    private final String DATABASE_TABLE="FlagerrrrTable";
    private final int DATABASE_VERSION=1;
    private FlagDb.DBHelper ourHelper;
    private final Context ourcontext;
    private SQLiteDatabase ourDatabase;
    public FlagDb(Context context) {
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
                    KEY_FLAG + " TEXT NOT NULL); ";
            db.execSQL(sqlCode);
        }

    }
    public FlagDb open() throws SQLException {
        ourHelper = new FlagDb.DBHelper(ourcontext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }
    public long createEntry(String roll,String time) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID,roll);
        cv.put(KEY_FLAG, time);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }
    public String getdata2()
    {
        Log.d("getdata2FLGdb","Thread is "+Thread.currentThread());
        String[] columns = new String[]{KEY_ROWID, KEY_FLAG};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";
        int iRowID = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_FLAG);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRowID) + ": " + c.getString(iName) + " " +"\n";
        }
        c.close();
        return result;
    }
    public long deleteEntry(String rowId) {
        return ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=?", new String[]{rowId});
    }

    public String fetchData(String arg1) {
        String[] columns = new String[]{KEY_ROWID, KEY_FLAG};
        Cursor c=
                ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"=?",new String[]{arg1},null,null,null);
        String result = "";
        int iRowID = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_FLAG);
        int cntr=0;
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String bbb=c.getString(iRowID);
            if(bbb.equals(arg1)) {
                cntr++;
                result = c.getString(iName);
                break;
            }
        }
        if(cntr==0)
        {
            result="notfound\n";
        }
        c.close();
        return result;
    }
    public long updateEntry(String rowId,String hh) {
        String[] columns = new String[]{KEY_ROWID, KEY_FLAG};
        Cursor c=
                ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"=?",new String[]{rowId},null,null,null);
        String result = "";
        int iRowID = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_FLAG);
        int cntr=0;
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String bbb=c.getString(iRowID);
            if(bbb.equals(rowId)) {
                cntr++;
                result = c.getString(iName);
                break;
            }
        }
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        String timee=format.format(calendar.getTimeInMillis());
        int gg=result.length();
        gg--;
        if(gg<0)
        {
            result=timee+"to";
        }
        else if(result.charAt(gg)==',')
        {
            timee=timee+"to";
            result=result+timee;
        }
        else
        {
            timee=timee+",";
            result=result+timee;
        }
        if(hh.equals("none"))
            result="";
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID,rowId);
        cv.put(KEY_FLAG, result);
        return ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID + "=?", new String[]{rowId});
    }
}
