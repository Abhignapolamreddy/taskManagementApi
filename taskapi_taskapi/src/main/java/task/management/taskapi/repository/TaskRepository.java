package task.management.taskapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.management.taskapi.constant.Status;
import task.management.taskapi.entity.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
	// Spring Data JPA automatically handles pagination
    Page<Task> findAll(Pageable pageable);
    
    // Optional: add pagination with a filter, e.g., by status
    Page<Task> findByStatus(Status status, Pageable pageable);

}
