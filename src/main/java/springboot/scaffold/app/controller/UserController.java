package springboot.scaffold.app.controller;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springboot.scaffold.app.entity.User;
import springboot.scaffold.app.requestDTO.UserRequestDTO;
import springboot.scaffold.app.service.UserService;
import springboot.scaffold.app.util.MapperUtil;
import springboot.scaffold.responseDTO.UserResponseDTO;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserResponseDTO> getAllUsers(@RequestParam Map<String, String> params) {
        List<User> users = userService.getUsers(params);
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

        String saltedPasswd = Hashing.sha256().hashString(userRequestDTO.getPassword(), StandardCharsets.UTF_8).toString();
        log.info("salted passwd: " +saltedPasswd);
        user.setPassword(saltedPasswd);
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
