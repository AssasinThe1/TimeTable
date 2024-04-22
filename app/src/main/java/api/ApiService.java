package api;

import java.util.List;

import model.RequestData;
import model.RequestLecturerData;
import model.FreeLecturersRequest;
import model.RoomTimetableRequest;
import model.EmptyClassroomsRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import okhttp3.ResponseBody;


public interface ApiService {
    @POST("/getTimetable")
    Call<ResponseBody> getTimetable(@Body RequestData data);

    @POST("/getLecturerTimetable")
    Call<ResponseBody> getLecturerTimetable(@Body RequestLecturerData data);

    @POST("freeLecturers")
    Call<List<String>> getFreeLecturers(@Body FreeLecturersRequest request);

    @POST("roomTimetable")
    Call<List<List<String>>> getRoomTimetable(@Body RoomTimetableRequest request);

    @POST("emptyClassrooms")
    Call<List<String>> getEmptyClassrooms(@Body EmptyClassroomsRequest request);
}
