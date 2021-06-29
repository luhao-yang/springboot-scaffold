package luhaoyang.springbootscaffold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import luhaoyang.springbootscaffold.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByEmail(String email);

}
