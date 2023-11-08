package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository<T extends Role> {
    T create(T t);
    Optional<T> getById(int id);
    void deleteById(int id);
    void update(T t);
    List<T> list();
}
