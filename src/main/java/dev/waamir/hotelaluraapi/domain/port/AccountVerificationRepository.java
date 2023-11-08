package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.AccountVerification;
import dev.waamir.hotelaluraapi.domain.model.User;

import java.util.Optional;

public interface AccountVerificationRepository<T extends AccountVerification> {
    T create(T t);
    Optional<T> getByUser(T t);
    void deleteById();
    void update(T t);
}
