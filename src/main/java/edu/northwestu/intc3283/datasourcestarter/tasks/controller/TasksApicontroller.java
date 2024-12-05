package edu.northwestu.intc3283.datasourcestarter.tasks.controller;

import edu.northwestu.intc3283.datasourcestarter.tasks.entity.Task;
import edu.northwestu.intc3283.datasourcestarter.tasks.repository.TasksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksApicontroller {

    private final TasksRepository tasksRepository;

    public TasksApicontroller(final TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable("id") Long id) {
        Optional<Task> taskOptional = this.tasksRepository.findById(id);

        if (taskOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
        return taskOptional.get();
    }
    @PostMapping("")
    public Task createTask(@Validated @RequestBody Task task) {
        task.setStatus("PENDING");
        return this.tasksRepository.save(task);
    }


}
