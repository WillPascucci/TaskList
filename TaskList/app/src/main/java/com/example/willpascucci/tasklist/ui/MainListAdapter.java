package com.example.willpascucci.tasklist.ui;


import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.willpascucci.tasklist.R;
import com.example.willpascucci.tasklist.model.Task;

import java.util.List;

/**
 * Created by Nathan Walker on 2/4/15.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {

    public List<Task> taskList;
    private ViewHolder mHolder;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.textView);
        }

    }

    public MainListAdapter(List<Task> l) {
        taskList = l;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_list_item, null);
        mHolder = new ViewHolder(v);
        return mHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        final Task task = taskList.get(i);
        holder.text.setText(task.text);
        holder.text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                task.text = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public int getItemCount() {
        return taskList.size();
    }
}
