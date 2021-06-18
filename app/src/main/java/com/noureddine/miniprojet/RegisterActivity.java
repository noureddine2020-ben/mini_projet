package com.noureddine.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextInputEditText email,password,confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        email=(TextInputEditText)findViewById(R.id.register_email);
        password=(TextInputEditText)findViewById(R.id.register_password);
        confirmPassword=(TextInputEditText)findViewById(R.id.register_confirm_password);
    }


    public void openLogin(View view) {
        Intent login =new Intent (this , MainActivity.class);
        startActivity(login);
    }

    public void Register (View view) {

        String emailText,passwordText,confirmPasswordText;

        emailText=email.getText().toString();
        passwordText=password.getText().toString();
        confirmPasswordText=confirmPassword.getText().toString();

        //validation sur les input

        if(emailText.isEmpty()||emailText.equals(" "))
        {
            email.setError("Fill here please !");
            return;
        }

        if(passwordText.isEmpty()||passwordText.equals(" "))
        {
            password.setError("Fill here please !");
            return;
        }

        if(confirmPasswordText.isEmpty()||confirmPasswordText.equals(" "))
        {
            confirmPassword.setError("Fill here please !");
            return;
        }

        if(passwordText.length()<8)
        {
            password.setError("Length must be greater than 8");
            return;
        }


        if(!confirmPasswordText.equals(passwordText))
        {
            confirmPassword.setError("Password not match !");
            return;
        }


        mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent home=new Intent(RegisterActivity.this,HomeActivity.class);
                            startActivity(home);
                            finish();

                        } else {

                            Log.e("my_store", "failure", task.getException());
                        }
                    }
                });

    }
}