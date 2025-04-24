package com.example.assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);


        TaskModel task = (TaskModel) getIntent().getSerializableExtra("task");

        TextView tvDescription = findViewById(R.id.tv_task_description);
        Button btnBack = findViewById(R.id.btn_back);


        if (task != null) {
            tvDescription.setText(task.getDescription());
        } else {
            tvDescription.setText("No description available.");
        }


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
