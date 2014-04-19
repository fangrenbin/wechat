package name.frb.wechat.server.bean;

/**
 * 百度翻译结果列表
 */
public class TranslationResultList {
    private String src;
    private String dst;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    @Override
    public String toString() {
        return "TransResult{" +
                "src='" + src + '\'' +
                ", dst='" + dst + '\'' +
                '}';
    }
}
