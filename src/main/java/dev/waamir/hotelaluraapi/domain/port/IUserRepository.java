package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository<T extends User> {
    T create(T user);
    Optional<T> getById(Long id);
    Optional<T> getByUsername(String username);
    void delete(T user);
    void update(T user);
    List<T> list();
    void enable(String type, String userIdEncoded, String url);
}
