package webenum;

public enum PageNameRedirect {
    INDEX("/cafe.by/index"),
    ERROR("/cafe.by/error"),
    WAITER_HOME("/cafe.by/waiterHome");

    private String path;

    PageNameRedirect(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}