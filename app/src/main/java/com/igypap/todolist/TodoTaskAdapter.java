package com.igypap.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by igypap on 18.12.16.
 */

public class TodoTaskAdapter extends RecyclerView.Adapter<TodoTaskAdapter.TodoViewHolder> {
    private List<TodoTask> mTasks;

    public TodoTaskAdapter(List<TodoTask> mTasks) {
        this.mTasks = mTasks;
    }

    public void setTasks(List<TodoTask> tasks) {
        this.mTasks = tasks;
        notifyDataSetChanged(); //refresh the data on the screen, use this method in setters of Adapter class
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rowView = inflater.inflate(R.layout.list_item_todo, parent, false);
        return new TodoViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        //1. Pobranie elementu danych na pozycji position
        TodoTask task = mTasks.get(position);
        //2. Uzupelnienie widoku (holder) na podstawie pobranego obiektu
        holder.mTitle.setText(task.getName());
        holder.mDone.setChecked(task.isDone());


    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_done)
        CheckBox mDone;
        @BindView(R.id.task_title)
        TextView mTitle;

        public TodoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
