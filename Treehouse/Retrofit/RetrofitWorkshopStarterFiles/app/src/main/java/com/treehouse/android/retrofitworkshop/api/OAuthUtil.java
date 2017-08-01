package com.treehouse.android.retrofitworkshop.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * A class that helps parsing info for doing OAuth
 */
public class OAuthUtil {

    // These constants are what we expect to get back
    public static final String ACCESS_TOKEN = "access_token";
    public static final String EXPIRES_IN = "expires_in";   // how long access token lasts, in milliseconds
    public static final String TOKEN_TYPE = "token_type";
    public static final String REFRESH_TOKEN = "refresh_token";  // use this when token has expired and you need to get a new token
    public static final String ACCOUNT_USERNAME = "account_username";  // username of the account we're accessing

    private static SharedPreferences sOAuthCredentials;

    /**
     * TODO: call this in your Application onCreate() to initialize a SharedPref object
     *
     * @param context your Application Context
     */
    public static void initSharedPref(Context context) {
        sOAuthCredentials = context.getSharedPreferences("oauth", Context.MODE_PRIVATE);
    }

    private static SharedPreferences getOAuthCredentials() {
        return sOAuthCredentials;
    }

    private static SharedPreferences.Editor editSharedPrefs() {
        return getOAuthCredentials().edit();
    }

    @Nullable
    public static String get(String key) {
        return sOAuthCredentials.getString(key, null);
    }

    public static Long getLong(String key) {
        return sOAuthCredentials.getLong(key, -1);
    }

    public static void set(String key, String value) {
        editSharedPrefs().putString(key, value).commit();
    }

    public static void set(String key, Long value) {
        editSharedPrefs().putLong(key, value);
    }

    public static boolean isAuthorized() {
        // Tells use whether we have a valid token or not
        // If it returns true, ACCESS_TOKEN is still valid;
        return get(ACCESS_TOKEN) != null &&
                getLong(EXPIRES_IN) < System.currentTimeMillis();
    }


}
