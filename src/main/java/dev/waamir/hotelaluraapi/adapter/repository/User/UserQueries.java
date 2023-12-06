package dev.waamir.hotelaluraapi.adapter.repository.User;

public class UserQueries {

    public static final String INSERT_USER_QUERY = """
            INSERT INTO users (email, password, created_at, role_id, enabled) VALUES
            (:username, :password, :createdAt, :roleId, :enabled);
            """;
    public static final String SELECT_USER_BY_ID_QUERY = """
            SELECT email as username, password, user_id as id, created_at as createdAt, enabled FROM users 
            WHERE user_id = :id;
            """;
    public static final String SELECT_USER_BY_USERNAME_QUERY = """
            SELECT email as username, password, user_id as id, created_at as createdAt, enabled FROM users 
            WHERE email = :username;
            """;
    public static final String DELETE_USER_QUERY = """
            DELETE FROM users
            WHERE user_id = :id;
            """;
    public static final String UPDATE_USER_QUERY = """
            UPDATE users SET
            role_id = :roleId,
            enabled = :enabled,
            email = :email,
            password = :password
            WHERE user_id = :id;
            """;
    public static final String SELECT_ALL_USERS_QUERY = """
            SELECT email as username, password, user_id as id, created_at as createdAt, enabled FROM users ;
            """;

    public static final String COUNT_USER_USERNAME_QUERY = """
            SELECT COUNT(*) FROM users
            WHERE email = :username;
            """;
}
