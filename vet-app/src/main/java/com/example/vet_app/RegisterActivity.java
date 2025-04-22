package com.example.vet_app;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petcare_shared.BaseActivity;
import com.example.petcare_shared.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends BaseActivity {

    private EditText emailInput, passwordInput;
    private Button registerButton;

    @Override
    protected void setupUI() {
        setContentView(R.layout.activity_register);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> register());
    }

    private void register() {
        String email = emailInput.getText().toString();
        String pass = passwordInput.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener(authResult -> {
                    String uid = authResult.getUser().getUid();
                    User user = new User(uid, email, "vet");

                    FirebaseDatabase.getInstance().getReference("users").child(uid).setValue(user)
                            .addOnSuccessListener(r -> {
                                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this, VetHomeActivity.class));
                                finish();
                            });
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
