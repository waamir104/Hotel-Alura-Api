package dev.waamir.hotelaluraapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static dev.waamir.hotelaluraapi.application.enumeration.RoleType.*;

import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.port.IRoleRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner{

    private final IRoleRepository<Role> roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role role1 = Role.builder()
            .name(GUEST.get())
            .permissions("some")
            .build();
        Role role2 = Role.builder()
            .name(ADMIN.get())
            .permissions("all")
            .build();
        Role role3 = Role.builder()
            .name(WORKER.get())
            .permissions("some more")
            .build();

        roleRepository.create(role1);
        roleRepository.create(role2);
        roleRepository.create(role3);
    }
    
}
