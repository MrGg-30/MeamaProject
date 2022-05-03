package com.example.MeamaProject.roles.boundary;

import com.example.MeamaProject.roles.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/roles")
public class RoleResource {

    private final RoleService roleService;

    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity createUser(HttpServletRequest request, @RequestBody RoleDTO role) {
        Long userId = roleService.addRole(role);
        try {
            return ResponseEntity.created(new URI(request.getRequestURL().append("/").append(userId.toString()).toString())).build();
        } catch (URISyntaxException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
