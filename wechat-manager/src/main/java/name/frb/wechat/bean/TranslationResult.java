package name.frb.wechat.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * TranslationResult
 */
public class TranslationResult {
    /**
     * 翻译语种
     */
    private String from;
    /**
     * 翻译成的语种
     */
    private String to;
    /**
     * 翻译结果列表，支持多单词翻译，所以返回一个列表
     */
    private List<TranslationResultList> trans_result = new ArrayList<TranslationResultList>();

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setTrans_result(List<TranslationResultList> trans_result) {
        this.trans_result = trans_result;
    }

    public List<TranslationResultList> getTrans_result() {
        return trans_result;
    }

    @Override
    public String toString() {
        return "TranslationResult{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", trans_result=" + trans_result +
                '}';
    }
}