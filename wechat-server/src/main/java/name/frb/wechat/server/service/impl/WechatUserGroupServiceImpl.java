package name.frb.wechat.server.service.impl;

import name.frb.wechat.persist.model.WechatUserGroup;
import name.frb.wechat.persist.repository.WechatUserGroupRepository;
import name.frb.wechat.server.service.WechatUserGroupService;
import name.frb.wechat.server.util.WechatUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Implements {@link name.frb.wechat.persist.model.WechatUserGroup}
 * This class does all the group of wechat user things.
 * Including:
 * <li>create group</li>
 * <li>query all group</li>
 * <li>query user is which group belongs to</li>
 * <li>move user to the group</li>
 * Created by renbin.fang on 2014/5/13.
 */
public class WechatUserGroupServiceImpl implements WechatUserGroupService {
    @Autowired
    private WechatUserGroupRepository wechatUserGroupRepository;

    private static final String CREATE_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=${ACCESS_TOKEN}";
    private static final String CREATE_GROUP_JSON = "{\"group\":{\"name\":\"${GROUP_NAME}\"}}";

    public void createGroup(WechatUserGroup wechatUserGroup) {
        String groupName = wechatUserGroup.getName();
        String accessToken = WechatUtils.getAccessToken().getAccessToken();
        String requestUrl = CREATE_GROUP_URL.replace("${ACCESS_TOKEN}", accessToken);

        String groupJson = generateGroupJson(groupName);
        String responseContent = createGroupRequest(requestUrl, groupJson);
        System.out.println(responseContent);

        WechatUserGroup userGroup = parseUserGroup(responseContent);

        wechatUserGroupRepository.save(userGroup);

        return;
    }

    /**
     * 解析分组信息JSON
     *
     * @param jsonContent
     * @return
     */
    private WechatUserGroup parseUserGroup(String jsonContent) {
        WechatUserGroup userGroup = new WechatUserGroup();
        JSONObject jsonObject = new JSONObject(jsonContent);
        JSONObject groupObject = jsonObject.getJSONObject("group");
        Long id = groupObject.getLong("id");
        String name = groupObject.getString("name");

        userGroup.setWechatId(id);
        userGroup.setName(name);
        userGroup.setCount(0);

        return userGroup;
    }

    /**
     * 生成创建组的JSON
     *
     * @param groupName
     * @return
     */
    private String generateGroupJson(String groupName) {
        return CREATE_GROUP_JSON.replace("${GROUP_NAME}", groupName);
    }

    /**
     * 发送创建组的请求
     *
     * @param requestUrl
     * @param postContents
     * @return
     */
    private String createGroupRequest(String requestUrl, String postContents) {
        String responseContents = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(requestUrl);
        try {
            StringEntity params = new StringEntity(postContents, ContentType.create("application/json", "utf-8"));
            request.addHeader("Content-type:", "application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(params);

            HttpResponse reponse = httpClient.execute(request);
            if (reponse.getStatusLine().getStatusCode() != 200) {
                //TODO
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(reponse.getEntity().getContent()));
            String output;
            StringBuffer stringBuffer = new StringBuffer();
            while ((output = bufferedReader.readLine()) != null) {
                stringBuffer.append(output);
            }

            responseContents = stringBuffer.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return responseContents;
    }

    public void setWechatUserGroupRepository(WechatUserGroupRepository wechatUserGroupRepository) {
        this.wechatUserGroupRepository = wechatUserGroupRepository;
    }
}
