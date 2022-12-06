package com.example.calendermemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendermemo.R;
import com.example.calendermemo.add_schedule_Activity;
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
        // recyclerview 에서 item 클릭시 스케줄 수정
        holder.layoutSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scheduleActivity = new Intent(context, add_schedule_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("scheduleData",data);
                scheduleActivity.putExtras(bundle);
                context.startActivity(scheduleActivity);
            }
        });

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

    public class ViewHolder extends RecyclerView.ViewHolder{
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
