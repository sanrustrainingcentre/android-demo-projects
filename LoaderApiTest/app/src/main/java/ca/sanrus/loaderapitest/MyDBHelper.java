package ca.sanrus.loaderapitest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by santhoshgutta on 2017-05-24.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context) {
        super(context, "mydb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"PERSON_TABLE\" (\"_ID\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, \"FIRST_NAME\" TEXT, \"LAST_NAME\" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
