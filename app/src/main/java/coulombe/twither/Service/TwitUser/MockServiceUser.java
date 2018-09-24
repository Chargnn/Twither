package coulombe.twither.Service.TwitUser;

import java.util.ArrayList;
import java.util.List;

import coulombe.twither.Global.TwitMessage;
import coulombe.twither.Global.TwitUser;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockServiceUser implements IMockServiceUser {

    private BehaviorDelegate<IMockServiceUser> delegate;

    public MockServiceUser(BehaviorDelegate<IMockServiceUser> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Call<TwitUser> get() {
        TwitUser user = new TwitUser();
        user.nickname = "Bobinette";
        user.password = "123";
        user.email = "wow@mail.com";
        user.bio = "ma bio";

        return delegate.returningResponse(user).get();
    }

}
