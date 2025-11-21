package task.management.taskapi.entity;



import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import task.management.taskapi.constant.Status;

import java.time.*;
import java.util.*;

@Entity
@Table(name = "TASK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    //public enum Status { TODO, IN_PROGRESS, COMPLETED }

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ---------- Relationships ---------- //

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"tasks"})
    private User user;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"task"})
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
  
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnore
    private Set<Tag> tags = new HashSet<>();

    // ---------- Auto timestamps ---------- //

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    

    
 

	
  
}

