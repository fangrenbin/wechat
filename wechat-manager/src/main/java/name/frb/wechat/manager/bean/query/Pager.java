package name.frb.wechat.manager.bean.query;

/**
 * 分页查询
 */
public class Pager {
    /**
     * 当前页
     */
    private int pageNow = 1;

    /**
     * 总页数
     */
    private int pageCount;

    /**
     * 总记录数
     */
    private int count;

    /**
     * 每页记录数
     */
    private int pageSize = 10;

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
