package api.controller;

import api.dto.TaskInsertDTO;
import api.model.Task;
import api.service.TaskService;
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
    public List<Task> getAll(){
        return this.taskService.getAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Long id){
        return this.taskService.getById(id);
    }

    @PostMapping
    public Task insert(@RequestBody @Valid TaskInsertDTO task){
        return this.taskService.insert(task);
    }

    @PutMapping("/{id}")
    public Task update(@RequestBody TaskInsertDTO task, @PathVariable("id") Long id){
        return this.taskService.update(task, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        this.taskService.delete(id);
    }
}