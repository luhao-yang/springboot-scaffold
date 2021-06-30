package springboot.scaffold.controller;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.common.hash.Hashing;

import springboot.scaffold.entity.User;
import springboot.scaffold.requestDTO.UserRequestDTO;
import springboot.scaffold.responseDTO.UserResponseDTO;
import springboot.scaffold.service.UserService;
import springboot.scaffold.util.MapperUtil;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userService.getUsers();
        return MapperUtil.mapList(users, UserResponseDTO.class);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@NonNull @PathVariable String id) {
        User user = userService.getUserById(Integer.parseInt(id));
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @PostMapping("/")
    public void addUser(@NonNull @RequestBody UserRequestDTO userRequestDTO) {
        //todo: input validation

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userRequestDTO, User.class);

        user.setPassword(Hashing.sha256().hashString(userRequestDTO.getPassword(), StandardCharsets.UTF_8).toString());
        user.setRegisterAt(LocalDateTime.now());
        userService.saveUser(user);

    }

    @PutMapping("/{id}")
    public void updateUser(@NonNull @PathVariable String id, @NonNull @RequestBody UserRequestDTO userRequestDTO) {

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@NonNull @PathVariable String id) {
        userService.deleteUserById(Integer.parseInt(id));
    }


}
