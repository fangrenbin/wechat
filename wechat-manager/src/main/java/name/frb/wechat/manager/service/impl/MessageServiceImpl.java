package name.frb.wechat.manager.service.impl;

import name.frb.wechat.manager.service.MessageService;
import name.frb.wechat.manager.vo.UserMessageListVo;
import name.frb.wechat.server.dao.MessageDao;
import name.frb.wechat.server.dao.NcenglishDao;
import name.frb.wechat.server.model.TextMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    private MessageDao messageDao;
    private NcenglishDao ncenglishDao;

    @Override
    public List<UserMessageListVo> retrieveMessageList() {
        List<TextMessage> textMessageList = messageDao.retrieveTextMessage();
        List<UserMessageListVo> userMessageList = new ArrayList<UserMessageListVo>();
        for (TextMessage textMessage : textMessageList) {
            UserMessageListVo messageListVo = new UserMessageListVo();
            messageListVo.setToUserName(textMessage.getToUserName());
            messageListVo.setCreateTime(formatDate(textMessage.getCreateTime()));
            messageListVo.setFromUserName(textMessage.getFromUserName());
            messageListVo.setContent(textMessage.getContent());

            userMessageList.add(messageListVo);
        }

        return userMessageList;
    }

    /**
     * 格式化时间格式
     *
     * @param timestamp
     * @return
     */
    private String formatDate(String timestamp) {
        String fmt = "MMM dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        Date date = new Date(Long.valueOf(timestamp + "000"));
        String dateStr = sdf.format(date);

        return dateStr;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void setNcenglishDao(NcenglishDao ncenglishDao) {

        this.ncenglishDao = ncenglishDao;
    }
}
