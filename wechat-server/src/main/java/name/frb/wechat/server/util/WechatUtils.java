package name.frb.wechat.server.util;

import name.frb.wechat.persist.model.WechatUserInfo;
import name.frb.wechat.server.bean.AccessToken;
import name.frb.wechat.server.bean.WechatUserList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WechatUtils {
    private final static String ACCESS_TOKEN_REQUEST_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${APP_ID}&secret=${APP_SECRET}";
    private final static String APP_ID = "wxd30e31bfd8c207ee";
    private final static String APP_SECRET = "d0f9c44269c0282a1a7337efea1950a1";
    private final static String USER_LIST_REQUEST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=${ACCESS_TOKEN}&next_openid=${NEXT_OPENID}";
    private final static String USER_INFO_REQUEST_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=${ACCESS_TOKEN}&openid=${OPEN_ID}&lang=zh_CN";


    public static void main(String[] args) {
//        String accessTokenRequstUrl = ACCESS_TOKEN_REQUEST_URL.replace("${APP_ID}", APP_ID).replace("${APP_SECRET}", APP_SECRET);

        WechatUtils utils = new WechatUtils();
//        String jsonContent = utils.getJson(accessTokenRequstUrl);
//        System.out.println(jsonContent);
//        AccessToken accessToken = utils.parseAccessToken(jsonContent);
//
        String userListRequestUrl = USER_LIST_REQUEST_URL.replace("${ACCESS_TOKEN}", "rlTs_974MKxAoMNpFN9hWVfa4a2jnDSkJsecHkdgWlO8uacGQSAeJJ5-seR0S361").replace("${NEXT_OPENID}", "");
        String jsonContent = utils.getJson(userListRequestUrl);
        System.out.println(utils.parseWechatUserList(jsonContent).toString());
    }

    private WechatUserInfo parseWechatUserInfo(String jsonContent){
        WechatUserInfo wechatUserInfo = new WechatUserInfo();

        return wechatUserInfo;
    }

    /**
     * 解析用户列表的OPENID
     *
     * @param jsonContent
     * @return
     */
    private WechatUserList parseWechatUserList(String jsonContent) {
        JSONObject jsonObject = new JSONObject(jsonContent);

        WechatUserList wechatUserList = new WechatUserList();
        wechatUserList.setTotal(jsonObject.getLong("total"));
        wechatUserList.setCount(jsonObject.getLong("count"));
        wechatUserList.setNextOpenId(jsonObject.getString("next_openid"));

        JSONObject dataObject = jsonObject.getJSONObject("data");

        JSONArray openIdArray = dataObject.getJSONArray("openid");
        List<String> openIdList = new ArrayList<String>();
        for (int i = 0; i < openIdArray.length(); i++) {
            String opendId = openIdArray.getString(i);

            openIdList.add(opendId);
        }
        wechatUserList.setOpenIdList(openIdList);

        return wechatUserList;
    }

    /**
     * 解析access_token
     *
     * @param jsonContent
     * @return
     */
    private AccessToken parseAccessToken(String jsonContent) {
        JSONObject jsonObject = new JSONObject(jsonContent);

        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(jsonObject.getString("access_token"));
        accessToken.setExpiresIn(jsonObject.getLong("expires_in"));

        return accessToken;
    }

    /**
     * 获取接口中返回的JSON格式的数据
     *
     * @param requstUrl url
     * @return json数据
     */
    private String getJson(String requstUrl) {
        String returnString = null;
        // 获取接口内容
        try {
            URL url = new URL(requstUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }

            reader.close();
            connection.disconnect();

            returnString = stringBuffer.toString();


        } catch (IOException e) {
            System.err.println(e.toString());
        }

        return returnString;
    }
}
