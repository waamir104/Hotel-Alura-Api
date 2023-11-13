package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.domain.model.AccountVerification;
import dev.waamir.hotelaluraapi.domain.model.User;

import java.util.Optional;

public interface IAccountVerificationRepository<T extends AccountVerification> {
    T create(T accountVerification);
    Optional<T> getByUser(User user);
    Integer getUrlCount(String url);
}
