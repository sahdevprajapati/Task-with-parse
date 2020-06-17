package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        email = findViewById (R.id.email);
        password = findViewById (R.id.password);
        login = findViewById (R.id.buttonlogin);

        login.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty (email.getText ())) {

                    email.setError ("Email is required!");
                } else if (TextUtils.isEmpty (password.getText ())) {
                    password.setError ("Password is required!");
                } else {
                    ParseUser.logInInBackground (email.getText ().toString (), password.getText ().toString (), new LogInCallback () {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if (parseUser != null) {
                                Toast.makeText (LoginActivity.this, "Welcome", Toast.LENGTH_LONG).show ();
                                Intent intent = new Intent (LoginActivity.this, Dashboard.class);
                                startActivity (intent);
                                finish ();
                            } else {
                                ParseUser.logOut ();
                                Toast.makeText (LoginActivity.this, e.getMessage (), Toast.LENGTH_LONG).show ();
                            }
                        }
                    });
                }
            }
        });

    }

    public void signup(View view) {
        Intent intent = new Intent (LoginActivity.this, RegisterActivity.class);
        startActivity (intent);
    }
}
