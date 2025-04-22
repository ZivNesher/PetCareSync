package com.example.vet_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare_shared.models.TestResult;

import java.util.List;

public class TestResultAdapter extends RecyclerView.Adapter<TestResultAdapter.ViewHolder> {

    private final Context context;
    private final List<TestResult> testResults;

    public TestResultAdapter(Context context, List<TestResult> testResults) {
        this.context = context;
        this.testResults = testResults;
    }

    @NonNull
    @Override
    public TestResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_test_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestResultAdapter.ViewHolder holder, int position) {
        TestResult result = testResults.get(position);
        holder.testDate.setText(result.getDate());
        holder.testType.setText(result.getType());
        holder.testResult.setText(result.getResult());
    }

    @Override
    public int getItemCount() {
        return testResults.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView testDate, testType, testResult;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            testDate = itemView.findViewById(R.id.testDate);
            testType = itemView.findViewById(R.id.testType);
            testResult = itemView.findViewById(R.id.testResult);
        }
    }
}
