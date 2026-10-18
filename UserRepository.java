package LostFoundBackend.repository;

import LostFoundBackend.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get all users
    public List<User> getAllUsers() {

        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            User user = new User();

            user.setUserId(rs.getInt("user_id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            user.setRole(rs.getString("role"));

            return user;
        });
    }

    // Add a new user
    public User save(User user) {

        String sql = """
                INSERT INTO users (name, email, password, phone, role)
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getRole()
        );

        return user;
    }
    public boolean validateLogin(String email, String password) {
    String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";

    Integer count = jdbcTemplate.queryForObject(
        sql,
        Integer.class,
        email,
        password
    );

    return count != null && count > 0;
}
}