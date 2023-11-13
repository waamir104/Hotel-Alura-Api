package dev.waamir.hotelaluraapi.application.service.User;

import dev.waamir.hotelaluraapi.domain.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface IUserService<T extends User> {
    T create(T user);
    Optional<T> getById(Long id);
    Optional<T> getByUsername(String username);
    void delete(T user);
    void update(T user);
    List<T> list();
    void enable(String type, String userIdEncoded, String url);
}
