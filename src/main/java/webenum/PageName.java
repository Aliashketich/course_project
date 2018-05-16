package webenum;

public enum PageName {
    INDEX("/front/jsp/index.jsp"),
    ERROR("/front/jsp/error.jsp"),
    ORDERS("/front/jsp/common/orders.jsp"),
    PROFILE("/front/jsp/waiter/profile.jsp"),
    WAITERS("/front/jsp/admin/waiterlist.jsp"),
    PRODUCTS("/front/jsp/common/products.jsp");


    private String path;

    PageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
