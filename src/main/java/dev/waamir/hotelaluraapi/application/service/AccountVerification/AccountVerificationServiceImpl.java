package dev.waamir.hotelaluraapi.application.service.AccountVerification;

import java.util.Optional;

import dev.waamir.hotelaluraapi.domain.model.AccountVerification;
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IAccountVerificationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountVerificationServiceImpl implements IAccountVerificationService<AccountVerification>{

    private final IAccountVerificationRepository<AccountVerification> accountVerificationRepository;

    @Override
    public AccountVerification create(AccountVerification accountVerification) {
        return accountVerificationRepository.create(accountVerification);
    }

    @Override
    public Optional<AccountVerification> getByUser(User user) {
        return accountVerificationRepository.getByUser(user);
    }

    @Override
    public Integer getUrlCount(String url) {
        return accountVerificationRepository.getUrlCount(url);
    }
    
}
