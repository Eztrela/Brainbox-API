package api.service;

import api.dto.TaskInsertDTO;
import api.model.Tag;
import api.model.Task;
import api.repository.TagRepository;
import api.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TagRepository tagRepository;

    public List<Task> getAll() {
        return taskRepository.findAll().stream().toList();
    }

    public Task getById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getByTagId(Long tagId) {
        return getAll().stream()
                .filter(task -> task.getTag() != null && task.getTag().getId().equals(tagId))
                .collect(Collectors.toList());
    }

    @Transactional
    public Task insert(TaskInsertDTO task) {
        Optional<Task> register = taskRepository.findByTitle(task.title());
        if (register.isPresent()) throw new RuntimeException("Task already exists");
        Optional<Tag> tagRegister = tagRepository.findById(task.tag().getId());
        if (tagRegister.isEmpty()) throw new RuntimeException("Task tag does not exist");
        Task newTask = new Task(task.title(), task.description(), task.status(), task.datetimeDue(), task.priority(), tagRegister.get());
        return taskRepository.save(newTask);
    }

    @Transactional
    public Task update(TaskInsertDTO task, Long id) {
        return taskRepository.findById(id).map(
                register -> {
                    register.setTitle(task.title());
                    register.setDescription(task.description());
                    register.setStatus(task.status());
                    register.setDatetimeDue(task.datetimeDue());
                    register.setPriority(task.priority());
                    register.setTag(task.tag());
                    return taskRepository.save(register);
                }
        ).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void delete(Long id){
        Optional<Task> register = taskRepository.findById(id);
        if (register.isEmpty()) throw new RuntimeException("Task "+id+" not found");
        taskRepository.deleteById(id);
    }
}
