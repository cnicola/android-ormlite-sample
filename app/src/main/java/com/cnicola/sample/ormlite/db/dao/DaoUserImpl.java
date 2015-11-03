package com.cnicola.sample.ormlite.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.cnicola.sample.ormlite.models.User;
import java.sql.SQLException;
import java.util.List;

import static com.cnicola.sample.ormlite.db.DatabaseConstants.Users.ALIAS;
import static com.cnicola.sample.ormlite.db.DatabaseConstants.Users.USERNAME;

public class DaoUserImpl extends BaseDaoImpl<User, String> implements DaoUser {

    public DaoUserImpl(ConnectionSource connectionSource)
        throws SQLException {
        super(connectionSource, User.class);
    }

    public User getByUsername(String username) throws SQLException {
        final QueryBuilder<User, String> queryBuilder = queryBuilder();
        queryBuilder.where().eq(USERNAME, username);
        final PreparedQuery<User> preparedQuery = queryBuilder.prepare();
        return queryForFirst(preparedQuery);
    }

    public User getByAlias(String alias) throws SQLException {
        final QueryBuilder<User, String> queryBuilder = queryBuilder();
        queryBuilder.where().eq(ALIAS, alias);
        final PreparedQuery<User> preparedQuery = queryBuilder.prepare();
        return queryForFirst(preparedQuery);
    }

    public List<User> getAllUsers() throws SQLException {
        return queryForAll();
    }
}
