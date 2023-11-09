package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.AccountVerification;
import dev.waamir.hotelaluraapi.domain.model.User;

import java.util.Optional;

public interface IAccountVerificationRepository<T extends AccountVerification> {
    T create(T t);
    Optional<T> getByUser(User user);
    void delete();
    void update(T t);
}
