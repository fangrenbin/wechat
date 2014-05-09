package name.frb.wechat.manager.service;

import junit.framework.Assert;
import name.frb.wechat.AbstractTestng;
import name.frb.wechat.persist.model.NewsMessage;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NewsMsgServiceTest extends AbstractTestng {
    @Autowired
    private NewsMsgService newsMsgService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Test
    public void addNewsTest() {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setTitle("xxxx");
        newsMessage.setDescription("hello");
        newsMessage.setPicUrl("http://frb.name/wp-content/uploads/2012/12/Untitled-Sketch_bb_thumb.png");
        newsMessage.setUrl("http://frb.name/arduino-ethernet-shield-control-led/");

        Assert.assertTrue(newsMsgService.addNewsMsg(newsMessage));
        NewsMessage savedNewsMessage = viewNewsMsg();

        newsMessage.setId(savedNewsMessage.getId());
        newsMessage.setAddTime(savedNewsMessage.getAddTime());
        newsMessage.setUpdateTime(savedNewsMessage.getUpdateTime());

        Assert.assertEquals(newsMessage.toString(), savedNewsMessage.toString());
    }

//    @Test
    public void deleteNewsTest() {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setTitle("xxxx");
        newsMessage.setDescription("hello");
        newsMessage.setPicUrl("http://frb.name/wp-content/uploads/2012/12/Untitled-Sketch_bb_thumb.png");
        newsMessage.setUrl("http://frb.name/arduino-ethernet-shield-control-led/");

        Assert.assertTrue(newsMsgService.addNewsMsg(newsMessage));

        long id = jdbcTemplate.queryForLong("SELECT id FROM news_message");
        Assert.assertTrue(newsMsgService.deleteNewsMsg(id));
    }

    public NewsMessage viewNewsMsg() {
        String sql = "SELECT * FROM news_message";

        List<NewsMessage> newsMessageList = jdbcTemplate.query(sql, new RowMapper<NewsMessage>() {
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

    @AfterClass
    @BeforeMethod
    public void destroy() {
        jdbcTemplate.execute("DELETE FROM news_message");
    }
}