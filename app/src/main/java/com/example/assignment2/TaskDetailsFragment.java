package com.example.assignment2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TaskDetailsFragment extends Fragment {

    private TaskModel task;

    public static TaskDetailsFragment newInstance(TaskModel task) {
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("task", task);
        fragment.setArguments(args);
        return fragment;
    }

    public TaskDetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            task = (TaskModel) getArguments().getSerializable("task");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_details, container, false);
        TextView titleText = view.findViewById(R.id.task_title);
        TextView descText = view.findViewById(R.id.task_description);
        TextView dateText = view.findViewById(R.id.task_date);
        TextView priorityText = view.findViewById(R.id.task_priority);

        if (task != null) {
            titleText.setText(task.getTitle());
            descText.setText(task.getDescription());
            dateText.setText(task.getDate());
            priorityText.setText(task.getPriority());
        }
        return view;
    }
}
