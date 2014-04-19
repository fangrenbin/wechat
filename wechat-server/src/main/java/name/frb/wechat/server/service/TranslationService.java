package name.frb.wechat.server.service;

public interface TranslationService {
    /**
     * 翻译：英文－>中文
     *
     * @param words
     * @return 中文释义
     */
    String translatEnToZh(String words);
}
