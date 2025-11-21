package task.management.taskapi.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String text;
    private LocalDateTime createdAt;
    private SimpleUserDto user;
}



