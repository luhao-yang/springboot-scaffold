package springboot.scaffold.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.scaffold.app.entity.User;
import springboot.scaffold.app.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(Map<String, String> params) {
        List<User> users = null;
        if(params.isEmpty()) {
            users = userRepository.findAll();
        } else {
            users = userRepository.searchUser(params.get("email"), params.get("name"));
        }
        return users;
    }

    public User getUserById(int id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }


}
