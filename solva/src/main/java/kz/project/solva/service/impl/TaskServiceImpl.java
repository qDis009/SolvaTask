package kz.project.solva.service.impl;

import kz.project.solva.data.component.TaskComponent;
import kz.project.solva.data.dto.TaskDto;
import kz.project.solva.data.entity.Task;
import kz.project.solva.data.enums.TaskStatus;
import kz.project.solva.exception.InvalidTaskCompletedDateException;
import kz.project.solva.mapper.TaskMapper;
import kz.project.solva.rest.client.HolidaysClient;
import kz.project.solva.rest.request.TaskRequest;
import kz.project.solva.rest.response.HolidaysResponse;
import kz.project.solva.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
  private final TaskComponent taskComponent;
  private final TaskMapper taskMapper;
  private final HolidaysClient holidaysClient;

  @Override
  public TaskDto create(TaskRequest model) {
    List<HolidaysResponse> holidays = holidaysClient.getHolidays(model.completedDate().getYear(), "KZ"); // Правильнее было бы получать код страны в model
    if (isHolidayOrWeekend(holidays, model.completedDate())) {
      LocalDateTime nearestWeekDay = getNearestDay(holidays, model.completedDate());
      throw new InvalidTaskCompletedDateException("Выберите другой день - ближайший доступный " + nearestWeekDay);
    }
    Task task = taskComponent.create(taskMapper.mapTaskRequestToTask(model));
    return taskMapper.mapTaskToTaskDto(task);
  }

  @Override
  public Page<TaskDto> getTasksByStatus(TaskStatus taskStatus, int page, int size) {
    return taskComponent.findAllByStatus(taskStatus, page, size).map(taskMapper::mapTaskToTaskDto);
  }

  @Override
  public void delete(int id) {
    Task task = taskComponent.findById(id);
    taskComponent.delete(task);
  }

  @Override
  public TaskDto update(int id, String title, String description, TaskStatus taskStatus) {
    Task task = taskComponent.findById(id);
    if (title != null) {
      task.setTitle(title);
    }
    if (description != null) {
      task.setDescription(description);
    }
    if (taskStatus != null) {
      task.setTaskStatus(taskStatus);
    }
    taskComponent.update(task);
    return taskMapper.mapTaskToTaskDto(task);
  }

  private boolean isHolidayOrWeekend(List<HolidaysResponse> holidays, LocalDateTime completedDate) {
    if (completedDate.getDayOfWeek() == DayOfWeek.SATURDAY
        || completedDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
      return true;
    }
    return holidays.stream().anyMatch(holiday -> holiday.date().equals(completedDate.toString()));
  }

  private LocalDateTime getNearestDay(List<HolidaysResponse> holidays, LocalDateTime completedDate) {
    while (isHolidayOrWeekend(holidays, completedDate)) {
      completedDate = completedDate.plusDays(1);
    }
    return completedDate;
  }
}
