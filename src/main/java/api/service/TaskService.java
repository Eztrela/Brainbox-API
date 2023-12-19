package api.service;

import api.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAll() {
        return taskRepository.findAll().stream().toList();
    }

    public Task getById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Transactional
    public Task insert(Task task) {
        Optional<Task> register = taskRepository.findByTitle(task.getTitle());
        if (register.isPresent()) throw new RuntimeException("Task already exists");
        return taskRepository.save(task);
    }

    @Transactional
    public Task update(Task task) {
        return taskRepository.findById(task.getId()).map(
                register -> {
                    register.setTitle(task.getTitle());
                    register.setDescription(task.getDescription());
                    register.setStatus(task.getStatus());
                    register.setDatetimeDue(task.getDatetimeDue());
                    register.setPriority(task.getPriority());
                    register.setTag(task.getTag());
                    return taskRepository.save(register);
                }
        ).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void delete(Long id){
        Optional<Task> register = taskRepository.findById(id);
        if (register.isEmpty()) throw new RuntimeException("Task "+id+" not found");
        taskRepository.deleteById(id);
    }

    public Task findByTitle(String title){
        return tagRepository.findByTitle(title).orElse(null);
    }
}
