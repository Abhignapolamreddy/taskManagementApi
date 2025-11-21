package task.management.taskapi.service;

import java.util.List;

import task.management.taskapi.dto.TaskDto;
import task.management.taskapi.dto.UserDto;
import task.management.taskapi.entity.Task;
import task.management.taskapi.entity.User;
public interface UserService {

	Object createUser(UserDto dto);

	List<User> getAllUsers();
    
    List<Task> getTasksForUser(Long userId);
    
   
}
