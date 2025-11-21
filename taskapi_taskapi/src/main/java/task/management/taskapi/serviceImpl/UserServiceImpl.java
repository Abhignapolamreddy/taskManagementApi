package task.management.taskapi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task.management.taskapi.dto.TaskDto;
import task.management.taskapi.dto.UserDto;
import task.management.taskapi.entity.Task;
import task.management.taskapi.entity.User;
import task.management.taskapi.repository.UserRepository;
import task.management.taskapi.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired 
	private UserRepository userRepository;

	@Override
	public Object createUser(UserDto dto) {
		 User u = new User();
	        u.setName(dto.getName());
	        u.setEmail(dto.getEmail());
	        User saved = userRepository.save(u);
	        return saved;
	}

	@Override
	public List<User> getAllUsers() {
		  return userRepository.findAll();
	}

	@Override
	public List<Task> getTasksForUser(Long userId) {
		  User user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        return user.getTasks();
	}

	

	

}
