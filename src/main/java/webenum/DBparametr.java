package webenum;

public enum DBparametr {
    DRIVER("db.driver"),
    USER("db.user"),
    PASSWORD("db.password"),
    URL("db.url"),
    PULL_SIZE("db.pullsize"),
    WAITING_TIME("db.waitingtime");

    private String value;

    DBparametr(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
