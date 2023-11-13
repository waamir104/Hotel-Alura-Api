package dev.waamir.hotelaluraapi.application.service.Room;

import dev.waamir.hotelaluraapi.domain.model.Room;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface IRoomService<T extends Room> {
    T create(T room);
    Optional<T> getById(Long id);
    Optional<T> getByNumber(Long number);
    void delete(T room);
    void update(T room);
    List<T> list();
}
