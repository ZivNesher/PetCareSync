package com.example.petowner_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petcare_shared.models.HealthLog;
import java.util.List;

public class HealthLogAdapter extends RecyclerView.Adapter<HealthLogAdapter.ViewHolder> {

    private final Context context;
    private final List<HealthLog> logs;

    public HealthLogAdapter(Context context, List<HealthLog> logs) {
        this.context = context;
        this.logs = logs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_health_log, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthLog log = logs.get(position);
        holder.tvLogText.setText(log.getDate() + ": " + log.getNote());

        // Apply color based on who wrote it
        int bgColor = "vet".equalsIgnoreCase(log.getWrittenBy())
                ? ContextCompat.getColor(context, R.color.vetLog)
                : ContextCompat.getColor(context, R.color.ownerLog);

        holder.itemView.setBackgroundColor(bgColor);
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLogText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLogText = itemView.findViewById(R.id.tvLogText);
        }
    }
}
