package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository<T extends Room> {
    T create(T t);
    Optional<T> getById(int id);
    Optional<T> getByNumber(int number);
    void deleteById(int id);
    void update(T t);
    List<T> list();
}
