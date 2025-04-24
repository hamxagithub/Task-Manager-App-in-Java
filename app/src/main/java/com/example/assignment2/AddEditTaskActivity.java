package com.example.assignment2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEditTaskActivity extends AppCompatActivity {

    private EditText editTitle, editDescription, editDate, editPriority;
    private Button btnSave;
    private TaskRepository taskRepository;
    private TaskModel currentTask;
    private final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);


        editTitle = findViewById(R.id.edit_title);
        editDescription = findViewById(R.id.edit_description);
        editDate = findViewById(R.id.edit_date);
        editPriority = findViewById(R.id.edit_priority);
        btnSave = findViewById(R.id.btn_save);

        taskRepository = new TaskRepository(this);


        if (getIntent().hasExtra("task")) {
            currentTask = (TaskModel) getIntent().getSerializableExtra("task");
            if (currentTask != null) {
                editTitle.setText(currentTask.getTitle());
                editDescription.setText(currentTask.getDescription());
                editDate.setText(currentTask.getDate());
                editPriority.setText(currentTask.getPriority());
            }
        }


        editDate.setOnClickListener(v -> showDatePicker());

        btnSave.setOnClickListener(v -> saveTask());
    }

    private void showDatePicker() {

        if (!editDate.getText().toString().isEmpty()) {
            parseExistingDate(editDate.getText().toString());
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> {

                    calendar.set(Calendar.YEAR, year1);
                    calendar.set(Calendar.MONTH, month1);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDateField();
                },
                year,
                month,
                day
        );
        datePicker.show();
    }

    private void parseExistingDate(String dateString) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            calendar.setTime(sdf.parse(dateString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateDateField() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        editDate.setText(sdf.format(calendar.getTime()));
    }

    private void saveTask() {
        String title = editTitle.getText().toString().trim();
        String description = editDescription.getText().toString().trim();
        String date = editDate.getText().toString().trim();
        String priority = editPriority.getText().toString().trim();

        if (title.isEmpty() || date.isEmpty() || priority.isEmpty()) {

            return;
        }

        if (currentTask != null) {

            currentTask.setTitle(title);
            currentTask.setDescription(description);
            currentTask.setDate(date);
            currentTask.setPriority(priority);
            taskRepository.updateTask(currentTask);
        } else {

            TaskModel newTask = new TaskModel(title, description, date, priority);
            taskRepository.addTask(newTask);
        }
        finish();
    }
}
