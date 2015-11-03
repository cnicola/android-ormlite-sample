package com.cnicola.sample.ormlite.db.dao;

import com.j256.ormlite.dao.Dao;
import com.cnicola.sample.ormlite.models.User;
import java.sql.SQLException;
import java.util.List;

public interface DaoUser extends Dao<User, String> {

    User getByUsername(String username) throws SQLException;

    User getByAlias(String alias) throws SQLException;

    List<User> getAllUsers() throws SQLException;
}
