package tmbs.seliga.data;

import android.provider.BaseColumns;

/**
 * Created by thaisamirely on 12/15/15.
 */
public class DataSchema {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ReminderEntry.TABLE_NAME + " ("
                    + ReminderEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ReminderEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                    + ReminderEntry.COLUMN_DATE + " TEXT NOT NULL, "
                    + ReminderEntry.COLUMN_TIME + " TEXT NOT NULL, "
                    + ReminderEntry.COLUMN_AUDIO + " TEXT)";
    public static final String SQL_EXCLUDE_ENTRIES =
            "DROP TABLE IF EXISTS" + ReminderEntry.TABLE_NAME;

    public static abstract class ReminderEntry implements BaseColumns {

        public static final String TABLE_NAME = "reminder_table";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_AUDIO = "audio";

    }
}
