package com.cleanup.todoc.dao;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    //Insert one task
    @Insert(onConflict = IGNORE)
    void insertTask(Task task);

    // Delete one task
    @Delete
    void deleteTask(Task task);

    //Delete one task by id
    @Query("DELETE FROM project WHERE id = :taskId")
    void deleteByTaskId(long taskId);

    //Get all tasks
    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllTasks();

    //Delete All
    @Query("DELETE FROM Task")
    void deleteAll();

    }
