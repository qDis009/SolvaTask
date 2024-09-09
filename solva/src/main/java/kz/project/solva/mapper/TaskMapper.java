package kz.project.solva.mapper;

import kz.project.solva.data.dto.TaskDto;
import kz.project.solva.data.entity.Task;
import kz.project.solva.rest.request.TaskRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

  Task mapTaskRequestToTask(TaskRequest model);

  TaskDto mapTaskToTaskDto(Task task);
}
