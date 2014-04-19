package name.frb.wechat.manager.dao;

import name.frb.wechat.manager.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.CollectionUtils;

import java.sql.*;
import java.util.List;

public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public long addUser(final User user) {
        final String sql = "INSERT INTO user(username, password, email) VALUES(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int numberOfRowsAffected = jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());

                return ps;
            }
        }, keyHolder);

        if (numberOfRowsAffected == 1) {
            return keyHolder.getKey().longValue();
        } else {
            return -1;
        }
    }

    public boolean deleteUser(long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        int temp = jdbcTemplate.update(sql, new Object[]{id});

        return temp == 1;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE user SET username = ?, password = ?, email = ? WHERE id = ?";
        int temp = jdbcTemplate.update(sql, new Object[]{user.getUsername(), user.getPassword(), user.getEmail(), user.getId()});

        return temp == 1;
    }

    public User viewUser(long id) {
        String sql = "SELECT id, username, password, email FROM user WHERE id = ?";
        List<User> userList = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));

                return user;
            }
        });

        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public List<User> retrieveUserList() {
        String sql = "SELECT id, username, password, email FROM user";
        return jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));

                return user;
            }
        });
    }


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
