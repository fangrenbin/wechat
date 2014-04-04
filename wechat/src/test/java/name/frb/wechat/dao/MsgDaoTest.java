package name.frb.wechat.dao;

import name.frb.wechat.AbstractTestng;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MsgDaoTest extends AbstractTestng {
    @Autowired
    private NcenglishDao ncenglishDao;

//    @Test
    public void retrieveContentTest() {
        String querykey = "0103";
        String actual = ncenglishDao.retrieveNcenglishContent(querykey);
        Assert.assertTrue(StringUtils.isNotEmpty(actual));

        querykey = "abc";
        actual = ncenglishDao.retrieveNcenglishContent(querykey);
        Assert.assertTrue(StringUtils.isEmpty(actual));
    }
}
