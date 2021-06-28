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

public class ContactsDb {
    public static final String KEY_ROWID="_id";
    public static final String KEY_NAME="person_name";
    public static final String KEY_MAIL="person_mail";
    public static final String KEY_OUT="out_time";
    //  public Timing OUT_TIME="out_time";

    private final String DATABASE_NAME="ContactrDB";
    private final String DATABASE_TABLE="ContactrTable";
    private final int DATABASE_VERSION=1;

    private DBHelper ourHelper;
    private final Context ourcontext;
    private SQLiteDatabase ourDatabase;

    public ContactsDb(Context context) {
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
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_MAIL + " TEXT NOT NULL, "+
                    KEY_OUT + " TEXT NOT NULL);";
            db.execSQL(sqlCode);
        }

    }



    public ContactsDb open() throws SQLException {
        ourHelper = new DBHelper(ourcontext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public long createEntry(String roll,String name, String mail,String timee) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID,roll);
        cv.put(KEY_NAME, name);
        cv.put(KEY_MAIL, mail);
        cv.put(KEY_OUT,timee);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getdata() {
        Log.d("getdatacntsdb","Thread is "+Thread.currentThread());
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_MAIL,KEY_OUT};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";
        int iRowID = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iCell = c.getColumnIndex(KEY_MAIL);
        int iTime=c.getColumnIndex(KEY_OUT);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            //result = result + c.getString(iRowID) + ": " + c.getString(iName) + " " +
            //      c.getString(iCell) +" "+c.getString(iTime) +"\n";

            Calendar calendar=Calendar.getInstance();
            SimpleDateFormat format=new SimpleDateFormat("HH:mm");
            String time=format.format(calendar.getTimeInMillis());
            int g=calendar.get(Calendar.HOUR_OF_DAY);
            int hhh=calendar.get(Calendar.HOUR);
            int hh=calendar.get(Calendar.MINUTE);
            int m=g*3600+hh*60;
            String str=c.getString(iTime);
            Log.d("itime is ","sss "+str+ " "+m);
            int mm=Integer.parseInt(str);
            if(m>mm) {
                result = result + c.getString(iCell) + "\n";
            }
        }
        c.close();
        return result;
    }
    public String getdata2()
    {
        Log.d("getdata2cntsdb","Thread is "+Thread.currentThread());
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_MAIL,KEY_OUT};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";
        int iRowID = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iCell = c.getColumnIndex(KEY_MAIL);
        int iTime=c.getColumnIndex(KEY_OUT);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRowID) + ": " + c.getString(iName) + " " +
                    c.getString(iCell) +" "+c.getString(iTime) +"\n";
        }
        c.close();
        return result;
    }
    public long deleteEntry(String rowId) {
        return ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=?", new String[]{rowId});
    }

    public long updateEntry(String rowId, String name, String mail,String timee) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID,rowId);
        cv.put(KEY_NAME, name);
        cv.put(KEY_MAIL, mail);
        cv.put(KEY_OUT,timee);

        return ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID + "=?", new String[]{rowId});
    }
    public String fetchData(String arg1) {
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_MAIL,KEY_OUT};
        Cursor c=
                ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"=?",new String[]{arg1},null,null,null);
        String result = "";
        int iRowID = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iCell = c.getColumnIndex(KEY_MAIL);
        int iTime=c.getColumnIndex(KEY_OUT);
        int cntr=0;
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String bbb=c.getString(iRowID);
            if(bbb.equals(arg1)) {
                cntr++;
                result = result + c.getString(iRowID) + ": " + c.getString(iName) + " " +
                        c.getString(iCell) + " " + c.getString(iTime) + "  ";
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
}
