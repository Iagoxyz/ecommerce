package tech.buid.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.buid.ecommerce.controller.dto.CreateUserDto;
import tech.buid.ecommerce.service.UserService;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserContoller {

    private final UserService userService;

    public UserContoller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto) {
        var user = userService.createUser(dto);
        return ResponseEntity.created(URI.create("/users/" + user.getUserId())).build();
    }
}
