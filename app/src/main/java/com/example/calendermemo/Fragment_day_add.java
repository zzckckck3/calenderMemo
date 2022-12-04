package com.example.calendermemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calendermemo.adapters.todoAdapter;
import com.example.calendermemo.model.TodoData;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Fragment_day_add extends Fragment {
    private ArrayList<TodoData> todoList = new ArrayList<TodoData>();
    RecyclerView todoRecyclerView;
    todoAdapter todoAdapter;
    TextView mWeek, mDate;
    ImageButton addTodo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day_add,container,false);
        Context context = container.getContext();

        todoRecyclerView = (RecyclerView) v.findViewById(R.id.rvTodo);
        mWeek = (TextView) v.findViewById(R.id.week);
        mDate = (TextView) v.findViewById(R.id.date);
        addTodo = (ImageButton) v.findViewById(R.id.addTodo);

        SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE", Locale.US);
        SimpleDateFormat dayFormat = new SimpleDateFormat("d", Locale.US);

        mWeek.setText(String.valueOf(weekdayFormat.format(System.currentTimeMillis())));
        mDate.setText(String.valueOf(dayFormat.format(System.currentTimeMillis()))+ "th");

        todoAdapter = new todoAdapter(context, todoList);
        todoRecyclerView.setAdapter(todoAdapter);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
        todoRecyclerView.setHasFixedSize(true);

        Dialog writeDialog = new Dialog(context);
        writeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        writeDialog.setContentView(R.layout.todolist_write);

        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeDialog.show();

                EditText writeTitle = (EditText) writeDialog.findViewById(R.id.writeTitle);
                EditText writeContents = (EditText) writeDialog.findViewById(R.id.writeContents);

                writeDialog.findViewById(R.id.todolist_saveBtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                        String title = writeTitle.getText().toString();
                        String contents = writeContents.getText().toString();

                        writeTitle.setText("");
                        writeContents.setText("");

                        todoList.add(new TodoData(title, contents, false));
                        todoAdapter.notifyDataSetChanged();

                        writeDialog.dismiss();
                    }
                });

                writeDialog.findViewById(R.id.todolist_cancelBtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();
                        writeTitle.setText("");
                        writeContents.setText("");
                        writeDialog.dismiss();
                    }
                });

            }
        });
        return v;
    }
}