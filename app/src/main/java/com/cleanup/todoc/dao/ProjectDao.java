package com.cleanup.todoc.dao;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    //Insert one project
    @Insert(onConflict = IGNORE)
    void insertProject(Project project);

    // Delete one project
    @Delete
    void deleteProject(Project project);

    //Delete one project by id
    @Query("DELETE FROM project WHERE id = :projectId")
    void deleteByProjectId(long projectId);

    //Get all projects
    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getAllProjects();

    //Delete All
    @Query("DELETE FROM Project")
    void deleteAll();
}
