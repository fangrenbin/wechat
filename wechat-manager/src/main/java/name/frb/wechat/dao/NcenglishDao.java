package name.frb.wechat.dao;

import name.frb.wechat.model.Ncenglish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NcenglishDao {
    private JdbcTemplate jdbcTemplate;

    public String retrieveNcenglishContent(String querykey) {
        String sql = " SELECT content FROM ncenglish WHERE querykey = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{querykey}, String.class);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
