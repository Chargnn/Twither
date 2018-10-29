package coulombe.twither.Service.user;

import org.coulombe.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("user/create")
    Call<Boolean> create(@Body User user);

    @POST("user/update/{id}")
    Call<String> update(@Path("id") int id);

    @GET("user/get/{id}")
    Call<User> get(@Path("id") int id);

    @GET("user/get2/{nickname}")
    Call<User> getByNicknameOrEmail(@Path("nickname") String nickname);
}
