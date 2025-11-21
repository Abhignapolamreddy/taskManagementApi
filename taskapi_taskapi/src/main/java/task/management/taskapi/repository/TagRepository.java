package task.management.taskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.management.taskapi.entity.Tag;
import task.management.taskapi.entity.User;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
