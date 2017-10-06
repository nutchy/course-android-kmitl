package kmitl.lab07.chayanon58070021.lazyinstagram.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nutchy on 10/6/2017 AD.
 */

public interface LazyInstagramApi {
    // Host
    String BASE_URL = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile") // Call API via HTTP GET
//    Call<String> getProfile(@Query("user") String userName);
    Call<UserProfile> getProfile(@Query("user") String userName);
}
