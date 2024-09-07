package kz.project.solva.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import kz.project.solva.data.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "task", schema = "solva")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;
  private String description;

  @CreationTimestamp
  @Column(updatable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  private LocalDateTime created;

  @Enumerated(EnumType.STRING)
  private TaskStatus taskStatus = TaskStatus.NOT_COMPLETED;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  private LocalDateTime completedDate;
}
