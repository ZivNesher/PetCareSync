package com.example.vet_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare_shared.models.Pet;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    public interface OnPetClickListener {
        void onPetClick(Pet pet);
    }

    private final List<Pet> pets;
    private final OnPetClickListener listener;

    public PetAdapter(List<Pet> pets, OnPetClickListener listener) {
        this.pets = pets;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet pet = pets.get(position);
        holder.tvPetName.setText(pet.getName());
        holder.tvSpecies.setText(pet.getSpecies());

        holder.itemView.setOnClickListener(v -> listener.onPetClick(pet));
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {
        TextView tvPetName, tvSpecies;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPetName = itemView.findViewById(R.id.tvPetName);
            tvSpecies = itemView.findViewById(R.id.tvSpecies);
        }
    }
}
