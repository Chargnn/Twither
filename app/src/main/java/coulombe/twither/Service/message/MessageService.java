package coulombe.twither.Service.message;

import org.coulombe.Message;

import java.util.List;

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

    @GET("message/remove/{id}/{messageid}")
    Call<Boolean> remove(@Path("id") int id, @Path("messageid") int messageid);

    @GET("message/get/{id}/{messageid}")
    Call<Message> get(@Path("id") int id, @Path("messageid") int messageid);

    @GET("message/getall/{id}")
    Call<List<Message>> getAll(@Path("id") int id);

    @GET("message/getall")
    Call<List<Message>> getAll();

}
