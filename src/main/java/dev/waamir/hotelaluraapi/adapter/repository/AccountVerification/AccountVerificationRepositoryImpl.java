package dev.waamir.hotelaluraapi.adapter.repository.AccountVerification;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import static dev.waamir.hotelaluraapi.adapter.repository.AccountVerification.AccountVerificationQueries.*;

import dev.waamir.hotelaluraapi.domain.model.AccountVerification;
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IAccountVerificationRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiException;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountVerificationRepositoryImpl implements IAccountVerificationRepository<AccountVerification> {

    private final NamedParameterJdbcTemplate jdbc;
    
    @Override
    public AccountVerification create(AccountVerification accountVerification) {
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", accountVerification.getUser().getId())
                .addValue("url", accountVerification.getUrl());
            jdbc.update(INSERT_ACCOUNT_VERIFICATION_QUERY, parameters, holder);
            accountVerification.setId(Objects.requireNonNull(holder.getKey()).longValue());
            return accountVerification;
        } catch (Exception e) {
            throw new ApiException("An error ocurred creating the account verification record. Please try again \n\n" + e.getMessage());
        }
    }

    @Override
    public Optional<AccountVerification> getByUser(User user) {
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", user.getId());
            AccountVerification wantedAccountVerification = Objects.requireNonNull(jdbc.queryForObject(SELECT_ACCOUNT_VERIFICATION_BY_USER_QUERY, parameters, new BeanPropertyRowMapper<>(AccountVerification.class)));
            return Optional.of(wantedAccountVerification);
        } catch (Exception e) {
            throw new ApiException("An error ocurred creating the account verification record. Please try again \n\n" + e.getMessage());
        }
    }
    
    @Override
    public Integer getUrlCount(String url) {
        return jdbc.queryForObject(COUNT_ACCOUNT_VERIFICATIONS_BY_URL, Map.of("url", url), Integer.class);
    }
}
