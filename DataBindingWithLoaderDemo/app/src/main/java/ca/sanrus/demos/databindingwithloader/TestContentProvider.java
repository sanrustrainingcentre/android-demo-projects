package ca.sanrus.demos.databindingwithloader;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by santhoshgutta on 2017-05-24.
 */

public class TestContentProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MyDBHelper myDBHelper;

    static {
        sUriMatcher.addURI("ca.sanrus.demos.databindingwithloader", "PERSON_TABLE", 1);
    }

    @Override
    public boolean onCreate() {
        myDBHelper = new MyDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();

        switch (sUriMatcher.match(uri)) {
            case 1:
                Log.d("", "");
                break;
        }

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables("PERSON_TABLE");

        Cursor c = sqLiteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        long id = db.insert("PERSON_TABLE", null, values);
        if(id > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse("ca.sanrus.demos.databindingwithloader/PERSON_TABLE/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        long id = db.update("PERSON_TABLE", values, selection, null);
        if(id > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return (int) id;
    }
}
