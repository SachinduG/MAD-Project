package Database;

import android.provider.BaseColumns;

public final class BOOKUser {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private BOOKUser() {}

    /* Inner class that defines the table contents */
    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "Bookingdetails";
        public static final String COLUMN_1 = "name";
        public static final String COLUMN_2 = "email";
        public static final String COLUMN_3 = "nic";
        public static final String COLUMN_4 = "mobile";
    }
}