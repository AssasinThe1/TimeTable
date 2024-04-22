package model;

public class EmptyClassroomsRequest {
    private String day;
    private String period;

    public EmptyClassroomsRequest(String day, String period) {
        this.day = day;
        this.period = period;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}