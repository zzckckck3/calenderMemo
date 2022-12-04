package com.example.calendermemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.calendermemo.DB.DBLoader;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class add_schedule_Activity extends AppCompatActivity {
    private ArrayList<ScheduleData> scheduleData;
    private DBLoader dbLoader;
    Button btn_start_date, btn_finish_date;
    Button btn_start, btn_finish;
    Button btn_alarm;
    EditText title_text, location_text;
    TextView start_text, finish_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        Toolbar toolbar = findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("일정 생성");

        btn_start_date = (Button) findViewById(R.id.start_date);
        btn_finish_date = (Button) findViewById(R.id.finish_date);
        btn_start = (Button) findViewById(R.id.start_clock);
        btn_finish = (Button) findViewById(R.id.finish_clock);
        btn_alarm = (Button) findViewById(R.id.alarm);
        title_text = (EditText) findViewById(R.id.edit_title);
        location_text = (EditText) findViewById(R.id.edit_location);
        start_text = findViewById(R.id.start_text);
        finish_text = findViewById(R.id.finish_text);

        // 오늘 날짜로 default
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        btn_start_date.setText(String.valueOf(dateFormat.format(System.currentTimeMillis())));
        btn_finish_date.setText(String.valueOf(dateFormat.format(System.currentTimeMillis())));
        this.setListener();
    }


    public void setListener() {
        btn_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(add_schedule_Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        btn_start_date.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btn_finish_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(add_schedule_Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        btn_finish_date.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        btn_start.setOnClickListener(new View.OnClickListener() { // 시작 시간 입력
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mhour = c.get(Calendar.HOUR);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(add_schedule_Activity.this,android.R.style.Theme_Holo_Light_DarkActionBar,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        start_text.setText(String.format("%02d:%02d",hourOfDay,minute));
                    }
                }, mhour, mMinute,true);
                timePickerDialog.setTitle("시작");
                timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                timePickerDialog.show();
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() { // 종료 시간 입력
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mhour = c.get(Calendar.HOUR);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(add_schedule_Activity.this,android.R.style.Theme_Holo_Light_DarkActionBar,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        finish_text.setText(String.format("%02d:%02d",hourOfDay,minute));
                    }
                }, mhour, mMinute,true);
                timePickerDialog.setTitle("종료");
                timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                timePickerDialog.show();
            }
        });

        /*btn_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int

                TimePickerDialog timePickerDialog = new TimePickerDialog(add_schedule_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                    }
                }true);

            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.schedule_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_saving:
                // 저장 버튼 클릭시: db에 저장
                dbLoader = new DBLoader(getApplicationContext());

                String title = title_text.getText().toString();
                String start_date = btn_start_date.getText().toString().replace("-",""); // 값 예시: 20221204
                String finish_date = btn_finish_date.getText().toString().replace("-","");
                String start_time = start_text.getText().toString();
                String finish_time = finish_text.getText().toString();
                String location = location_text.getText().toString();


                if(!title.equals("")){
                    dbLoader.save(title,start_date,finish_date,start_time,finish_time,location);
                    Intent intent = new Intent(this,MainActivity.class);
                    Toast.makeText(add_schedule_Activity.this, "저장 완료", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Intent intent = new Intent(this,MainActivity.class);
                    Toast.makeText(add_schedule_Activity.this, "저장 실패", Toast.LENGTH_SHORT).show();
                    finish();
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}