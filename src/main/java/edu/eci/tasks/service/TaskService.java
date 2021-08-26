package edu.eci.tasks.service;

import edu.eci.tasks.data.Task;

import java.util.List;

public interface TaskService {

    Task create(Task task );

    Task findById( String id );

    List<Task> all();

    void deleteById( String id );

    Task update( Task task, String id );
}
