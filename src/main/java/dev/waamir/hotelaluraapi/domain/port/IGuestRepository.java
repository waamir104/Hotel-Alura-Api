package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Guest;

import java.util.List;
import java.util.Optional;

public interface IGuestRepository<T extends Guest> {
    T create(T guest);
    Optional<T> getById(Long id);
    Optional<T> getByEmail(String email);
    void delete(T guest);
    void update(T guest);
    List<T> list();
}
