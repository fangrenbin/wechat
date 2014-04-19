package name.frb.wechat.manager.model;

public class Ncenglish {
//    create table ncenglish (
//    id bigint not null auto_increment,
//    querykey varchar(255),
//    content varchar(1000),
//    primary key (id));
    private long id;
    private String querykey;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuerykey() {
        return querykey;
    }

    public void setQuerykey(String querykey) {
        this.querykey = querykey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
