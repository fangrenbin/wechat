package name.frb.wechat.server.util;

import name.frb.wechat.server.bean.AccessToken;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WechatUtils {
    private final static String ACCESS_TOKEN_REQUEST_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${APP_ID}&secret=${APP_SECRET}";
    private final static String APP_ID = "wxd30e31bfd8c207ee";
    private final static String APP_SECRET = "d0f9c44269c0282a1a7337efea1950a1";

    //TODO 处理解析错误的情况，比如access过期，返回的其它字符串
    /**
     * get access token
     *
     * @return {@link name.frb.wechat.server.bean.AccessToken}
     */
    public static AccessToken getAccessToken() {
        String accessTokenRequstUrl = ACCESS_TOKEN_REQUEST_URL.replace("${APP_ID}", APP_ID).replace("${APP_SECRET}", APP_SECRET);
        String jsonContent = getJson(accessTokenRequstUrl);

        return parseAccessToken(jsonContent);
    }

    /**
     * 解析access_token的JOSON内容
     *
     * @param jsonContent
     * @return
     */
    private static AccessToken parseAccessToken(String jsonContent) {
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
    public static String getJson(String requstUrl) {
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
