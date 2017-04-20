package com.geniusnine.android.taxcalculator.Profile;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusnine.android.taxcalculator.LoginActivity.User;
import com.geniusnine.android.taxcalculator.R;
import com.geniusnine.android.taxcalculator.models.Comment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewEmail;
    private DatabaseReference mDataBaseProfile;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();
        mDataBaseProfile = FirebaseDatabase.getInstance().getReference().child("Users");
        textViewName = (TextView)findViewById(R.id.textViewName);
        textViewEmail = (TextView)findViewById(R.id.textViewEmail);

    }
}
