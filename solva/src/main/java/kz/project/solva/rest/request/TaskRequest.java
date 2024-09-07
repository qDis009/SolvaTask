package kz.project.solva.rest.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import kz.project.solva.data.enums.TaskStatus;

import java.time.LocalDateTime;

public record TaskRequest(
    String title,
    String description,
    @Valid
    @NotNull(message = "Дата завершения не может быть пустой")
    LocalDateTime completedDate,
    TaskStatus taskStatus) {}
