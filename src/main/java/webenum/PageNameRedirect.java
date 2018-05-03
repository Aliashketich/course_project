package webenum;

public enum PageNameRedirect {
    INDEX("/front/jsp/index.jsp"),ERROR("/front/jsp/error.jsp");

    private String path;

    PageNameRedirect(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}