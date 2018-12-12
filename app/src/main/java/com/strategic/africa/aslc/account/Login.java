package com.strategic.africa.aslc.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.strategic.africa.aslc.R;
import com.strategic.africa.aslc.conf.Config;
import com.strategic.africa.aslc.conf.SessionManager;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Muhwezi Denis Liam on 11/19/2017.
 */

public class Login extends AppCompatActivity {


    @Bind(R.id.btn_login)
    Button btn_login;

    @Bind(R.id.link_signup)
    TextView link_sign_up;

    @Bind(R.id.input_name_til)
    TextInputLayout til_input_name;

    @Bind(R.id.input_email_til)
    TextInputLayout til_input_email;

    @Bind(R.id.input_email)
    EditText _passwordText;

    @Bind(R.id.input_name)
    EditText _nameText;

    String User, Password;

    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        session = new SessionManager(this);


        _nameText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        _passwordText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        btn_login.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        til_input_name.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        til_input_email.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        session = new SessionManager(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login.this,Dashboard.class));

                finish();


                /*if (!validate()) {
                    return;
                } else {
                    User = _nameText.getText().toString();
                    Password = _passwordText.getText().toString();

                    if (isNetworkAvailable()) {


//
//                        HashMap<String, String> postLogin = new HashMap<>();
//                        postLogin.put("user_name", User);
//                        postLogin.put("password", Password);


                        PostResponseAsyncTask taskLogin = new PostResponseAsyncTask(Login.this, new AsyncResponse() {
                            @Override
                            public void processFinish(String s) {
                                Log.d("string",s);

                                try {
                                    JSONObject jsonResponse = new JSONObject(s);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {



                                        String user_name = jsonResponse.getString("user_account_username");
                                        String user_password= jsonResponse.getString("user_account_password");
                                        String api_password = jsonResponse.getString("api_password");
                                        String api_ac = jsonResponse.getString("api_account");
                                        String owner = jsonResponse.getString("owner");
                                        String balance = jsonResponse.getString("TOTAL_BALANCE");
                                        String user_profile = jsonResponse.getString("user_account_profile");
                                        String user_currency = jsonResponse.getString("user_currency");

                                        session.saveCurrency(user_currency);


                                        onLoginSuccess(user_name, user_password,
                                                api_password,owner,user_profile,
                                                api_ac,Integer.valueOf(balance));


                                    }
                                    else
                                    {

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        taskLogin.execute(Config.url + "darajja/android/login.php?user_name="+User+"&password="+Password);

                    } else {
                        Toast.makeText(
                                Login.this, "Check Internet Connectivity and try again", Toast.LENGTH_LONG).show();
                    }
                }*/
            }

        });


        link_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,SignUp.class));
                finish();
            }
        });

    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty()) {

            _nameText.setError("enter a Username");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void onLoginSuccess(String user, String pass, String api_pass, String owner, String prof,String account,int balance) {




        //session.createLoginSession(user, pass, api_pass, owner, prof, account, balance,0);

        startActivity(new Intent(Login.this,Dashboard.class));

        finish();

    }

}
