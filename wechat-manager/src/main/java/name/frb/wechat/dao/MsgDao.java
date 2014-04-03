package name.frb.wechat.dao;

import name.frb.wechat.model.wechat.TextMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MsgDao {
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @return
     */
    public List<TextMessage> retrieveTextMessage() {
        String sql = "SELECT id, toUserName, fromUserName, createTime, msgType, content, msgId FROM text_message";

        return jdbcTemplate.query(sql, new RowMapper<TextMessage>() {
            @Override
            public TextMessage mapRow(ResultSet resultSet, int i) throws SQLException {
                TextMessage textMessage = new TextMessage();

                textMessage.setId(resultSet.getLong("id"));
                textMessage.setToUserName(resultSet.getString("toUserName"));
                textMessage.setFromUserName(resultSet.getString("fromUserName"));
                textMessage.setCreateTime(resultSet.getString("createTime"));
                textMessage.setMsgType(resultSet.getString("msgType"));
                textMessage.setContent(resultSet.getString("content"));
                textMessage.setMsgId(resultSet.getString("msgId"));

                return textMessage;
            }
        });
    }

    /**
     *
     * @param textMessage
     * @return
     */
    public boolean addTextMessage(TextMessage textMessage) {
        String sql = "INSERT INTO text_message(toUserName, fromUserName, createTime, msgType, content, msgId) VALUES(?, ?, ?, ?, ?, ?)";
        int temp = jdbcTemplate.update(sql, new Object[]{textMessage.getToUserName(), textMessage.getFromUserName(), textMessage.getCreateTime(), textMessage.getMsgType(), textMessage.getContent(), textMessage.getMsgId()});
        return temp == 1;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
