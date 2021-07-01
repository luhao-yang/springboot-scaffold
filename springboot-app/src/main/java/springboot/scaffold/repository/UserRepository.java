package springboot.scaffold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.scaffold.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByEmail(String email);

}
