package model;

public class RoomTimetableRequest {
    private String room;

    public RoomTimetableRequest(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}