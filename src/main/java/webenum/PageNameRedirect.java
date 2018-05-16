package webenum;

public enum PageNameRedirect {
    INDEX("/cafe.by/index"),
    ERROR("/cafe.by/error"),
    ORDERS("/cafe.by/show_order_waiter");


    private String path;

    PageNameRedirect(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}