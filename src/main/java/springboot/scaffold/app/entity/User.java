package springboot.scaffold.app.entity;

import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class User {
    
    @TableGenerator(name = "user_gen", initialValue = 10000)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_gen")
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime registerAt;

}
