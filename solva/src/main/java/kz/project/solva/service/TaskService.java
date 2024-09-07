package kz.project.solva.service;

import kz.project.solva.data.dto.TaskDto;
import kz.project.solva.data.enums.TaskStatus;
import kz.project.solva.rest.request.TaskRequest;
import org.springframework.data.domain.Page;

public interface TaskService {
  TaskDto create(TaskRequest model);

  Page<TaskDto> getTasksByStatus(TaskStatus taskStatus, int page, int size);

  void delete(int id);

  TaskDto update(int id, String title, String description, TaskStatus taskStatus);
}
