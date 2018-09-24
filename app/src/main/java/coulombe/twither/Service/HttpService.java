package coulombe.twither.Service;

import java.util.concurrent.TimeUnit;

import coulombe.twither.Service.TwitMessage.IMockServiceMessage;
import coulombe.twither.Service.TwitMessage.MockServiceMessage;
import coulombe.twither.Service.TwitUser.IMockServiceUser;
import coulombe.twither.Service.TwitUser.MockServiceUser;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class HttpService {

    public static IMockServiceMessage getMockMessage(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.google.ca/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        NetworkBehavior networkBehavior = NetworkBehavior.create();
        networkBehavior.setDelay(1000, TimeUnit.MILLISECONDS);
        networkBehavior.setVariancePercent(90);

        MockRetrofit mock = new MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build();

        BehaviorDelegate<IMockServiceMessage> delegate = mock.create(IMockServiceMessage.class);


        return new MockServiceMessage(delegate);
    }

    public static IMockServiceUser getMockUser(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.google.ca/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        NetworkBehavior networkBehavior = NetworkBehavior.create();
        networkBehavior.setDelay(1000, TimeUnit.MILLISECONDS);
        networkBehavior.setVariancePercent(90);

        MockRetrofit mock = new MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build();

        BehaviorDelegate<IMockServiceUser> delegate = mock.create(IMockServiceUser.class);


        return new MockServiceUser(delegate);
    }
}
