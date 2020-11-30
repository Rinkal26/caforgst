package com.example.caforgst.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.caforgst.RegistrationApi.UserRegistration;
import com.example.caforgst.VerificationcodeActivity.VerifyCode;
import com.example.caforgst.VerificationnoActivity.VerifyNumber;

public class Sharedpref {

    private static final String SHARE_PREF="simplifiedcodingsharedprefretrofit";

    private static Sharedpref mInstance;
    private static Context mCtx;

    private static final String KEY_USER_ID = "keyuserid";
    private static final String KEY_FIRST_NAME= "keyusername";
    private static final String KEY_LAST_NAME= "keyusername";
    private static final String KEY_USER_EMAIL = "keyuseremail";
    private static final String KEY_USER_GENDER = "keyusergender";
    private static final String KEY_USER_CITY= "keyusercity";
    private static final String KEY_USER_STATE = "keyuserstate";
    private static final String KEY_USER_DOB = "keyuserdob";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_VERIFY_MOBILE_NO="keyverifymobileno";

    public Sharedpref(Context context) {
        mCtx = context;
    }

    public static synchronized Sharedpref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Sharedpref(context);
        }
        return mInstance;
    }

    public boolean userRegistartion(UserRegistration user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, user.getUser().getId());
        editor.putString(KEY_FIRST_NAME, user.getUser().getFirstname());
        editor.putString(KEY_LAST_NAME,user.getUser().getLastname());
        editor.putString(KEY_USER_EMAIL, user.getUser().getEmail());
        editor.putString(KEY_USER_GENDER, user.getUser().getGender());
        editor.putString(KEY_USER_DOB, user.getUser().getDob());
        editor.putString(KEY_USER_CITY,user.getUser().getCity());
        editor.putString(KEY_USER_STATE,user.getUser().getState());
        editor.putString(KEY_TOKEN, user.getToken());

        editor.apply();
        return true;
    }
    public  boolean userLogin(VerifyCode verifyNumber)
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARE_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_VERIFY_MOBILE_NO,verifyNumber.getMobile());
        editor.apply();
        return true;
    }

    public String getnumber(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_VERIFY_MOBILE_NO, null) != null)
            return sharedPreferences.getString(KEY_VERIFY_MOBILE_NO, null);
        return "";
    }

    public String getToken() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_TOKEN, null) != null)
            return sharedPreferences.getString(KEY_TOKEN, null);
        return "";
    }

    public static boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

}
