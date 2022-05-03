package com.example.MeamaProject.users.boundary;

import com.example.MeamaProject.users.control.UserService;
import com.example.MeamaProject.users.entity.User;
import com.example.MeamaProject.users.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserResource(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity createUser(HttpServletRequest request, @Valid @RequestBody User user) {
        Long userId = userService.createUser(user);
        try {
            return ResponseEntity.created(new URI(request.getRequestURL().append("/").append(userId.toString()).toString())).build();
        } catch (URISyntaxException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
