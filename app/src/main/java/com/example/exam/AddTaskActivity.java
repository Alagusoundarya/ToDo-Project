package com.example.exam;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {
    private EditText taskName, description;
    private Button deadlineButton, submitButton;
    private Calendar deadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskName = findViewById(R.id.taskName);
        description = findViewById(R.id.description);
        deadlineButton = findViewById(R.id.deadlineButton);
        submitButton = findViewById(R.id.submitButton);

        deadline = Calendar.getInstance();

        deadlineButton.setOnClickListener(view -> {
            DatePickerDialog datePicker = new DatePickerDialog(this,
                    (DatePicker view1, int year, int month, int dayOfMonth) -> {
                        deadline.set(Calendar.YEAR, year);
                        deadline.set(Calendar.MONTH, month);
                        deadline.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        TimePickerDialog timePicker = new TimePickerDialog(this,
                                (TimePicker view2, int hourOfDay, int minute) -> {
                                    deadline.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                    deadline.set(Calendar.MINUTE, minute);
                                }, deadline.get(Calendar.HOUR_OF_DAY), deadline.get(Calendar.MINUTE), false);
                        timePicker.show();
                    }, deadline.get(Calendar.YEAR), deadline.get(Calendar.MONTH), deadline.get(Calendar.DAY_OF_MONTH));
            datePicker.show();
        });

        submitButton.setOnClickListener(view -> {
            String name = taskName.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(this, "Task Name is mandatory", Toast.LENGTH_SHORT).show();
                return;
            }

            TaskDatabaseHelper dbHelper = new TaskDatabaseHelper(this);
            dbHelper.insertTask(name, description.getText().toString(), deadline.getTimeInMillis());
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
