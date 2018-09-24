package coulombe.twither.Service.TwitMessage;

import java.util.List;

import coulombe.twither.Global.TwitMessage;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IMockServiceMessage {

    @GET("/")
    Call<List<TwitMessage>> get();
}
