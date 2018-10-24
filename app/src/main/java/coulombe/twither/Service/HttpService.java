package coulombe.twither.Service;

import coulombe.twither.Service.message.MessageService;
import coulombe.twither.Service.session.SessionService;
import coulombe.twither.Service.user.UserService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpService {

    public static UserService getUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);
        return service;
    }

    public static SessionService getSession(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        SessionService service = retrofit.create(SessionService.class);
        return service;
    }

    public static MessageService getMessage(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        MessageService service = retrofit.create(MessageService.class);
        return service;
    }
}
