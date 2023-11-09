package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository<T extends User> {
    T create(T t);
    Optional<T> getById(Long id);
    Optional<T> getByUsername(String username);
    void delete(T t);
    void update(T t);
    List<T> list();
}
