package com.gunmachan.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.gunmachan.SQLite.StaticContextFactory.getAppContext;

/**
 * VocabDb class
 *
 * @author pdunlavey
 * @version 1.0
 * @date 10-22-18
 */
public final class VocabDb {
    private SqlHelper vDbHelper;
    private SQLiteDatabase database;

    /**
     * Constructor that always keeps the same table active given the application context.
     * @param context
     */
    public VocabDb(Context context) {
        vDbHelper = SqlHelper.getsInstance(StaticContextFactory.getAppContext());
        database = vDbHelper.getWritableDatabase();
    }

    /**
     * Inserts tuple into table and returns the corresponding row id.
     *
     * @param jpn
     * @param eng
     * @return newRowId
     */
    public long dbInsertRecords(String jpn, String eng) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.VocabEntry.COLUMN_JPN, jpn);
        contentValues.put(DbContract.VocabEntry.COLUMN_ENG, eng);
        long newRowId =
                database.insert(DbContract.VocabEntry.TABLE_NAME, null, contentValues);
        return newRowId;
    }

    /**
     * Selects tuple into table and makes a query that is returned as a Cursor.
     * Checks if Cursor is null and closes the dbRead instance.
     * @return dbCursor
     */
    public Cursor dbSelectRecords() {
        SQLiteDatabase dbRead = vDbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                DbContract.VocabEntry.COLUMN_JPN,
                DbContract.VocabEntry.COLUMN_ENG
        };

        String selection = DbContract.VocabEntry.COLUMN_JPN + " ?";
        String[] selectionArgs = {"Vocab"};

        String sortOrder =
                DbContract.VocabEntry.COLUMN_ENG + " DESC";

        Cursor dbCursor = dbRead.query(
                DbContract.VocabEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        if (dbCursor != null) {
            dbCursor.moveToFirst();
        }
        dbRead.close();
        return dbCursor;

    }
    /**
     * Returns a list that contains every item from the table.
     * Queries the table using dbSelectRecords and retrieves the index from the ID column.
     * @return itemIds
     */
    public List viewDb() {
        List itemIds = new ArrayList<>();
            Cursor cursor = dbSelectRecords();
            while (cursor.moveToNext()) {
                long itemId = cursor.getLong(
                        cursor.getColumnIndexOrThrow(DbContract.VocabEntry._ID));
                itemIds.add(itemId);
            }
        cursor.close();
        return itemIds;
    }

    /**
     * Returns the index values for the rows of the table that were updated
     * given an update query.
     *
     * @param jpn
     * @param eng
     * @return indexes for updated tuples in db.
     */
    public int dbUpdateRecords(String jpn, String eng) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.VocabEntry.COLUMN_JPN, jpn);
        contentValues.put(DbContract.VocabEntry.COLUMN_ENG, eng);

        //unfinished
        return 0;
    }
    /**
     * Selects a row in the table and uses the delete function to remove the
     * selected row from the table.
     * @param query
     * @return deletedReows
     */
    public int dbDeleteRecords(String query) {
        SQLiteDatabase dbRead = vDbHelper.getReadableDatabase();
        String selection = DbContract.VocabEntry.COLUMN_JPN + query;
        String[] selectionArgs = {"Vocab"};
        int deletedRows = dbRead.delete(DbContract.VocabEntry.TABLE_NAME, selection, selectionArgs);
        dbRead.close();
        return deletedRows;
    }

    /**
     * Uses an asset manager to open the locally stored CSV and completes
     * a database transaction that parses the comma separated items into
     * the corresponding vocabulary table.
     * If it cannot open the file or the file is empty, the IO exception
     * is caught and the stacktrace is printed.
     * If the table is not properly formatted with the correct number of
     * columns, then the extra columns are skipped and a log message is displayed.
     * @param fileName
     */
    public void importCSV(String fileName) {
        AssetManager manager = getAppContext().getAssets();
        InputStream inStream = null;
        try {
            inStream = manager.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";
        database.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length != 3) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues contentValues = new ContentValues(2);
                contentValues.put(DbContract.VocabEntry._ID, columns[0].trim());
                contentValues.put(DbContract.VocabEntry.COLUMN_JPN, columns[1].trim());
                contentValues.put(DbContract.VocabEntry.COLUMN_ENG, columns[2].trim());
                database.insert(DbContract.VocabEntry.TABLE_NAME, null, contentValues);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }
}
