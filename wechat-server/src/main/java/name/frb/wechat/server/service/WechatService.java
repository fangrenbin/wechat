package name.frb.wechat.server.service;

import java.io.InputStream;

public interface WechatService {
    String replyMessage(InputStream inputStream);
}
