package webenum;

public enum PageName {
    INDEX("/front/jsp/index.jsp"),
    ERROR("/front/jsp/error.jsp"),
    WAITER_HOME("/front/jsp/waiterViews/menu.jsp");

    private String path;

    PageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
