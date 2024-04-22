package model;

public class RequestData {
    private String year;
    private String section;

    public RequestData(String year, String section) {
        this.year = year;
        this.section = section;
    }

    public String getYear() {
        return year;
    }

    public String getSection() {
        return section;
    }
}