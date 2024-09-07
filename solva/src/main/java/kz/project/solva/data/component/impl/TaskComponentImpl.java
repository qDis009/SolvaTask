package kz.project.solva.data.component.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.project.solva.data.component.TaskComponent;
import kz.project.solva.data.entity.Task;
import kz.project.solva.data.enums.TaskStatus;
import kz.project.solva.data.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskComponentImpl implements TaskComponent {
  private final TaskRepository taskRepository;

  @Override
  public Task create(Task task) {
    return taskRepository.save(task);
  }

  @Override
  public Page<Task> findAllByStatus(TaskStatus taskStatus, int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    return taskRepository.findAllByTaskStatus(taskStatus, pageRequest);
  }

  @Override
  public Task findById(int id) {
    return taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public void delete(Task task) {
    taskRepository.delete(task);
  }

  @Override
  public Task update(Task task) {
    return taskRepository.save(task);
  }
}
