package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository<T extends Role> {
    T create(T role);
    Optional<T> getById(Long id);
    Optional<T> getByName(String name);
    void delete(T role);
    void update(T role);
    List<T> list();
    Optional<T> getByUser(User user);
}
