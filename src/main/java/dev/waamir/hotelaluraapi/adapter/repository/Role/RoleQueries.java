package dev.waamir.hotelaluraapi.adapter.repository.Role;

public class RoleQueries {

    public static final String INSERT_ROLE_QUERY = """
            INSERT INTO roles (name, permissions) VALUES
            (:name, :permissions);
            """;
    public static final String SELECT_ROLE_BY_ID_QUERY = """
            SELECT role_id AS id, name , permissions FROM roles
            WHERE role_id = :id;
            """;
    public static final String DELETE_ROLE_QUERY = """
            DELETE FROM roles 
            WHERE role_id = :id;
            """;
    public static final String UPDATE_ROLE_QUERY = """
            UPDATE roles SET
            name = :name,
            permissions = :permissions
            WHERE role_id = :id;
            """;
    public static final String SELECT_ALL_ROLES_QUERY = """
            SELECT role_id AS id, name , permissions FROM roles;
            """;

    public static final String SELECT_ROLE_BY_USER_QUERY = """
            SELECT role_id as id, name, permissions FROM roles 
            WHERE role_id IN (SELECT role_id FROM users
                                WHERE user_id = :user_id);
            """;       
    public static final String SELECT_ROLE_BY_NAME_QUERY = """
            SELECT role_id as id, name, permissions FROM roles
            WHERE name = :name;
            """; 
}
