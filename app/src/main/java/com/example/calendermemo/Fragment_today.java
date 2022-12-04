package com.example.calendermemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calendermemo.adapters.ScheduleAdapter;
import com.example.calendermemo.db.DBLoader;
import com.example.calendermemo.model.ScheduleData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Fragment_today extends Fragment {
    //private ArrayList<ScheduleData> scheduleData;
    private ScheduleAdapter scheduleAdapter;
    private ScheduleData item;
    private String today_date;
    private DBLoader dbloader;
    private String selectDay;
    TextView today_Text;
    RecyclerView rv_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_today,container,false);
        Context context = container.getContext();

        today_Text = (TextView) v.findViewById(R.id.today_textView);
        rv_view = (RecyclerView) v.findViewById(R.id.today_recyclerview);
        scheduleAdapter = new ScheduleAdapter(context);
        rv_view.setAdapter(scheduleAdapter);
        rv_view.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
        rv_view.setHasFixedSize(true);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        today_date = String.valueOf(dateFormat.format(System.currentTimeMillis()));
        today_Text.setText(today_date);

        selectDay = today_date.replace(".","");

        dbloader = new DBLoader(context);
        scheduleAdapter.setList(dbloader.getScheduleList(selectDay));
        scheduleAdapter.notifyDataSetChanged();


        return v;
    }
}