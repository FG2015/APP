package fabertelecom.fabergroup.Clients;

import com.google.gson.JsonElement;
import java.util.List;
import fabertelecom.fabergroup.Models.Task;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface FaberAPIInterface {
    @GET("/api/tasks")
    @Headers("Content-Type: application/json")
    void getTasks(Callback<List<Task>> cb);

    @POST("/auth/sign_in")
    @Headers("Content-Type: application/json")
    void loginUser(@Query("email") String email, @Query("password") String password, Callback<JsonElement> cb);

    @POST("/auth/sign_up")
    @Headers("Content-Type: application/json")
    void signUpUser(@Query("email") String email, @Query("password") String password, @Query("name") String name, Callback<JsonElement> cb);

    // CREATE TASK
    // GET TASK
}
