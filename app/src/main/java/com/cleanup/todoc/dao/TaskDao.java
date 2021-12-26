package com.cleanup.todoc.dao;

import static androidx.room.OnConflictStrategy.IGNORE;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao

public interface TaskDao {


    @Query("SELECT * FROM Task ")

    LiveData<List<Task>> getTasks();


    @Query("SELECT * FROM Task WHERE projectId = :projectId")
    Cursor getTasksWithCursor(long projectId);


    @Insert

    long insertTask(Task task);


    @Update

    int updateTask(Task task);


    @Delete
    void deleteTask(Task task);

}
