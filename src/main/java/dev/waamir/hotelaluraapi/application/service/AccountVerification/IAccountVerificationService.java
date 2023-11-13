package dev.waamir.hotelaluraapi.application.service.AccountVerification;

import dev.waamir.hotelaluraapi.domain.model.AccountVerification;
import dev.waamir.hotelaluraapi.domain.model.User;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface IAccountVerificationService<T extends AccountVerification> {
    T create(T accountVerification);
    Optional<T> getByUser(User user);
    Integer getUrlCount(String url);
}
