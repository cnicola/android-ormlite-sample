package com.cnicola.sample.ormlite.db;


public class DatabaseConstants {

    public static final String DATABASE_NAME = "android_ormlite_sample.db";
    public static final int DATABASE_VERSION = 1;

    public static class Users {
        private Users() {
        }

        public static final String TABLE_NAME = "Users";
        public static final String USERNAME = "username";
        public static final String ALIAS = "alias";
    }
}
