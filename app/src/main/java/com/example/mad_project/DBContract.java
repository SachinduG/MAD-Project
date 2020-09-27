package com.example.mad_project;
import android.provider.BaseColumns;


public final class DBContract {
    public DBContract() {
    }

    public static abstract class CustomerProfile implements BaseColumns {
        /*Customer Profile Table*/
        public static final String USER_TABLE_NAME = "User";
        public static final String USER_EMAIL = "email";
        public static final String USER_NAME = "name";
        public static final String USER_MOBILE = "mobile";
        public static final String USER_NIC = "nic";
        public static final String USER_ADDRESS = "address";
        public static final String USER_PASSWORD = "password";


    }
}
