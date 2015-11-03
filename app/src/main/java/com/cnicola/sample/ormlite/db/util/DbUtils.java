package com.cnicola.sample.ormlite.db.util;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import com.cnicola.sample.ormlite.db.provider.SampleOrmLiteHelper;
import java.io.File;

public class DbUtils {

    public static String getDatabasePath(String databaseName) {
        return (new File(Environment.getExternalStorageDirectory().getPath())).getPath() + File.separator + databaseName;
    }

    public static boolean checkDatabase(SampleOrmLiteHelper dbHelper) {
        SQLiteDatabase database = null;
        try {
            database = SQLiteDatabase.openDatabase(dbHelper.getDatabaseName(), null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if (database != null) {
            database.close();
        }
        return database != null;
    }
}
