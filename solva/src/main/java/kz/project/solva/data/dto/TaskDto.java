package kz.project.solva.data.dto;

import kz.project.solva.data.enums.TaskStatus;

public record TaskDto(
    Integer id,
    String title,
    String description,
    String created,
    TaskStatus taskStatus,
    String completedDate) {}
