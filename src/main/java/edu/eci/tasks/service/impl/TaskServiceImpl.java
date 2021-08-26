package edu.eci.tasks.service.impl;

import edu.eci.tasks.data.Task;
import edu.eci.tasks.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskServiceImpl implements TaskService {

    private static final ConcurrentHashMap<String,Task> tasks = new ConcurrentHashMap<>();
    private AtomicInteger taskId = new AtomicInteger(0);

    @Override
    public Task create(Task task) {
        Integer idTask = taskId.getAndIncrement();
        String idTaskStr = idTask.toString();
        task.setId(idTaskStr);
        tasks.put(idTaskStr, task);
        return task;
    }

    @Override
    public Task findById(String id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> all() {
        List<Task> allTasks = new ArrayList<>();
        for (String keyTask : tasks.keySet()){
            allTasks.add(tasks.get(keyTask));
        }
        return allTasks;
    }

    @Override
    public void deleteById(String id) {
        tasks.remove(id);

    }

    @Override
    public Task update(Task task, String id) {
        Task updateTask = null;
        task.setId(id);
        updateTask = tasks.put(id, task);
        return updateTask;
    }
}
