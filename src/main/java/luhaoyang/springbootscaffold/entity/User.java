package luhaoyang.springbootscaffold.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;


import lombok.Data;

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

    public User() {
    }

}
