package tmbs.seliga.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import tmbs.seliga.model.Reminder;

import static tmbs.seliga.data.DataSchema.ReminderEntry;

/**
 * Created by thaisamirely on 12/15/15.
 */
public class ReminderDataSource {

    private SQLiteDatabase database;
    private ReminderSQLiteOpenHelper reminderSQLiteOpenHelper;

    private String[] allColumns = {
            ReminderEntry.COLUMN_ID,
            ReminderEntry.COLUMN_TITLE,
            ReminderEntry.COLUMN_DATE,
            ReminderEntry.COLUMN_TIME,
            ReminderEntry.COLUMN_AUDIO};

    public ReminderDataSource(Context context) {
        reminderSQLiteOpenHelper = new ReminderSQLiteOpenHelper(context);
    }

    private void open() throws SQLException {
        //If database is null, then get its instance at once
        if (database == null || !database.isOpen()) {
            database = reminderSQLiteOpenHelper.getWritableDatabase();
        }
    }

    private void close() {
        reminderSQLiteOpenHelper.close();
    }

    public Reminder insert(Reminder reminder) {
        open();
        long insertId = database.insert(ReminderEntry.TABLE_NAME,
                null,
                getValues(reminder));

        Cursor cursor = database.query(ReminderEntry.TABLE_NAME, allColumns, ReminderEntry.COLUMN_ID + "=" + insertId, null, null, null, null);
        cursor.moveToFirst();
        Reminder newReminder = cursorToReminder(cursor);

        close();
        return newReminder;
    }

    public Reminder update(Reminder reminder) {
        open();

        long row = database.update(ReminderEntry.TABLE_NAME,
                getValues(reminder),
                ReminderEntry.COLUMN_ID + " = LIKE",
                new String[]{String.valueOf(reminder.getId())});

        Cursor cursor = database.query(ReminderEntry.TABLE_NAME, allColumns, ReminderEntry.COLUMN_ID + "=" + row, null, null, null, null);
        cursor.moveToFirst();
        Reminder newReminder = cursorToReminder(cursor);

        cursor.close();
        close();
        return newReminder;
    }

    public List<Reminder> select() {
        List<Reminder> reminderList = new ArrayList<>();

        return reminderList;
    }

    /**
     * Lists all Reminders sotred into the database.
     * @return A list of Reminder objects.
     */
    public List<Reminder> listAllReminders() {
        open();
        //Stores all Reminders into a cursor
        Cursor cursor = database.query(ReminderEntry.TABLE_NAME, allColumns, null, null, null, null, null);

        List<Reminder> reminderList = new ArrayList<>();

        //Iterates the cursor, retrieveing every Reminder and adding them to the List.
        while(cursor.moveToNext()){
            Reminder reminder = cursorToReminder(cursor);
            reminderList.add(reminder);
        }

        cursor.close();
        close();

        return reminderList;
    }

    private ContentValues getValues(Reminder reminder) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ReminderEntry.COLUMN_TITLE, reminder.getTitle());
        contentValues.put(ReminderEntry.COLUMN_DATE, reminder.getDate());
        contentValues.put(ReminderEntry.COLUMN_TIME, reminder.getTime());
        contentValues.put(ReminderEntry.COLUMN_AUDIO, reminder.getAudioAddress());
        return contentValues;
    }


    private Reminder cursorToReminder(Cursor cursor) {
        Reminder reminder = new Reminder();
        reminder.setId(cursor.getLong(0));
        reminder.setTitle(cursor.getString(1));
        reminder.setDate(cursor.getString(2));
        reminder.setTime(cursor.getString(1));
        reminder.setAudioAddress(cursor.getString(1));

        return reminder;
    }
}
