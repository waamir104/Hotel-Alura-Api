package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Room;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoomRepository<T extends Room> {
    T create(T room);
    Optional<T> getById(Long id);
    Optional<T> getByNumber(Long number);
    void delete(T room);
    void update(T room);
    Page<T> list(Pageable pagination);
}
