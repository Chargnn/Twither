package coulombe.twither.Service.TwitUser;

import java.util.List;

import coulombe.twither.Global.TwitMessage;
import coulombe.twither.Global.TwitUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IMockServiceUser {

    @GET("/")
    Call<TwitUser> get();

    @POST("/create")
    Call<Boolean> create(@Body TwitUser user);

    @POST("/update")
    Call<Boolean> update(@Body TwitUser user);

    @POST("/delete")
    Call<Boolean> delete(@Body TwitUser user);
}