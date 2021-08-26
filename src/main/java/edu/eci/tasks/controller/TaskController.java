package edu.eci.tasks.controller;

import edu.eci.tasks.data.Task;
import edu.eci.tasks.dto.TaskDto;
import edu.eci.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/task")

public class TaskController {

    private final TaskService taskService;

    public TaskController( @Autowired TaskService taskService ) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> all() {
        try {
            return ResponseEntity.status(200).body(taskService.all());
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById( @PathVariable String id ) {
        try{
            return ResponseEntity.status(200).body(taskService.findById(id));
        }catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskDto taskDto){
        try{
            Task newTask = new Task(taskDto.getName(), taskDto.getDescription(), taskDto.getStatus(), taskDto.getAssignedTo(), taskDto.getDueDate(),taskDto.getCreated());
            return ResponseEntity.status(200).body(taskService.create(newTask));
        }catch (Exception e) {
            return ResponseEntity.status(400).build();

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update (@RequestBody TaskDto taskDto, @PathVariable String id){
        try{
            Task newTask = new Task(taskDto.getName(), taskDto.getDescription(), taskDto.getStatus(), taskDto.getAssignedTo(), taskDto.getDueDate(),taskDto.getCreated());
            return ResponseEntity.status(200).body(taskService.update(newTask, id));
        }catch (Exception e){
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById (@PathVariable String id){
        try{
            boolean taskDeleted = false;
            taskService.deleteById(id);
            taskDeleted = taskService.findById(id) != null ? false : true;
            return ResponseEntity.status(200).body(taskDeleted);
        }catch (Exception e){
            return ResponseEntity.status(404).build();
        }
    }
}
