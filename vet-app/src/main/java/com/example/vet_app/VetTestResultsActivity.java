package com.example.vet_app;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare_shared.BaseActivity;
import com.example.petcare_shared.models.TestResult;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VetTestResultsActivity extends BaseActivity {

    private String petId;
    private RecyclerView testResultsRecyclerView;
    private TestResultAdapter adapter;
    private ArrayList<TestResult> testResults = new ArrayList<>();

    @Override
    protected void setupUI() {
        setContentView(R.layout.activity_vet_test_results);

        petId = getIntent().getStringExtra("petId");
        testResultsRecyclerView = findViewById(R.id.testResultsRecyclerView);
        testResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TestResultAdapter(this, testResults);
        testResultsRecyclerView.setAdapter(adapter);

        loadTestResults();
    }

//    private void loadTestResults() {
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("testResults");
//
//        ref.orderByChild("petId").equalTo(petId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                testResults.clear();
//                for (DataSnapshot child : snapshot.getChildren()) {
//                    TestResult result = child.getValue(TestResult.class);
//                    if (result != null) testResults.add(result);
//                }
//
//                Collections.sort(testResults, Comparator.comparing(TestResult::getDate));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {}
//        });
//    }
private void loadTestResults() {
    testResults.clear();

    testResults.add(new TestResult("1", petId, "15.03.2025", "Blood Test", "Hemoglobin levels normal. WBC count within range."));
    testResults.add(new TestResult("2", petId, "18.03.2025", "MRI", "No abnormalities detected in spine."));
    testResults.add(new TestResult("3", petId, "25.03.2025", "EMG", "Mild nerve compression in left leg."));
    testResults.add(new TestResult("4", petId, "01.04.2025", "Blood Test", "Slightly elevated cholesterol."));
    testResults.add(new TestResult("5", petId, "10.04.2025", "CT Scan", "Liver and kidney appear normal."));

    adapter.notifyDataSetChanged();
}

}
