package dev.waamir.hotelaluraapi.infrastructure.rest.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.waamir.hotelaluraapi.adapter.dto.resource.Role.RoleResponse;
import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.port.IRoleRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleResource {
    
    @Autowired
    private IRoleRepository<Role> roleRepository;

    @GetMapping("/list")
    public ResponseEntity<List<RoleResponse>> list() {
        // TODO implement the logic
        return null;
    }
}
