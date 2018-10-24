package coulombe.twither.Service.session;

import org.coulombe.User;
import org.coulombe.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SessionService {

    @POST("session/login")
    Call<Boolean> login(@Body UserLogin login);

    @POST("session/register")
    Call<Boolean> register(@Body User user);
}
