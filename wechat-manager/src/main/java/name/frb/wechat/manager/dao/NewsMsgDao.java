package name.frb.wechat.manager.dao;

import name.frb.wechat.persist.model.NewsMessage;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NewsMsgDao {
    private JdbcTemplate jdbcTemplate;

    public boolean addNewsMsg(NewsMessage newsMessage) {
        String sql = "INSERT INTO news_message(title, description, picUrl, url, addTime, updateTime) VALUES(?, ?, ?, ?, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000)";

        int temp = jdbcTemplate.update(sql, new Object[]{newsMessage.getTitle(), newsMessage.getDescription(), newsMessage.getPicUrl(), newsMessage.getUrl()});

        return temp == 1;
    }

    public boolean updateNewsMsg(NewsMessage newsMessage) {
        String sql = "UPDATE news_message SET title = ?, description = ?, picUrl = ?, url = ?, updateTime = UNIX_TIMESTAMP() * 1000 WHERE id = ?";

        int temp = jdbcTemplate.update(sql, newsMessage.getTitle(), newsMessage.getDescription(), newsMessage.getPicUrl(), newsMessage.getUrl());

        return temp == 1;
    }


    public boolean deleteNewsMsg(long id) {
        String sql = "DELETE FROM news_message WHERE id = ?";

        int temp = jdbcTemplate.update(sql, new Object[]{id});

        return temp == 1;
    }

    public NewsMessage viewNewsMsg(long id) {
        String sql = "SELECT id, title, description, picUrl, url, addTime, updateTime FROM news_message WHERE id = ?";

        List<NewsMessage> newsMessageList = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<NewsMessage>() {
            @Override
            public NewsMessage mapRow(ResultSet resultSet, int i) throws SQLException {
                NewsMessage newsMessage = new NewsMessage();

                newsMessage.setId(resultSet.getLong("id"));
                newsMessage.setTitle(resultSet.getString("title"));
                newsMessage.setDescription(resultSet.getString("description"));
                newsMessage.setPicUrl(resultSet.getString("picUrl"));
                newsMessage.setUrl(resultSet.getString("url"));
                newsMessage.setAddTime(resultSet.getLong("addTime"));
                newsMessage.setUpdateTime(resultSet.getLong("updateTime"));

                return newsMessage;
            }
        });

        if (CollectionUtils.isNotEmpty(newsMessageList) && newsMessageList.size() == 1) {
            return newsMessageList.get(0);
        }

        return null;
    }

    public List<NewsMessage> retrieveNewsMsgList() {
        String sql = "SELECT id, title, description, picUrl, url, addTime, updateTime FROM news_message";

        return jdbcTemplate.query(sql, new RowMapper<NewsMessage>() {
            @Override
            public NewsMessage mapRow(ResultSet resultSet, int i) throws SQLException {
                NewsMessage newsMessage = new NewsMessage();

                newsMessage.setId(resultSet.getLong("id"));
                newsMessage.setTitle(resultSet.getString("title"));
                newsMessage.setDescription(resultSet.getString("description"));
                newsMessage.setPicUrl(resultSet.getString("picUrl"));
                newsMessage.setUrl(resultSet.getString("url"));
                newsMessage.setAddTime(resultSet.getLong("addTime"));
                newsMessage.setUpdateTime(resultSet.getLong("updateTime"));

                return newsMessage;
            }
        });
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
