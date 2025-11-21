package task.management.taskapi.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {
    
	private Long id;
    private String title;
    private List<TagDto> tags;
    // getters/setters
}
