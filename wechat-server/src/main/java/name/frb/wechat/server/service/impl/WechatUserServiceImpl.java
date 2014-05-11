package name.frb.wechat.server.service.impl;

import name.frb.wechat.persist.model.WechatUserInfo;
import name.frb.wechat.persist.repository.WechatUserRepository;
import name.frb.wechat.server.bean.AccessToken;
import name.frb.wechat.server.bean.WechatUserOpenIdList;
import name.frb.wechat.server.service.WechatUserService;
import name.frb.wechat.server.util.WechatUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* Implements {@link name.frb.wechat.server.service.WechatUserService} interface<br/>
* Wechat user will async the data of remote wechat server.
* <p/>
* Created by renbin.fang on 2014/5/9.
*/
@Service
public class WechatUserServiceImpl implements WechatUserService {
    private final static String USER_LIST_REQUEST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=${ACCESS_TOKEN}&next_openid=${NEXT_OPENID}";
    private final static String USER_INFO_REQUEST_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=${ACCESS_TOKEN}&openid=${OPEN_ID}&lang=zh_CN";

    @Resource
    private WechatUserRepository wechatUserRepository;

    public void groupWehcatUser() {
        //TODO
    }

    public void syncWechatUserGroup() {
        //TODO
    }

    @Override
    public void syncWechatUser() {
        //1st. Get access token
        AccessToken accessToken = WechatUtils.getAccessToken();

        //2nd. Get wechat user openid list
        WechatUserOpenIdList wechatUserOpenIdList = getWechatUserOpenIdList(accessToken.getAccessToken());
        List<String> openIdList = wechatUserOpenIdList.getOpenIdList();

        List<WechatUserInfo> wechatUserList = new ArrayList<WechatUserInfo>();
        for (String openId : openIdList) {
            WechatUserInfo wechatUserInfo = getWechatUserInfo(accessToken.getAccessToken(), openId);

            wechatUserList.add(wechatUserInfo);
        }

        //3rd. Save wechat user info into DB
        for (WechatUserInfo userInfo : wechatUserList) {
            wechatUserRepository.save(userInfo);
        }

        return;
    }


    /**
     * get wechat user open id list
     *
     * @return wechat user open id list {@link name.frb.wechat.server.bean.WechatUserOpenIdList}
     */
    private WechatUserOpenIdList getWechatUserOpenIdList(String accessToken) {
        String userListRequestUrl = USER_LIST_REQUEST_URL.replace("${ACCESS_TOKEN}", accessToken).replace("${NEXT_OPENID}", "");
        String jsonContent = WechatUtils.getJson(userListRequestUrl);

        return parseWechatUserOpenIdList(jsonContent);
    }

    /**
     * get wechat user information
     *
     * @return wechat user info {@link name.frb.wechat.persist.model.WechatUserInfo}
     */
    private WechatUserInfo getWechatUserInfo(String accessToken, String openId) {
        String userInfoRequestUrl = USER_INFO_REQUEST_URL.replace("${ACCESS_TOKEN}", accessToken).replace("${OPEN_ID}", openId);
        String jsonContent = WechatUtils.getJson(userInfoRequestUrl);

        return parseWechatUserInfo(jsonContent);
    }

    /**
     * 解析用户信息JOSN
     *
     * @param jsonContent
     * @return 用户信息 {@link name.frb.wechat.persist.model.WechatUserInfo}
     */
    private WechatUserInfo parseWechatUserInfo(String jsonContent) {
        JSONObject jsonObject = new JSONObject(jsonContent);

        WechatUserInfo wechatUserInfo = new WechatUserInfo();
        wechatUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
        wechatUserInfo.setOpenId(jsonObject.getString("openid"));
        wechatUserInfo.setNickname(jsonObject.getString("nickname"));
        wechatUserInfo.setSex(jsonObject.getInt("sex"));
        wechatUserInfo.setLanguage(jsonObject.getString("language"));
        wechatUserInfo.setCity(jsonObject.getString("city"));
        wechatUserInfo.setProvince(jsonObject.getString("province"));
        wechatUserInfo.setCountry(jsonObject.getString("country"));
        wechatUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
        wechatUserInfo.setSubscribeTime(jsonObject.getLong("subscribe_time"));

        return wechatUserInfo;
    }

    /**
     * 解析用户列表的OPENID
     *
     * @param jsonContent
     * @return parse wechat user open id list
     */
    private WechatUserOpenIdList parseWechatUserOpenIdList(String jsonContent) {
        JSONObject jsonObject = new JSONObject(jsonContent);

        WechatUserOpenIdList wechatUserOpenIdList = new WechatUserOpenIdList();
        wechatUserOpenIdList.setTotal(jsonObject.getLong("total"));
        wechatUserOpenIdList.setCount(jsonObject.getLong("count"));
        wechatUserOpenIdList.setNextOpenId(jsonObject.getString("next_openid"));

        JSONObject dataObject = jsonObject.getJSONObject("data");
        JSONArray openIdArray = dataObject.getJSONArray("openid");
        List<String> openIdList = new ArrayList<String>();

        for (int i = 0; i < openIdArray.length(); i++) {
            String opendId = openIdArray.getString(i);

            openIdList.add(opendId);
        }
        wechatUserOpenIdList.setOpenIdList(openIdList);

        return wechatUserOpenIdList;
    }

    public void setWechatUserRepository(WechatUserRepository wechatUserRepository) {
        this.wechatUserRepository = wechatUserRepository;
    }
}