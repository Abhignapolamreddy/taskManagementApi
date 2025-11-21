package task.management.taskapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import task.management.taskapi.constant.Status;
import task.management.taskapi.dto.TaskDto;
import task.management.taskapi.entity.Task;

public interface TaskService {
	
	  Object createTask(TaskDto dto);

	    Object updateTask(Long id, TaskDto dto);

	    Object getTaskById(Long id);
	    
	    Page<Task> getAllTasks(Pageable pageable);

	    // Get tasks by status with pagination
	    Page<Task> getTasksByStatus(Status status, Pageable pageable);

	    //List<Task> getAllTasks();

	    void deleteTask(Long id);

}
