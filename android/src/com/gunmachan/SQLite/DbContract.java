package com.gunmachan.SQLite;

import android.provider.BaseColumns;

/**
 * The DbContract class establishes a container that is used to define the
 * SQLite Table layout with a table name and corresponding column names.
 *
 * @author pdunlavey
 * @version 1.0
 * @date 10-22-18
 */
public final class DbContract {
    private String Vocab;
    private String JPN_Spelling;
    private String ENG_Spelling;

    private DbContract() {
    }

    public static class VocabEntry implements BaseColumns {
        public static final String TABLE_NAME = "Vocab";
        // TWO COLUMNS AS A TEST, WILL ADD MORE LATER.
        public static final String COLUMN_JPN = "JPN_Spelling";
        public static final String COLUMN_ENG = "ENG_Spelling";

    }
}
