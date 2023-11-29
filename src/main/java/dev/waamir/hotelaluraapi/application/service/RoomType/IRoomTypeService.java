package dev.waamir.hotelaluraapi.application.service.RoomType;

import dev.waamir.hotelaluraapi.domain.model.RoomType;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface IRoomTypeService<T extends RoomType> {
    T create(T roomType);
    Optional<T> getById(Long id);
    Optional<T> getByName(String name);
    Long countByName(String name);
    void delete(T roomType);
    void update(T roomType);
    List<T> list();
}
