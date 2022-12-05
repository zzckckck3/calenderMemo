package com.example.calendermemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    private Button data_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        data_btn = (Button)findViewById(R.id.data_reset);

        data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this, "데이터 초기화", Toast.LENGTH_SHORT).show();
            }
        });
    }
}