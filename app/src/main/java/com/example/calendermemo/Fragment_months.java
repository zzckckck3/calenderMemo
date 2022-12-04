package com.example.calendermemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calendermemo.Adapter.ScheduleAdapter;
import com.example.calendermemo.db.DBHelper;
import com.example.calendermemo.db.DBLoader;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;


public class Fragment_months extends Fragment {
    private MaterialCalendarView calendarView;
    private RecyclerView rv_schedule;
    private ScheduleAdapter scheduleAdapter;
    private DBLoader dbloader;
    private TextView date_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_months,container,false);
        Context context = container.getContext();
        TodayDecorator todayDecorator = new TodayDecorator();

        calendarView = (MaterialCalendarView) v.findViewById(R.id.material_calendar);
        date_text = (TextView) v.findViewById(R.id.date_text);
        rv_schedule = (RecyclerView) v.findViewById(R.id.schedule);

        // schedule view
        scheduleAdapter = new ScheduleAdapter(context);
        rv_schedule.setAdapter(scheduleAdapter);
        rv_schedule.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));

        // calendar view
        calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        calendarView.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));
        calendarView.addDecorators(todayDecorator);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() { // 달력의 날짜 클릭 이벤트
            @Override
            public void onDateSelected(MaterialCalendarView widget,CalendarDay date, boolean selected) {
                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDay();

                String selectDay = Integer.toString(year)+Integer.toString(month)+Integer.toString(day);
                dbloader = new DBLoader(context);
                scheduleAdapter.setList(dbloader.getScheduleList(selectDay));
                scheduleAdapter.notifyDataSetChanged();

                date_text.setText(Integer.toString(year)+"년 "+Integer.toString(month)+"월 "+Integer.toString(day)+"일");
            }
        });

        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton btn_add = (FloatingActionButton) view.findViewById(R.id.floatingButton);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), add_schedule_Activity.class);
                startActivity(intent);
            }
        });
    }
}

