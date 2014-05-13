package name.frb.wechat.server.service.impl;


import name.frb.wechat.server.bean.TranslationResult;
import name.frb.wechat.server.bean.TranslationResultList;
import name.frb.wechat.server.service.TranslationService;
import org.apache.commons.collections.CollectionUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 翻译单词，调用百度翻译API
 */
public class TranslationServiceImpl implements TranslationService {
    //    private final static String ID = "2511376";
    private final static String API_KEY = "i9hQyBiewVHw9LbmF3sDQZgu";
    private final static String SECRET_KEY = "cGcYVqqk1TNsr6rVu5p0IVWWiCeEZZpE";
    private final static String REQUEST_URL = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=${API_KEY}&q=${WORDS}&from=${FROM}&to=${TO}";
    private final static String EN = "en";
    private final static String ZH = "zh";

    public static void main(String[] args) throws MalformedURLException {
        TranslationServiceImpl translationService = new TranslationServiceImpl();
        String result = translationService.translatEnToZh("shut up");
        System.out.println(result);
    }

    //TODO 加参数，就可以多语言转换了
    @Override
    public String translatEnToZh(String words) {
        String requstUrl = REQUEST_URL.replace("${API_KEY}", API_KEY).replace("${WORDS}", words).replace("${FROM}", EN).replace("${TO}", ZH);
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

            return null;
        }

        // 解析返回JSON格式的数据
        JSONObject jsonObject = new JSONObject(returnString);

        TranslationResult transResult = new TranslationResult();
        transResult.setFrom(jsonObject.getString("from"));
        transResult.setTo(jsonObject.getString("to"));

        List<TranslationResultList> transResultList = new ArrayList<TranslationResultList>();
        JSONArray resultJosnArray = jsonObject.getJSONArray("trans_result");
        for (int i = 0; i < resultJosnArray.length(); i++) {
            JSONObject jsonItem = resultJosnArray.getJSONObject(i);

            TranslationResultList resultList = new TranslationResultList();
            resultList.setSrc(jsonItem.getString("src"));
            resultList.setDst(jsonItem.getString("dst"));

            transResultList.add(resultList);
        }

        transResult.setTrans_result(transResultList);

        if (CollectionUtils.isEmpty(transResult.getTrans_result())) {
            return null;
        } else {
            return transResult.getTrans_result().get(0).getDst();
        }
    }
}
