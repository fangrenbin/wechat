package name.frb.wechat.service;

import java.io.InputStream;

public interface WechatService {
    String replyMessage(InputStream inputStream);
}
