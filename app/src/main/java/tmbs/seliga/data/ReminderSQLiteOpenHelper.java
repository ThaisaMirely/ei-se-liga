package tmbs.seliga.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thaisamirely on 12/15/15.
 */
public class ReminderSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Reminder.db";
    public static final int DATABASE_VERSION = 1;

    public ReminderSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DataSchema.SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(DataSchema.SQL_EXCLUDE_ENTRIES);
        onCreate(database);
    }
}
