package com.strategic.africa.aslc.conf;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.strategic.africa.aslc.account.Login;


public class SessionManager {
	// Shared Preferences
	SharedPreferences pref;
	
	// Editor for Shared preferences
	Editor editor;
	
	// Context
	Context _context;


	private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
	
	// Shared pref mode
	int PRIVATE_MODE = 0;
	
	// Sharedpref file name
	private static final String PREF_NAME = "LoginPref";
	
	// All Shared Preferences Keys
	private static final String IS_LOGIN = "IsLoggedIn";
	
	// User name (make variable public to access from outside)
	public static final String KEY_NAME = "username";
	
	// Email address (make variable public to access from outside)
	public static final String KEY_EMAIL = "password";

	public static final String KEY_ACCOUNT = "account";

	public static final String KEY_BALANCE = "balance";

	public static final String KEY_PROF = "profile_picture";

	public static final String KEY_API = "api_password";

	public static final String KEY_OWNER = "owner_id";

	public static final String KEY_CURR = "currency";

	public static final String KEY_LOAN = "loan";

	// Constructor
	public SessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	
	/**
	 * Create login session
	 * */
	public void createLoginSession(String name, String password, String api_password, String owner, String prof,String account, int balance, int loan){
		// Storing login value as TRUE
		editor.putBoolean(IS_LOGIN, true);
		
		// Storing name in pref
		editor.putString(KEY_NAME, name);
		
		// Storing email in pref
		editor.putString(KEY_EMAIL, password);

		editor.putString(KEY_PROF,prof);

		editor.putInt(KEY_LOAN,loan);

		editor.putString(KEY_ACCOUNT,account);

		editor.putInt(KEY_BALANCE,balance);

		editor.putString(KEY_API, api_password);

		editor.putString(KEY_OWNER, owner);
		// commit changes
		editor.commit();
	}

	public void saveCurrency(String currency){
		// Storing login value as TRUE

		// Storing name in pref
		editor.putString(KEY_CURR, currency);

		// commit changes
		editor.commit();
	}

	/**
	 * Check login method wil check user login status
	 * If false it will redirect user to login page
	 * Else won't do anything
	 * */
	public Boolean checkLogin(){
		// Check login status
		Boolean status = true;

		if(!this.isLoggedIn()){

			status = false;
			// user is not logged in redirect him to Login Activity
		}
		return status;
	}
	
	
	
	/**
	 * Get stored session data
	 * */
	public HashMap<String, String> getUserDetails(){
		HashMap<String, String> user = new HashMap<String, String>();
		// user name
		user.put(KEY_NAME, pref.getString(KEY_NAME, null));
		
		// user email id
		user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

		user.put(KEY_ACCOUNT, pref.getString(KEY_ACCOUNT, null));


		user.put(KEY_BALANCE, String.valueOf(pref.getInt(KEY_BALANCE, 0)));

		user.put(KEY_PROF, pref.getString(KEY_PROF, null));

		user.put(KEY_API, pref.getString(KEY_API, null));

		user.put(KEY_LOAN, String.valueOf(pref.getInt(KEY_LOAN, 0)));

		user.put(KEY_OWNER, pref.getString(KEY_OWNER,  null));
		
		// return user
		return user;
	}

	public HashMap<String, String> getCurrency(){
		HashMap<String, String> user = new HashMap<String, String>();
		// user name
		user.put(KEY_CURR, pref.getString(KEY_CURR, null));

		// return user
		return user;
	}
	
	/**
	 * Clear session details
	 * */
	public void logoutUser(){
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();
		
		// After logout redirect user to Loing Activity
		Intent i = new Intent(_context, Login.class);
		// Closing all the Activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		// Add new Flag to start new Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		// Staring Login Activity
		_context.startActivity(i);

	}
	
	/**
	 * Quick check for login
	 * **/
	// Get Login State
	public boolean isLoggedIn(){
		return pref.getBoolean(IS_LOGIN, false);
	}


	public void setFirstTimeLaunch(boolean isFirstTime) {
		editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
		editor.commit();
	}

	public void setIsLoggedIn(boolean isLoggedIn) {
		editor.putBoolean(IS_LOGIN, isLoggedIn);
		editor.commit();
	}

	public boolean isFirstTimeLaunch() {
		return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
	}
}
