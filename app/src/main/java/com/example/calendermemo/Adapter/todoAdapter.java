package com.example.calendermemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendermemo.R;
import com.example.calendermemo.TodoData;

import java.util.ArrayList;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.ViewHolder>{
    private Context context;
    private ArrayList<TodoData> dataList;

   public todoAdapter(Context context, ArrayList<TodoData> data){
       this.context = context;
       this.dataList = data;
       notifyDataSetChanged();
   }

    @Override
    public todoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.todolist_item_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(todoAdapter.ViewHolder holder, int position) {
        TodoData data = dataList.get(position);
        holder.todoTitle.setText(data.getTitle());
        holder.todoContents.setText(data.getContents());

        //holder.layoutTodo.setVisibility(View.VISIBLE);

        if(!data.isDone()){
            holder.doneBtn.setVisibility(View.VISIBLE);
        }
        else{ //체크표시 클릭시
            holder.doneBtn.setVisibility(View.INVISIBLE);
            holder.layoutTodo.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_item_list_done,null));
        }

        holder.doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setDone(true);
                notifyDataSetChanged();
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastPosition = holder.getBindingAdapterPosition(); // position 의 값이 불명확하므로 대체
                dataList.remove(lastPosition);
                holder.layoutTodo.setBackground(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_item_list, null));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout layoutTodo;
        TextView todoTitle, todoContents;
        ImageButton doneBtn, deleteBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            layoutTodo = itemView.findViewById(R.id.todolist_itemView);
            todoTitle = (TextView) itemView.findViewById(R.id.todo_item_title);
            todoContents = (TextView) itemView.findViewById(R.id.todo_item_contents);
            doneBtn = (ImageButton) itemView.findViewById(R.id.todo_done);
            deleteBtn = (ImageButton) itemView.findViewById(R.id.todo_delete);
        }

    }
    public void setItems(ArrayList<TodoData> items){
       this.dataList = items;
    }
}
