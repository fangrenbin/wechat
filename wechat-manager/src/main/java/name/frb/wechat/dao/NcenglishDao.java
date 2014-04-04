package name.frb.wechat.dao;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NcenglishDao {
    private JdbcTemplate jdbcTemplate;

    public String retrieveNcenglishContent(String querykey) {
        String sql = "SELECT content FROM ncenglish WHERE querykey = ?";

        List<String> contentList = jdbcTemplate.query(sql, new Object[]{querykey}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("content");
            }
        });

        if (CollectionUtils.isNotEmpty(contentList) && contentList.size() > 0) {
            return contentList.get(0);
        }

        return "";
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
