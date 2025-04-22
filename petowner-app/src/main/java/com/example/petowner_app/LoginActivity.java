package com.example.petowner_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petcare_shared.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends BaseActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton, goToRegister;

    @Override
    protected void setupUI() {
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        goToRegister = findViewById(R.id.goToRegister);

        loginButton.setOnClickListener(v -> login());
        goToRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void login() {
        String email = emailInput.getText().toString();
        String pass = passwordInput.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener(authResult -> {
                    startActivity(new Intent(this, HomeActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
