package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.parse.ParseUser;

public class Dashboard extends AppCompatActivity {
    TextView tvname, tvemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_dashboard);

        ShimmerFrameLayout container = (ShimmerFrameLayout) findViewById (R.id.shimmer_effect);
        container.startShimmer ();

        ParseUser currentUser = ParseUser.getCurrentUser ();

        tvname = findViewById (R.id.txtname);
        tvemail = findViewById (R.id.txtemail);

        if (currentUser != null) {
            tvname.setText (currentUser.getString ("name"));
            tvemail.setText (currentUser.getEmail ());
        }
    }

    public void Logout(View view) {
        ParseUser.logOut ();
        Intent intent = new Intent (Dashboard.this, LoginActivity.class);
        startActivity (intent);
        finish ();
    }
}
