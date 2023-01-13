package springboot.scaffold.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.scaffold.app.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE (u.email = :email OR :email IS NULL) AND (u.name = :name OR :name IS NULL)")
    List<User> searchUser(@Param("email") String email, @Param("name") String name);

}
