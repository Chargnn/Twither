package coulombe.twither.Service.message;

import org.coulombe.Message;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageService {

    @POST("message/create/{id}")
    Call<Boolean> create(@Path("id") int id, @Body Message message);

    @POST("message/update/{id}/{messageid}")
    Call<Boolean> update(@Path("id") int id, @Path("messageid") int messageid, @Body Message message);

    @GET("message/get/{id}/{messageid}")
    Call<String> get(@Path("id") int id, @Path("messageid") int messageid);

    @GET("message/getall/{id}")
    Call<String> getAll(@Path("id") int id);

}
