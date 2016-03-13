package be.g00glen00b.service;

import be.g00glen00b.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> findAll();
    TaskDTO save(TaskDTO dto);
    TaskDTO update(Long id, TaskDTO dto);
    void delete(Long id);
}
