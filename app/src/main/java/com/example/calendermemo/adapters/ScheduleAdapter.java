package com.example.calendermemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendermemo.R;
import com.example.calendermemo.model.ScheduleData;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ScheduleData> scheduleList = new ArrayList<ScheduleData>();

    public ScheduleAdapter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.schedule_item_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ScheduleData data = scheduleList.get(position);
        holder.layoutSchedule.setVisibility(View.VISIBLE);
        holder.title_text.setText(data.getTitle());
        holder.startTime_text.setText(data.getStart_time());
        holder.finishTime_text.setText(data.getFinish_time());
        holder.location_text.setText(data.getLocation());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public void setList(ArrayList<ScheduleData> array){
        scheduleList.clear();
        scheduleList.addAll(array);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout layoutSchedule;
        TextView title_text,startTime_text,finishTime_text,location_text;

        public ViewHolder(View view) {
            super(view);
            layoutSchedule = view.findViewById(R.id.schedule_item);
            title_text = (TextView) view.findViewById(R.id.title_text);
            startTime_text = (TextView) view.findViewById(R.id.startTime_text);
            finishTime_text = (TextView) view.findViewById(R.id.finish_text);
            location_text = (TextView) view.findViewById(R.id.location_text);
        }

    }
}
