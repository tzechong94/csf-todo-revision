package com.example.server.service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.models.Task;
import com.example.server.repo.TaskRepo;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;
    
    public List<Task> findAllTasks(){
        return taskRepo.findAllTasks()
                .stream()
                .map(t -> Task.create(t))
                .toList();
    }

    public Task insertTask(Document doc) {
        Document d = taskRepo.insertTask(doc);
        Task t = new Task();
        System.out.println(t.createFromDoc(d).getDescription());
        return t.createFromDoc(d);
    }

    public void updateTaskById(String taskId, Task t) {
        taskRepo.updateTaskById(taskId, t);
    }

    public Task findTaskById(String taskId) {
        return taskRepo.findTaskById(taskId);
    }

    public void removeTaskById(String taskId) {

        taskRepo.removeTaskById(taskId);
    }
}
