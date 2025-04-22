package com.example.petowner_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare_shared.BaseActivity;
import com.example.petcare_shared.models.Pet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    private RecyclerView petRecyclerView;
    private Button btnAddPet;
    private ArrayList<Pet> pets = new ArrayList<>();
    private PetAdapter adapter;

    @Override
    protected void setupUI() {
        setContentView(R.layout.activity_home);

        petRecyclerView = findViewById(R.id.petRecyclerView);
        btnAddPet = findViewById(R.id.btnAddPet);

        petRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PetAdapter(pets, pet -> {
            Intent intent = new Intent(HomeActivity.this, PetLogsActivity.class);
            intent.putExtra("petId", pet.getId());
            startActivity(intent);
        });
        petRecyclerView.setAdapter(adapter);

        btnAddPet.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, AddPetActivity.class))
        );

        loadPets();
    }

    private void loadPets() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference petsRef = FirebaseDatabase.getInstance().getReference("pets");

        petsRef.orderByChild("ownerId").equalTo(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                pets.clear();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Pet pet = child.getValue(Pet.class);
                    if (pet != null) {
                        pets.add(pet);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }
}
