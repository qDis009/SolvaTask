package kz.project.solva.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.project.solva.data.dto.TaskDto;
import kz.project.solva.data.enums.TaskStatus;
import kz.project.solva.rest.request.TaskRequest;
import kz.project.solva.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
@Tag(name = "Task controller",description = "Операции, связанные с управлением задачами")
public class TaskController {
  private final TaskService taskService;

  @Operation(summary = "Create a new task", description = "Endpoint для создания новой задачи")
  @PostMapping("/create")
  public ResponseEntity<TaskDto> create(@Valid @RequestBody TaskRequest model) {
    return ResponseEntity.ok(taskService.create(model));
  }

  @Operation(summary = "Get tasks by taskStatus", description = "Получить массив задач с возможностью фильтрации по статусу задачи и пагинацией")
  @GetMapping("/tasks-by-status")
  public ResponseEntity<Page<TaskDto>> getTasksByStatus(
      @RequestParam(required = false) TaskStatus taskStatus,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(taskService.getTasksByStatus(taskStatus, page, size));
  }

  @Operation(summary = "Update task", description = "Обновить любой из трех (title, description, taskStatus) параметров задачи по её id")
  @PatchMapping("/{id}/update")
  public ResponseEntity<TaskDto> update(
      @PathVariable("id") int id,
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "taskStatus", required = false) TaskStatus taskStatus) {
    return ResponseEntity.ok(taskService.update(id, title, description, taskStatus));
  }

  @Operation(summary = "Delete task", description = "Удалить задачу по id")
  @DeleteMapping("/{id}/delete")
  public ResponseEntity<Void> delete(@PathVariable("id") int id) {
    taskService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
