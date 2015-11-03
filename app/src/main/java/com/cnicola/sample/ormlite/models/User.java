package com.cnicola.sample.ormlite.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.cnicola.sample.ormlite.db.DatabaseConstants;
import com.cnicola.sample.ormlite.db.dao.DaoUserImpl;

@DatabaseTable(tableName = DatabaseConstants.Users.TABLE_NAME, daoClass = DaoUserImpl.class)
public class User {

    @DatabaseField(id = true)
    String username;
    @DatabaseField
    String password;
    @DatabaseField
    String alias;

    // needed by ormlite
    public User() {}

    public User(String alias, String username, String password) {
        this.alias = alias;
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public String toString() {
        return "Alias: " + alias + ", Username: " + username + ", Password: " + password;
    }
}
