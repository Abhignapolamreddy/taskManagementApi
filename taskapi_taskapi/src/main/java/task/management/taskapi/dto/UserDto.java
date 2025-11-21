package task.management.taskapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
	private Long id;
	private String name;
    private String email;

   // private List<Long> taskIds;
   // private List<Long> commentIds;

}
