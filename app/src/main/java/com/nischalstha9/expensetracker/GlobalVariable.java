package com.nischalstha9.expensetracker;

import android.app.Application;

public class GlobalVariable extends Application {
    public static String AccessToken=null;
    public static String BASE_URL = "https://expensetracker.up.railway.app/api/v1/";

    public static String LOGIN_URL = BASE_URL + "auth/token/obtain/";
    public static String FETCH_ACCOUNT_BOOKS_URL = BASE_URL + "expensetracker/account-books/?search=&limit=100&offset=0&ordering=-created_at";

}
