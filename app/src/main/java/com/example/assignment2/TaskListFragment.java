package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class TaskListFragment extends Fragment {

    private static final String TAG = "TaskListFragment";
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private TaskRepository taskRepository;
    private TextView tvNoTasks;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        tvNoTasks = view.findViewById(R.id.tv_no_tasks);
        FloatingActionButton fab = view.findViewById(R.id.fab_add_task);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        taskRepository = new TaskRepository(requireContext());

        fab.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddEditTaskActivity.class));
        });

        loadTasks();
        return view;
    }

    private void loadTasks() {
        List<TaskModel> tasks = taskRepository.getAllTasks();
        if (tasks.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvNoTasks.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvNoTasks.setVisibility(View.GONE);

            taskAdapter = new TaskAdapter(requireContext(), tasks);
            recyclerView.setAdapter(taskAdapter);
        }
    }

}
