package dev.waamir.hotelaluraapi.application.service.Role;

import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface IRoleService<T extends Role> {
    T create(T role);
    Optional<T> getById(Long id);
    Optional<T> getByName(String name);
    void delete(T role);
    void update(T role);
    List<T> list();
    Optional<T> getByUser(User user);
}
