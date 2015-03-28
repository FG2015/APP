package fabertelecom.fabergroup.Clients;

import com.google.gson.JsonElement;
import java.util.List;
import fabertelecom.fabergroup.Models.Tarea;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface FaberAPIInterface {
    @GET("/api/tasks")
    void getTasks(Callback<List<Tarea>> cb);

    @POST("/auth/sign_in")
    void loginUser(@Query("email") String email, @Query("password") String password, Callback<JsonElement> cb);

    @POST("/auth/sign_up")
    void signUpUser(@Query("email") String email, @Query("password") String password, @Query("name") String name, Callback<JsonElement> cb);

    // CREATE TASK
    // GET TASK
}
