package com.gunmachan.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

/**
 * SQLHelper extends the SQLiteOpenHelper class which contains useful API
 * to implement the CRUD operations of the database (CREATE, READ, UPDATE
 * and DELETE) by writing the subclasses in the SQLiteOpenHelper library.
 *
 * @author pdunlavey
 * @version 1.0
 * @date 10-22-18
 */
public final class SqlHelper extends SQLiteOpenHelper {
    private static SqlHelper sInstance;

    // increment when the schema is changed
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "VocabWord.db";

    private static final String SQLITE_CREATE_ENTRIES =
            "CREATE TABLE" + DbContract.VocabEntry.TABLE_NAME + " (" +
                    DbContract.VocabEntry._ID + " INTEGER PRIMARY KEY, " +
                    DbContract.VocabEntry.COLUMN_JPN + " TEXT)";

    private static final String SQLITE_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DbContract.VocabEntry.TABLE_NAME;

    public static synchronized SqlHelper getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SqlHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLITE_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SqlHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL(SQLITE_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
