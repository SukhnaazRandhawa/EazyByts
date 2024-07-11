package com.example.EasyBytes.service;

import com.example.EasyBytes.model.Task;
import com.example.EasyBytes.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    public Task createTask(Task task) {
        Task savedTask = taskRepository.save(task);
        // Send email notification
        emailService.sendEmail(
                task.getAssignedUserEmail(),
                "New Task Assigned",
                "You have been assigned a new task: " + task.getName()
        );
        return savedTask;
    }

    public Task updateTask(Task task) {
        Task updatedTask = taskRepository.save(task);
        // Send email notification if the deadline is updated
        if (task.isDeadlineUpdated()) {
            emailService.sendEmail(
                    task.getAssignedUserEmail(),
                    "Task Deadline Updated",
                    "The deadline for task: " + task.getName() + " has been updated to: " + task.getDeadline()
            );
        }
        return updatedTask;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}