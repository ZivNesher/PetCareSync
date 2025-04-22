package com.example.vet_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare_shared.BaseActivity;
import com.example.petcare_shared.models.Pet;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class VetHomeActivity extends BaseActivity {

    private RecyclerView vetPetRecyclerView;
    private ArrayList<Pet> pets = new ArrayList<>();
    private PetAdapter adapter;

    @Override
    protected void setupUI() {
        setContentView(R.layout.activity_vet_home);

        vetPetRecyclerView = findViewById(R.id.vetPetRecyclerView);
        vetPetRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PetAdapter(pets, pet -> {
            Intent intent = new Intent(this, VetPetLogsActivity.class);
            intent.putExtra("petId", pet.getId());
            startActivity(intent);
        });

        vetPetRecyclerView.setAdapter(adapter);
        loadAllPets();
    }

    private void loadAllPets() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("pets");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                pets.clear();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Pet pet = child.getValue(Pet.class);
                    if (pet != null) pets.add(pet);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }
}
