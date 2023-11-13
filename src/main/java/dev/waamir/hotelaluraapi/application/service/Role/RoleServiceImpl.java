package dev.waamir.hotelaluraapi.application.service.Role;

import java.util.List;
import java.util.Optional;

import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IRoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService<Role> {

    private final IRoleRepository<Role> roleRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.create(role);
    }

    @Override
    public Optional<Role> getById(Long id) {
        return roleRepository.getById(id);
    }

    @Override
    public Optional<Role> getByName(String name) {
        return roleRepository.getByName(name);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void update(Role role) {
        roleRepository.update(role);
    }

    @Override
    public List<Role> list() {
        return roleRepository.list();
    }

    @Override
    public Optional<Role> getByUser(User user) {
        return roleRepository.getByUser(user);
    }
    
}
