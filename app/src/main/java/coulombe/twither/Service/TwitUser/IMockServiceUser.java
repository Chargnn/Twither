package coulombe.twither.Service.TwitUser;

import java.util.List;

import coulombe.twither.Global.TwitMessage;
import coulombe.twither.Global.TwitUser;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IMockServiceUser {

    @GET("/")
    Call<TwitUser> get();
}
