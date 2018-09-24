package coulombe.twither.Service;

import java.util.ArrayList;
import java.util.List;

import coulombe.twither.Global.TwitMessage;
import coulombe.twither.Global.TwitUser;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockServiceMessage implements IMockServiceMessage {

    private BehaviorDelegate<IMockServiceMessage> delegate;

    public MockServiceMessage(BehaviorDelegate<IMockServiceMessage> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Call<List<TwitMessage>> get() {
        List<TwitMessage> messages = new ArrayList<>();
        messages.add(new TwitMessage("bobinette", "un message"));
        messages.add(new TwitMessage("jacob", "D/NetworkSecurityConfig: No Network Security Config specified, using platform default"));
        messages.add(new TwitMessage("pierrot", "7960-7960/coulombe.twither W/IInputConnectionWrapper: finishComposingText on inactive InputConnection"));
        messages.add(new TwitMessage("google", " W/art: Before Android 4.1, method int android.support.v7.widget.DropDownListView.lookForSelectablePosition(int, boolean) would have incorrectly overridden the package-private method in android.widget.ListView "));


        return delegate.returningResponse(messages).get();
    }

}
