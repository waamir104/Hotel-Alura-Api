package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Guest;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGuestRepository<T extends Guest> {
    T create(T guest);
    Optional<T> getById(Long id);
    Optional<T> getByEmail(String email);
    Optional<T> getByIdNumber(Long idNumber);
    long countByEmail(String email);
    void delete(T guest);
    void update(T guest);
    Page<T> list(Pageable pagination);
}
