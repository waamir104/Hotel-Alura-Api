package dev.waamir.hotelaluraapi.adapter.repository.Role;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import dev.waamir.hotelaluraapi.domain.model.Role;
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IRoleRepository;
import lombok.RequiredArgsConstructor;

import static dev.waamir.hotelaluraapi.adapter.repository.Role.RoleQueries.*;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements IRoleRepository<Role>{

    private final NamedParameterJdbcTemplate jdbc;
    
    @Override
    public Role create(Role role) {
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("name", role.getName())
                    .addValue("permissions", role.getPermissions());
            jdbc.update(INSERT_ROLE_QUERY, parameters, holder);
            role.setId(Objects.requireNonNull(holder.getKey()).longValue());
            return role;
        } catch (Exception e) {
            throw new ApiException("An error occured creating the role. Please try again.");
        }
    }

    @Override
    public Optional<Role> getById(Long id) {
        try {
            SqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
            return Optional.of(jdbc.queryForObject(SELECT_ROLE_BY_ID_QUERY, parameter, new BeanPropertyRowMapper<>(Role.class)));
        } catch (Exception e) {
            throw new ApiException("Role not found. Please try again.");
        }
    }

    @Override
    public Optional<Role> getByName(String name) {
        try {
            SqlParameterSource parameter = new MapSqlParameterSource().addValue("name", name);
            return Optional.of(jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, parameter, new BeanPropertyRowMapper<>(Role.class)));
        } catch (Exception e) {
            throw new ApiException("Role not found. Please try again.");
        }
    }

    @Override
    public void delete(Role role) {
        try {
            SqlParameterSource parameter = new MapSqlParameterSource().addValue("id", role.getId());
            jdbc.update(DELETE_ROLE_QUERY, parameter);
        } catch (Exception e) {
            throw new ApiException("An error ocurred deleting the role. Please try again.");
        }
    }

    @Override
    public void update(Role role) {
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", role.getId())
                .addValue("name", role.getName())
                .addValue("permissions", role.getPermissions());
            jdbc.update(UPDATE_ROLE_QUERY, parameters);
        } catch (Exception e) {
            throw new ApiException("An error occured updating the role. Please try again.");
        }
    }

    @Override
    public List<Role> list() {
        try {
            return jdbc.query(SELECT_ALL_ROLES_QUERY, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Role.class));
        } catch (Exception e) {
            throw new ApiException("An error occured listing the roles. Please try again.");
        }
    }

    @Override
    public Optional<Role> getByUser(User user) {
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_id", user.getId());
            return Optional.of(jdbc.queryForObject(SELECT_ROLE_BY_USER_QUERY, parameters, new BeanPropertyRowMapper<>(Role.class)));
        } catch (Exception e) {
            throw new ApiException("Role not found. Please try again.");
        }
    }
}
