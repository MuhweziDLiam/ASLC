package com.strategic.africa.aslc.account;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.EachExceptionsHandler;

import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.strategic.africa.aslc.R;
import com.strategic.africa.aslc.conf.Config;
import com.strategic.africa.aslc.conf.SessionManager;
import com.strategic.africa.aslc.country.CountryPicker;
import com.strategic.africa.aslc.country.CountryPickerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Muhwezi Denis Liam on 11/19/2017.
 */

public class SignUp extends AppCompatActivity{


    String base64String;

    byte imageInByte[];

    String currency_code = "";

    String dial_code = "";

    @Bind(R.id.btn_signup)
    Button btn_sign_up;


    @Bind(R.id.country)
    EditText country;

    @Bind(R.id.til_country)
    TextInputLayout til_country;

    @Bind(R.id.link_login)
    TextView link_login;

    @Bind(R.id.confirm_password)
    EditText confirm_password;

    @Bind(R.id.til_confirm_password)
    TextInputLayout con_password;

    @Bind(R.id.email)
    EditText email_address;

    @Bind(R.id.number)
    com.bachors.prefixinput.EditText phone_number;

    @Bind(R.id.org)
    EditText org;

    @Bind(R.id.account_password)
    EditText account_password;

    @Bind(R.id.full_name)
    EditText full_name;

    @Bind(R.id.til_email)
    TextInputLayout til_email;

    @Bind(R.id.til_number)
    TextInputLayout til_number;

    SessionManager sessionManager;

    @Bind(R.id.til_full_name)
    TextInputLayout til_full_name;

    @Bind(R.id.til_org)
    TextInputLayout til_org;

    @Bind(R.id.til_account_password)
    TextInputLayout til_account_password;

    Boolean profile_image = false;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;

    private String userChosenTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);

        org.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        confirm_password.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        btn_sign_up.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        account_password.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        til_account_password.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        country.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        til_country.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        org.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        full_name.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        email_address.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        phone_number.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        til_email.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        til_full_name.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        til_number.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        con_password.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        til_org.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/malgun_gothic_semilight.ttf"));

        TextWatcher textWatcher =new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!editable.toString().isEmpty())
                {
                    confirm_password.setVisibility(View.VISIBLE);
                }

                if(editable.toString().isEmpty())
                {
                    confirm_password.setVisibility(View.GONE);
                }

            }
        };

        account_password.addTextChangedListener(textWatcher);

        TextWatcher textWatcherOne =new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!editable.toString().isEmpty())
                {
                    if(account_password.getText().toString().equals(editable.toString()))
                    {
                        confirm_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_tick, 0);
                    }
                    else
                    {
                        confirm_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_wrong, 0);
                    }
                }
                else
                {
                    confirm_password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_wrong, 0);
                }

            }
        };

        confirm_password.addTextChangedListener(textWatcherOne);



        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CountryPicker picker = CountryPicker.newInstance("SelectCountry");
                picker.setListener(new CountryPickerListener() {

                    @Override
                    public void onSelectCountry(String name, String code, String dialCode) {

                        country.setText(name);

                        currency_code = String.valueOf(CountryPicker.getCurrencyCode(code));

                        til_number.setVisibility(View.VISIBLE);

                        phone_number.setPrefix(dialCode);

                        dial_code = dialCode;

//                        Toast.makeText(
//                                SignUp.this,
//                                "Country Name: " + name + " - Code: " + code
//                                        + " - Currency: "
//                                        + CountryPicker.getCurrencyCode(code) + " - Dial Code: " + dialCode,
//                                Toast.LENGTH_SHORT).show();

                        picker.dismiss();
                    }
                });

                picker.show(getSupportFragmentManager(), "COUNTRY_CODE_PICKER");
            }
        });

        country.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                final CountryPicker picker = CountryPicker.newInstance("SelectCountry");
                picker.setListener(new CountryPickerListener() {

                    @Override
                    public void onSelectCountry(String name, String code, String dialCode) {

                        country.setText(name);

                        currency_code = String.valueOf(CountryPicker.getCurrencyCode(code));

                        til_number.setVisibility(View.VISIBLE);

                        phone_number.setPrefix(dialCode);

                        dial_code = dialCode;

                        picker.dismiss();
//                        Toast.makeText(
//                                SignUp.this,
//                                "Country Name: " + name + " - Code: " + code
//                                        + " - Currency: "
//                                        + CountryPicker.getCurrencyCode(code) + " - Dial Code: " + dialCode,
//                                Toast.LENGTH_SHORT).show();
                    }
                });

                picker.show(getSupportFragmentManager(), "COUNTRY_CODE_PICKER");
            }
        });

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validate()) {
                    return;
                }
                else {

                    HashMap<String,String> postAccount = new HashMap<>();
                    postAccount.put("password",account_password.getText().toString());
                    postAccount.put("email",email_address.getText().toString());
                    postAccount.put("phone",phone_number.getText().toString());
                    postAccount.put("fullname",full_name.getText().toString().replace(" ",""));
                    postAccount.put("company_name",org.getText().toString());
                    postAccount.put("country",country.getText().toString());
                    postAccount.put("dial_code",dial_code);
                    postAccount.put("currency",currency_code);

                    String url = Config.url + "darajja/android/register_account.php";

                    PostResponseAsyncTask taskAccount = new PostResponseAsyncTask(SignUp.this, postAccount, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            Log.d("string",s);

                            try {
                                JSONObject jsonResponse = new JSONObject(s);
                                String success = jsonResponse.getString("success");

                                if (success.equalsIgnoreCase("true")) {



                                    Intent intent = new Intent(SignUp.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if (success.equalsIgnoreCase("no") || success.equalsIgnoreCase("false")) {


                                }
                                else {
                                    Toast.makeText(SignUp.this, "Username already exists", Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    taskAccount.execute(url);

                    taskAccount.setEachExceptionsHandler(new EachExceptionsHandler() {
                        @Override
                        public void handleIOException(IOException e) {
                            Toast.makeText(SignUp.this, "Error with internet or web server. "+e, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void handleMalformedURLException(MalformedURLException e) {
                            Toast.makeText(SignUp.this, "Error with the URL. "+e, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void handleProtocolException(ProtocolException e) {
                            Toast.makeText(SignUp.this, "Error with protocol. "+e, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                            Toast.makeText(SignUp.this, "Error with text encoding. "+e, Toast.LENGTH_LONG).show();
                        }
                    });

//                    } else {
//                        Toast.makeText(
//                                this, "Check Internet Connectivity and try again", Toast.LENGTH_LONG).show();
//                    }
                }

            }
        });

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, Login.class));
                finish();
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        String conf_pass = confirm_password.getText().toString();

        String password = account_password.getText().toString();

        if (full_name.getText().toString().isEmpty()) {
            full_name.setError("Enter your Full name please");
            valid = false;
        } else {
            full_name.setError(null);
        }

        if(country.getText().toString().isEmpty())
        {
            country.setError("Please select your Country");
            valid = false;
        }
        else{
            country.setError(null);
        }

        if (password.isEmpty()) {
            account_password.setError("Enter your password");
            valid = false;
        } else {
            account_password.setError(null);

            if (conf_pass.isEmpty()) {
                confirm_password.setError("Confirm your password");
                valid = false;
            } else {
                confirm_password.setError(null);
            }
        }
        return valid;
    }

    @Override
    protected void onPause() {
        overridePendingTransition(0,0);
        super.onPause();
    }

}
