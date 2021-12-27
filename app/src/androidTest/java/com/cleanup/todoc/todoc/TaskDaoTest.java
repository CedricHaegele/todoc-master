package com.cleanup.todoc.todoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.todoc.utils.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)

public class TaskDaoTest {

    // DATA SET FOR TEST
    private TodocDatabase database;
    private static Task taskTest = new Task (1,2,"Nettoyer les vitres",100);
    private static Project projectTest = new Project(2,"Travailler en équipe", android.R.color.holo_blue_dark);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDatabase.class)
               .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {

        List<Task> task = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(task.isEmpty());
    }


    @Test
    public void insertAndGetTasks() throws InterruptedException {

        this.database.projectDao().createProject(projectTest);
        this.database.taskDao().insertTask(taskTest);

        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(tasks.size() == 1);
    }


    @Test
    public void insertAndGetProject() throws InterruptedException {

        this.database.projectDao().createProject(projectTest);

        List<Project> projectTest = LiveDataTestUtil.getValue(this.database.projectDao().getAllProjects());

        assertEquals(projectTest.get(0).getId(),2);
        assertNotEquals(projectTest.get(0).getName(),"Travailler à la maison");

    }

    @Test
    public void insertNewTask() throws InterruptedException {

        this.database.projectDao().createProject(projectTest);
        this.database.taskDao().insertTask(taskTest);

        Task task = LiveDataTestUtil.getValue(this.database.taskDao().getTasks()).get(0);
        assertEquals(task.getName(),"Nettoyer les vitres");
        assertNotEquals(task.getId(),4);
    }

    @Test
    public void insertAndDeleteTask() throws InterruptedException {

        this.database.projectDao().createProject(projectTest);
        this.database.taskDao().insertTask(taskTest);

        this.database.taskDao().deleteTask(taskTest);
        List<Task> tasks= LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(tasks.isEmpty());
    }

}