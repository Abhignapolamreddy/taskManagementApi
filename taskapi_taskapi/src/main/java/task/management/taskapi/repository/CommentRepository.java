package task.management.taskapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.management.taskapi.entity.Comment;
import task.management.taskapi.entity.Task;
import task.management.taskapi.entity.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findByTask(Task task);

}
