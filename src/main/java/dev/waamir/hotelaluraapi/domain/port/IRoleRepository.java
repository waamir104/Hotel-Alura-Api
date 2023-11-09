package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository<T extends Role> {
    T create(T t);
    Optional<T> getById(Long id);
    void delete(T t);
    void update(T t);
    List<T> list();
}
