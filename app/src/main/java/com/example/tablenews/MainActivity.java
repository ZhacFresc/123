package com.example.tablenews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView nav_view;
    private FirebaseAuth mAuth;
    private TextView userMail;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    @Override
    protected void onStart(){
        super.onStart();
        getUserData();
    }
    private void getUserData(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            userMail.setText(currentUser.getEmail());
        }
        else {
            userMail.setText(R.string.sign_in_or_sign_up);
        }
    }

    private void init(){
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        mAuth = FirebaseAuth.getInstance();
        userMail = nav_view.getHeaderView(0).findViewById(R.id.tvEmail);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("test_i_hate_modific_gradle");
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.id_my_ads:
                Toast.makeText(this, "Pressed id_my_ads", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_cars:
                Toast.makeText(this, "Pressed id_cars", Toast.LENGTH_SHORT).show();
                break;
            case R.id.smartphone_ads:
                Toast.makeText(this, "Pressed smartphone_ads", Toast.LENGTH_SHORT).show();
                break;
            case R.id.computer_ads:
                Toast.makeText(this, "Pressed computer_ads", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dm_ads:
                Toast.makeText(this, "Pressed dm_ads", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sign_in:
                signUpAndInDialog(R.string.sign_in,R.string.sign_in_buton, 1);
                Toast.makeText(this, "Pressed sign_in", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sign_up:
                signUpAndInDialog(R.string.sign_up,R.string.sign_up_buton, 0);
                Toast.makeText(this, "Pressed sign_up", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sign_out:
                Toast.makeText(this, "Pressed sign_out", Toast.LENGTH_SHORT).show();
                signOut();
                break;
        }
        return true;
    }

    private void signUpAndInDialog(int title, int buttonTitle, int index){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_sign_up_layout, null);
        dialogBuilder.setView(dialogView);
        TextView titleTextView = dialogView.findViewById(R.id.tvalertTitle);
        EditText addEmail = dialogView.findViewById(R.id.addEmail);
        EditText addPassword = dialogView.findViewById(R.id.addPassword);
        titleTextView.setText(title);
        Button b = dialogView.findViewById(R.id.button);
        b.setText(buttonTitle);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == 0 ){
                    signUp(addEmail.getText().toString(), addPassword.getText().toString());
                }
                else if (index == 1){
                    signIn(addEmail.getText().toString(), addPassword.getText().toString());
                }
                dialog.dismiss();
            }
        });
        dialog = dialogBuilder.create();
        dialog.show();
    }

    private void signUp(String email, String password){
        if (!email.equals("") && !password.equals("")){
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getUserData();
                        }
                        else {

                            Log.w("Log_my_MainActivity", "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
        else {
            Toast.makeText(this, "email or password empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void signIn(String email, String password) {
        if (!email.equals("") && !password.equals("")) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                getUserData();
                            } else {
                                Toast.makeText(MainActivity.this, "Erore Sign in", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    private void signOut(){
        mAuth.signOut();
        getUserData();
    }
}