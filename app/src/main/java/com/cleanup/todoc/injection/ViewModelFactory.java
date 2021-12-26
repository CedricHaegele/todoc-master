package com.cleanup.todoc.injection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;

import com.cleanup.todoc.repositories.TaskDataRepository;

import com.cleanup.todoc.view_model.TaskViewModel;
import com.google.firebase.database.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {


    private final TaskDataRepository taskDataSource;

    private final ProjectDataRepository projectDataSource;

    private final Executor executor;


  ViewModelFactory(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor){
      this.taskDataSource=taskDataSource;
      this.projectDataSource=projectDataSource;
      this.executor=executor;

  }


    @Override

    @NotNull

    public <T extends ViewModel>  T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(TaskViewModel.class)) {

            return (T) new TaskViewModel(taskDataSource, projectDataSource, executor);

        }

        throw new IllegalArgumentException("Unknown ViewModel class");

    }

}