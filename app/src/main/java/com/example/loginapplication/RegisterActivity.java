package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {
    EditText name, email, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);

        name = findViewById (R.id.name);
        email = findViewById (R.id.email);
        password = findViewById (R.id.password);
        register = findViewById (R.id.buttonregister);

        register.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty (name.getText ())) {

                    name.setError ("Name is required!");
                } else if (TextUtils.isEmpty (email.getText ())) {

                    email.setError ("Email is required!");
                } else if (TextUtils.isEmpty (password.getText ())) {
                    password.setError ("Password is required!");
                } else {
                    ParseUser user = new ParseUser ();
                    user.setUsername (name.getText ().toString ().trim ());
                    user.setEmail (email.getText ().toString ().trim ());
                    user.setPassword (password.getText ().toString ());
                    user.put ("name", name.getText ().toString ());
                    user.signUpInBackground (new SignUpCallback () {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // Toast.makeText (RegisterActivity.this, "Welcome", Toast.LENGTH_LONG).show ();
                                Intent intent = new Intent (RegisterActivity.this, LoginActivity.class);
                                startActivity (intent);
                            } else {
                                ParseUser.logOut ();
                                Toast.makeText (RegisterActivity.this, e.getMessage (), Toast.LENGTH_LONG).show ();
                            }
                        }
                    });
                }
            }
        });
    }

    public void Login(View view) {
        Intent intent = new Intent (RegisterActivity.this, LoginActivity.class);
        startActivity (intent);
    }
}
