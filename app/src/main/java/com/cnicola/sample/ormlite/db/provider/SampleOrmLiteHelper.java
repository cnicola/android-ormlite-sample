package com.cnicola.sample.ormlite.db.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.cnicola.sample.ormlite.db.dao.DaoUser;
import com.cnicola.sample.ormlite.db.util.DbUtils;
import com.cnicola.sample.ormlite.models.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.cnicola.sample.ormlite.db.DatabaseConstants.DATABASE_NAME;
import static com.cnicola.sample.ormlite.db.DatabaseConstants.DATABASE_VERSION;

public class SampleOrmLiteHelper extends OrmLiteSqliteOpenHelper {

    public static final String TAG = SampleOrmLiteHelper.class.getSimpleName();

    private DaoUser userDao = null;

    public SampleOrmLiteHelper(Context context) {
        super(context, DbUtils.getDatabasePath(DATABASE_NAME), null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(TAG, "Create database");
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            Log.e(TAG, "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(TAG, "Upgrade database");
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(db);
        } catch (SQLException e) {
            Log.e(TAG, "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public DaoUser getUserDao() {
        if (userDao == null) {
            userDao = safeGetDao(User.class);
        }
        return userDao;
    }

    @SuppressWarnings("unchecked")
    protected <D extends Dao<T, ?>, T> D safeGetDao(Class<T> cls) {
        try {
            return (D) getDao(cls);
        } catch (SQLException e) {
            return null;
        }
    }

    public int createUser(User user) {
        try {
            return getUserDao().create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateUser(User user) {
        try {
            return getUserDao().update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteUser(User user) {
        try {
            return getUserDao().delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public User getUserByUsername(String username) {
        try {
            return getUserDao().getByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByAlias(String alias) {
        try {
            return getUserDao().getByAlias(alias);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        try {
            return getUserDao().getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void close() {
        super.close();
        userDao = null;
    }
}
