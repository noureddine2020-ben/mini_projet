package com.noureddine.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import android.util.Log;



public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextInputEditText password,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(TextInputEditText)findViewById(R.id.login_email);
        password=(TextInputEditText)findViewById(R.id.login_password);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            Intent home=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(home);
            finish();
        }
    }

   /* @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }*/
    public void Login(View view) {
        String passwordText,emailText;


        passwordText=password.getText().toString();
        emailText=email.getText().toString();


        mAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent home=new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(home);
                            finish();

                        } else {
                            Log.e("my_store", "failure", task.getException());

                        }

                        // ...
                    }
                });


    }

    public void openRegister(View view) {
        Intent register =new Intent (this , RegisterActivity.class);
        startActivity(register);
    }

    }
