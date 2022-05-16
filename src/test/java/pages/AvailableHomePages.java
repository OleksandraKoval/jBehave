package pages;

public enum AvailableHomePages {
    GOOGLE("https://www.google.com"),
    BING("https://www.bing.com");

    private String path;

    AvailableHomePages(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
