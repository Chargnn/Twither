package coulombe.twither.Service.TwitMessage;

import java.util.List;

import coulombe.twither.Global.TwitMessage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IMockServiceMessage {

    @GET("/")
    Call<List<TwitMessage>> get();

    @POST("send")
    Call<Boolean> send(@Body TwitMessage message);
}
