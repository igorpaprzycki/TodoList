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
import butterknife.OnClick;

/**
 * Created by igypap on 18.12.16.
 */

public class TodoTaskAdapter extends RecyclerView.Adapter<TodoTaskAdapter.TodoViewHolder> {
    private List<TodoTask> mTasks;
    private OnClickListener mClickListener;

    public TodoTaskAdapter(List<TodoTask> mTasks, OnClickListener mClickListener) {
        this.mTasks = mTasks;
        this.mClickListener = mClickListener;
    }

    public void setTasks(List<TodoTask> tasks) {
        this.mTasks = tasks;
        notifyDataSetChanged(); //refresh the data on the screen, use this method in setters of Adapter class
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //translate XML to View object:
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
        holder.mCurrentPosition = position;
        holder.mCurrentTask = task;

    }

    @Override
    public int getItemCount() {
        //how many elements to display?
        return mTasks.size();
    }

    public interface OnClickListener {
        //method invoking when user clicks on RecyclerView element (row)
        void onClick(TodoTask task, int position);
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        //only once find the elements and assign to variables:
        @BindView(R.id.task_done)
        CheckBox mDone;
        @BindView(R.id.task_title)
        TextView mTitle;

        TodoTask mCurrentTask;
        int mCurrentPosition;

        public TodoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @OnClick
        void onItemClick() {
            if (mClickListener != null) {
                mClickListener.onClick(mCurrentTask, mCurrentPosition);
            }
        }
    }
}
