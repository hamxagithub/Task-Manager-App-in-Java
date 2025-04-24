package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final TaskDatabaseHelper dbHelper;
    private final SQLiteDatabase db;

    public TaskRepository(Context context) {

        dbHelper = new TaskDatabaseHelper(context);

        db = dbHelper.getWritableDatabase();
    }

    public long addTask(TaskModel task) {
        ContentValues values = new ContentValues();
        values.put(TaskDatabaseHelper.COLUMN_TITLE, task.getTitle());
        values.put(TaskDatabaseHelper.COLUMN_DESCRIPTION, task.getDescription());
        values.put(TaskDatabaseHelper.COLUMN_DATE, task.getDate());
        values.put(TaskDatabaseHelper.COLUMN_PRIORITY, task.getPriority());
        return db.insert(TaskDatabaseHelper.TABLE_TASKS, null, values);
    }

    public TaskModel getTask(long id) {
        Cursor cursor = db.query(
                TaskDatabaseHelper.TABLE_TASKS,
                new String[]{
                        TaskDatabaseHelper.COLUMN_ID,
                        TaskDatabaseHelper.COLUMN_TITLE,
                        TaskDatabaseHelper.COLUMN_DESCRIPTION,
                        TaskDatabaseHelper.COLUMN_DATE,
                        TaskDatabaseHelper.COLUMN_PRIORITY
                },
                TaskDatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            TaskModel task = new TaskModel(
                    cursor.getLong(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_PRIORITY))
            );
            cursor.close();
            return task;
        }
        return null;
    }

    public List<TaskModel> getAllTasks() {
        List<TaskModel> tasks = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TaskDatabaseHelper.TABLE_TASKS, null);

        if (cursor.moveToFirst()) {
            do {
                TaskModel task = new TaskModel(
                        cursor.getLong(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_DATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_PRIORITY))
                );
                tasks.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tasks;
    }

    public int updateTask(TaskModel task) {
        ContentValues values = new ContentValues();
        values.put(TaskDatabaseHelper.COLUMN_TITLE, task.getTitle());
        values.put(TaskDatabaseHelper.COLUMN_DESCRIPTION, task.getDescription());
        values.put(TaskDatabaseHelper.COLUMN_DATE, task.getDate());
        values.put(TaskDatabaseHelper.COLUMN_PRIORITY, task.getPriority());

        return db.update(
                TaskDatabaseHelper.TABLE_TASKS,
                values,
                TaskDatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(task.getId())}
        );
    }

    public void deleteTask(long id) {
        db.delete(
                TaskDatabaseHelper.TABLE_TASKS,
                TaskDatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }


    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
