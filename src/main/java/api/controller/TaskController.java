package api.controller;

import api.model.Task;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskListingDTO> getAll(){
        return this.taskService.getAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Long id){
        return this.taskService.getTaskById(id);
    }

    @PostMapping
    public Task insert(@RequestBody @Valid TaskInsertDTO task){
        Task newTask = new Task(task.title,task.description,task.status,task.datetimeCreated,task.datetimeDue,task.priority,task.tag);
        return this.taskService.insert(newTask);
    }

    @PutMapping("/{id}")
    public Task update(@RequestBody TaskInsertDTO task, @PathVariable("id") Long id){
        return this.taskService.update(task, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        this.taskService.delete(id);
        return "Task "+id+" deleted with success!";
    }
}