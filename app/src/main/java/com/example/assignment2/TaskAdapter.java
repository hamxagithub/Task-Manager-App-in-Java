package com.example.assignment2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final Context context;
    private final List<TaskModel> tasks;
    private final TaskRepository taskRepository;

    public TaskAdapter(Context context, List<TaskModel> tasks) {
        this.context = context;
        this.tasks = tasks;
        this.taskRepository = new TaskRepository(context);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskModel task = tasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView titleText, priorityText, dateText;
        FloatingActionButton fabUpdate, fabDelete;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.task_title);
            priorityText = itemView.findViewById(R.id.task_priority);
            dateText = itemView.findViewById(R.id.task_date);
            fabUpdate = itemView.findViewById(R.id.fab_update_task);
            fabDelete = itemView.findViewById(R.id.fab_delete_task);
        }

        void bind(final TaskModel task) {

            titleText.setText(task.getTitle());
            priorityText.setText("Priority: " + task.getPriority());
            dateText.setText("Date: " + task.getDate());


            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, TaskDetailsActivity.class);
                intent.putExtra("task", task);
                context.startActivity(intent);
            });


            fabUpdate.setOnClickListener(v -> {
                new AlertDialog.Builder(context)
                        .setTitle("Update Task")
                        .setMessage("Do you want to update this task?")
                        .setPositiveButton("Yes", (dialog, which) -> {

                            Intent intent = new Intent(context, AddEditTaskActivity.class);
                            intent.putExtra("task", task);
                            context.startActivity(intent);
                        })
                        .setNegativeButton("No", null)
                        .show();
            });


            fabDelete.setOnClickListener(v -> {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Task")
                        .setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton("Delete", (dialog, which) -> {

                            taskRepository.deleteTask(task.getId());
                            int pos = getAdapterPosition();
                            tasks.remove(pos);
                            notifyItemRemoved(pos);
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            });

        }
    }
}
