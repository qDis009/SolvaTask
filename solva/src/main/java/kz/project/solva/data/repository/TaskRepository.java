package kz.project.solva.data.repository;

import kz.project.solva.data.entity.Task;
import kz.project.solva.data.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
	@Query("select t from Task t where (:taskStatus is null or t.taskStatus = :taskStatus)")
	Page<Task> findAllByTaskStatus(TaskStatus taskStatus, Pageable pageable);
}
