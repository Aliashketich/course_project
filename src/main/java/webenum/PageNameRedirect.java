package webenum;

public enum PageNameRedirect {
    INDEX("/cafe.by/index"),
    ERROR("/cafe.by/error");

    private String path;

    PageNameRedirect(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}