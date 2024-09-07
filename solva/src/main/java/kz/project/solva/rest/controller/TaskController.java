package kz.project.solva.rest.controller;

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
public class TaskController {
  private final TaskService taskService;

  @PostMapping("/create")
  public ResponseEntity<TaskDto> create(@Valid @RequestBody TaskRequest model) {
    return ResponseEntity.ok(taskService.create(model));
  }

  @GetMapping("/tasks-by-status")
  public ResponseEntity<Page<TaskDto>> getTasksByStatus(
      @RequestParam(required = false) TaskStatus taskStatus,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(taskService.getTasksByStatus(taskStatus, page, size));
  }

  @PatchMapping("/{id}/update")
  public ResponseEntity<TaskDto> update(
      @PathVariable("id") int id,
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "taskStatus", required = false) TaskStatus taskStatus) {
    return ResponseEntity.ok(taskService.update(id, title, description, taskStatus));
  }

  @DeleteMapping("/{id}/delete")
  public ResponseEntity<Void> delete(@PathVariable("id") int id) {
    taskService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
