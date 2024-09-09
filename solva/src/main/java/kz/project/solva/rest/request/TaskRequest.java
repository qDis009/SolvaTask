package kz.project.solva.rest.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import kz.project.solva.data.enums.TaskStatus;

import java.time.LocalDateTime;

public record TaskRequest(
    @Schema(description = "Название задачи",example = "ТЗ")
    String title,
    @Schema(description = "Описание задачи",example = "Создание технического задания")
    String description,
    @Valid
    @NotNull(message = "Дата завершения не может быть пустой")
    @Schema(description = "Дата завершения задачи",example = "2024-09-07T12:30:00")
    LocalDateTime completedDate,
    @Schema(description = "Статус задачи",example = "COMPLETED")
    TaskStatus taskStatus) {}
