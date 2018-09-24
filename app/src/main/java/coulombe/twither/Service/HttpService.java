package coulombe.twither.Service;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class HttpService {

    public static IMockServiceMessage getMock(){
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
}
