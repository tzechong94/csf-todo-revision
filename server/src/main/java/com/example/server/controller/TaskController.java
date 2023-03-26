package com.example.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.models.Task;
import com.example.server.service.TaskService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/api")
public class TaskController {
    
    @Autowired
    private TaskService taskSvc;

    @GetMapping(path="/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> getTasks(){
        
        List<Task> results = taskSvc.findAllTasks();
        return new ResponseEntity<>(results, HttpStatus.OK);
        

    }

    @GetMapping(path="/task/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> getTaskById(@PathVariable String taskId){
        
        Task result = taskSvc.findTaskById(taskId);

        if (result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

    }


    @PostMapping(path="/task", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> addTask(@RequestBody String taskEntry){
        
        Task t = Task.toTask(taskEntry);
        Task result = taskSvc.insertTask(t.toDoc());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(path="/task/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateTask(@PathVariable String taskId, @RequestBody String updatedTask) {
        Task t = Task.toTask(updatedTask);

        taskSvc.updateTaskById(taskId, t);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path="/task/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeTaskById(@PathVariable String taskId) {
        
        taskSvc.removeTaskById(taskId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
