package kz.project.solva.data.component;

import kz.project.solva.data.entity.Task;
import kz.project.solva.data.enums.TaskStatus;
import org.springframework.data.domain.Page;

public interface TaskComponent {
  Task create(Task task);

  Page<Task> findAllByStatus(TaskStatus taskStatus, int page, int size);

  Task findById(int id);

  void delete(Task task);

  Task update(Task task);
}
