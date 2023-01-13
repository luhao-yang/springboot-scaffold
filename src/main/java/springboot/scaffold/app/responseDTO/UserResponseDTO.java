package springboot.scaffold.responseDTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponseDTO {

    private String id;
    private String name;
    private String email;
    private LocalDateTime registerAt;

}
