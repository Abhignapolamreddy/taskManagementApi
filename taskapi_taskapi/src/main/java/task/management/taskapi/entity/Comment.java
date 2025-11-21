package task.management.taskapi.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import task.management.taskapi.entity.Task.Status;

@Entity
@Table(name = "COMMENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(length = 1000)
	    private String text;
	    private LocalDateTime createdAt;

	    @ManyToOne
	    @JoinColumn(name = "task_id")
	    @JsonIgnoreProperties({"comments","tags","metadata","user"})
	    private Task task;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    @JsonIgnoreProperties({"email", "tasks", "comments"})
	    private User user;

	    @PrePersist
	    public void prePersist() {
	        createdAt = LocalDateTime.now();
	    }

}
