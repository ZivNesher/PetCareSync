package com.example.petowner_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petcare_shared.BaseActivity;
import com.example.petcare_shared.models.Pet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AddPetActivity extends BaseActivity {

    private EditText nameInput, speciesInput;
    private Button savePetButton;

    @Override
    protected void setupUI() {
        setContentView(R.layout.activity_add_pet);

        nameInput = findViewById(R.id.nameInput);
        speciesInput = findViewById(R.id.speciesInput);
        savePetButton = findViewById(R.id.savePetButton);

        savePetButton.setOnClickListener(v -> savePet());
    }

    private void savePet() {
        String id = UUID.randomUUID().toString();
        String name = nameInput.getText().toString();
        String species = speciesInput.getText().toString();
        String ownerId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Pet pet = new Pet(id, name, species, ownerId); // now ownerId is included!

        FirebaseDatabase.getInstance().getReference("pets")
                .child(id).setValue(pet)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Pet added", Toast.LENGTH_SHORT).show();
                    finish(); // go back to HomeActivity
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

}
