package dev.waamir.hotelaluraapi.adapter.repository.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.nio.charset.StandardCharsets;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.waamir.hotelaluraapi.application.model.EmailDetails;
import dev.waamir.hotelaluraapi.domain.model.AccountVerification;
import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IAccountVerificationRepository;
import dev.waamir.hotelaluraapi.domain.port.IEmailService;
import dev.waamir.hotelaluraapi.domain.port.IRoleRepository;
import dev.waamir.hotelaluraapi.domain.port.IUserRepository;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiException;
import lombok.RequiredArgsConstructor;

import static dev.waamir.hotelaluraapi.application.enumeration.VerificationType.*;
import static dev.waamir.hotelaluraapi.adapter.repository.User.UserQueries.*;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository<User>{
    
    private final NamedParameterJdbcTemplate jdbc;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository<Role> roleRepository;
    private final Encoder encoder; 
    private final Decoder decoder;
    private final IAccountVerificationRepository<AccountVerification> accountVerificationRepository;
    private final IEmailService emailService;

    @Override
    public User create(User user) {
        if (getUsernameCount(user.getUsername()) > 0) throw new ApiException("Username is already registered:");
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("password", passwordEncoder.encode(user.getPassword()))
                .addValue("createdAt", user.getCreatedAt())
                .addValue("roleId", user.getRole().getId())
                .addValue("enabled", user.isEnabled());
            jdbc.update(INSERT_USER_QUERY, parameters, holder);
            user.setId(Objects.requireNonNull(holder.getKey()).longValue());
            String verificationUrl = getVerificationUrl(encode(user.getId().toString()), ACCOUNT.getType());
            AccountVerification newAccountVerification = new AccountVerification();
            newAccountVerification.setUrl(verificationUrl);
            newAccountVerification.setUser(user);
            accountVerificationRepository.create(newAccountVerification);
            
            EmailDetails emailDetails = new EmailDetails(user.getUsername(), emailService.getConfirmationMessage(verificationUrl  ), "Confirm Account");
            emailService.sendEmail(emailDetails);

            return user;
        } catch (Exception e) {
            throw new ApiException("An error ocurred creating the user. Please try again.");
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try {
            User wantedUser = Objects.requireNonNull(jdbc.queryForObject(SELECT_USER_BY_ID_QUERY, Map.of("id", id), new BeanPropertyRowMapper<>(User.class)));
            Role wantedRole = Objects.requireNonNull(roleRepository.getByUser(wantedUser).get());
            wantedUser.setRole(wantedRole);
            return Optional.of(wantedUser);
        } catch (Exception e) {
            throw new ApiException("User not found. Please try again.");
        }
    }
    
    @Override
    public Optional<User> getByUsername(String username) {
        try {
            User wantedUser = Objects.requireNonNull(jdbc.queryForObject(SELECT_USER_BY_USERNAME_QUERY, Map.of("username", username), new BeanPropertyRowMapper<>(User.class)));
            Role wantedRole = Objects.requireNonNull(roleRepository.getByUser(wantedUser).get());
            wantedUser.setRole(wantedRole);
            return Optional.of(wantedUser);
        } catch (Exception e) {
            throw new ApiException("User not found. Please try again.");
        }
    }

    @Override
    public void delete(User user) {
        try {
            jdbc.update(DELETE_USER_QUERY, Map.of("id", user.getId()));
        } catch (Exception e) {
            throw new ApiException("An error ocurred deleting the user. Please try again.");
        }
    }

    @Override
    public void update(User user) {
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("roleId", user.getRole().getId())
                .addValue("enabled", user.isEnabled())
                .addValue("id", user.getId());
            jdbc.update(UPDATE_USER_QUERY, parameters);
        } catch (Exception e) {
            throw new ApiException("An error ocurred updating the user. Please try again.");
        }
    }

    @Override
    public List<User> list() {
        try {
            return jdbc.query(SELECT_ALL_USERS_QUERY, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            throw new ApiException("An error ocurred listing the users. Please try again.");
        }
    }

    @Override
    public void enable(String type, String userIdEncoded, String url) {
        if (!(accountVerificationRepository.getUrlCount(url) == Integer.valueOf(1))) throw new ApiException("An error ocurred validating the url. Please try again.");
        try {
            byte[] userIdEncodedArray = decoder.decode(userIdEncoded);
            String userIdString = new String(userIdEncodedArray, StandardCharsets.UTF_8);
            User user = Objects.requireNonNull(getById(Long.parseLong(userIdString)).get());
            if (type.equals(ACCOUNT.getType())) {
                user.setEnabled(true);
                update(user);
            }
        } catch (Exception e) {
            throw new ApiException("An error ocurred enabling user. Please try again.");
        }
    }
    
    private Integer getUsernameCount(String username) {
        return jdbc.queryForObject(COUNT_USER_USERNAME_QUERY, Map.of("username", username), Integer.class);
    }

    private String getVerificationUrl(String key, String type) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/" + type + "/" + key).toUriString();
    }

    private String encode(String id) {
        byte[] idBytes = id.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(idBytes);
    }
}
