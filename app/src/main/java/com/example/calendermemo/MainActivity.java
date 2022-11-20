package com.example.calendermemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    Fragment_months fragment_months;
    Fragment_weeks fragment_weeks;
    Fragment_today fragment_today;
    Fragment_day_add fragment_day_add;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Calendar Memo"); //toolbar 제목

        drawerLayout=findViewById(R.id.layout_drawer);
        navigationView=findViewById(R.id.nav_bar);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        fragment_months = new Fragment_months();
        fragment_weeks = new Fragment_weeks();
        fragment_today = new Fragment_today();
        fragment_day_add = new Fragment_day_add();
        getSupportFragmentManager().beginTransaction().replace(R.id.containers,fragment_months).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_bar_menu1:
                        Toast.makeText(getApplicationContext(),"메인 화면입니다",Toast.LENGTH_SHORT).show();
                    case R.id.nav_bar_menu2:
                        Intent intent = new Intent(MainActivity.this, AndroidExplorerActivity.class);
                        startActivity(intent);
                        finish();
                }
                return true;
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnItemSelectedListener(item -> {
           switch(item.getItemId()) {
               case R.id.cal_months:
                   getSupportFragmentManager().beginTransaction().replace(R.id.containers,fragment_months).commit();
                   return true;
               case R.id.cal_weeks:
                   getSupportFragmentManager().beginTransaction().replace(R.id.containers,fragment_weeks).commit();
                   return true;
               case R.id.cal_today:
                   getSupportFragmentManager().beginTransaction().replace(R.id.containers,fragment_today).commit();
                   return true;
               case R.id.plus:
                   getSupportFragmentManager().beginTransaction().replace(R.id.containers,fragment_day_add).commit();
                   return true;
           }
            return true;
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.setting_menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting_save:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}