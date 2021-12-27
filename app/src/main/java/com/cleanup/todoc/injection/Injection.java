package com.cleanup.todoc.injection;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.view_model.TaskViewModel;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection{


    public static TaskDataRepository provideTaskDataSource(Context context) {
        TodocDatabase database = TodocDatabase.getInstance(context);
        return new TaskDataRepository(database.taskDao());
    }

    public static ProjectDataRepository provideProjectDataSource(Context context) {
        TodocDatabase database = TodocDatabase.getInstance(context);
        return new ProjectDataRepository(database.projectDao());
    }


    public static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }


    public static ViewModelFactory provideViewModelFactory(Context context) {
        TaskDataRepository

                taskDataSource = provideTaskDataSource(context);
        ProjectDataRepository projectDataSource = provideProjectDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(taskDataSource, projectDataSource, executor);

    }

}


