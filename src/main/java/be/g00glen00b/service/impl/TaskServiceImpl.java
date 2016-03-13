package be.g00glen00b.service.impl;

import be.g00glen00b.dto.TaskDTO;
import be.g00glen00b.entity.Task;
import be.g00glen00b.repository.TaskRepository;
import be.g00glen00b.service.TaskNotFoundException;
import be.g00glen00b.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository repository;

    @Override
    public List<TaskDTO> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskDTO save(TaskDTO dto) {
        final Task newEntity = new Task(dto.getDescription(), dto.isCompleted());
        final Task entity = repository.saveAndFlush(newEntity);
        return map(entity);
    }

    @Override
    @Transactional
    public TaskDTO update(Long id, TaskDTO dto) {
        final Task entity = findOneSafe(id);
        entity.setDescription(dto.getDescription());
        entity.setCompleted(dto.isCompleted());
        return map(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        final Task entity = findOneSafe(id);
        repository.delete(entity);
    }

    private Task findOneSafe(Long id) {
        final Task entity = repository.findOne(id);
        if (entity != null) {
            return entity;
        } else {
            throw new TaskNotFoundException();
        }
    }

    private TaskDTO map(Task entity) {
        return new TaskDTO(entity.getId(), entity.getDescription(), entity.isCompleted());
    }
}
