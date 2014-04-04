package name.frb.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

/**
 * AbstractTestng
 * <p/>
 *
 * @author : renbin.fang
 * @date : Jan 23, 2014
 */
@ContextConfiguration(locations = { "classpath:spring/common/applicationContext-*.xml", "classpath:spring/local/applicationContext-*.xml" })
public abstract class AbstractTestng extends AbstractTestNGSpringContextTests {
    /**
     * LOGGER
     */
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * constructor without parameter for initializing log4j
     */
    public AbstractTestng() {
        try {
            Log4jConfigurer.initLogging("classpath:log/log4j.xml", 60000);
        } catch (FileNotFoundException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
