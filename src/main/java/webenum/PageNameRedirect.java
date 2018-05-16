package webenum;

public enum PageNameRedirect {
    INDEX("/cafe.by/index"),
    ERROR("/cafe.by/error"),
    FIND_BY_TYPE("/cafe.by/find_by_type"),
    PROFILE("/cafe.by/waiter_profile"),
    ORDERS("/cafe.by/show_order_waiter");


    private String path;

    PageNameRedirect(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}