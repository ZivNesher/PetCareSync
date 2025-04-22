package com.example.vet_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare_shared.BaseActivity;
import com.example.petcare_shared.models.HealthLog;
import com.google.firebase.database.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class VetPetLogsActivity extends BaseActivity {

    private String petId;
    private EditText logInput;
    private Button saveLogButton;
    private RecyclerView logsRecyclerView;

    private ArrayList<HealthLog> logs = new ArrayList<>();
    private HealthLogAdapter adapter;

    @Override
    protected void setupUI() {
        setContentView(R.layout.activity_vet_pet_logs);

        petId = getIntent().getStringExtra("petId");

        logsRecyclerView = findViewById(R.id.logsRecyclerView);
        logInput = findViewById(R.id.logInput);
        saveLogButton = findViewById(R.id.saveLogButton);

        logsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HealthLogAdapter(this, logs);
        logsRecyclerView.setAdapter(adapter);

        saveLogButton.setOnClickListener(v -> saveLog());
        loadLogs();
    }

    private void loadLogs() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("logs");

        ref.orderByChild("petId").equalTo(petId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                logs.clear();
                for (DataSnapshot child : snapshot.getChildren()) {
                    HealthLog log = child.getValue(HealthLog.class);
                    if (log != null) logs.add(log);
                }

                Collections.sort(logs, (log1, log2) -> {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                        Date d1 = sdf.parse(log1.getDate());
                        Date d2 = sdf.parse(log2.getDate());
                        return d1.compareTo(d2);
                    } catch (Exception e) {
                        return 0;
                    }
                });

                adapter.notifyDataSetChanged();
                logsRecyclerView.scrollToPosition(logs.size() - 1);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(VetPetLogsActivity.this, "Error loading logs", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveLog() {
        String note = logInput.getText().toString().trim();
        if (note.isEmpty()) return;

        String id = UUID.randomUUID().toString();
        String date = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        HealthLog log = new HealthLog(id, petId, date, note, "vet");

        FirebaseDatabase.getInstance().getReference("logs").child(id).setValue(log)
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, "Log added", Toast.LENGTH_SHORT).show();
                    logInput.setText("");
                });
    }
}
