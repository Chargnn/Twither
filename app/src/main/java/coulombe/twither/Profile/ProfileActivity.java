package coulombe.twither.Profile;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import coulombe.twither.Global.TwitMessage;
import coulombe.twither.Home.HomeListViewAdapter;
import coulombe.twither.R;

public class ProfileActivity extends AppCompatActivity {

    private List<TwitMessage> twitMessages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle(getString(R.string.ProfileOf) + " [Pseudo]");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        /////////////////////////////// TEMPORARY
        twitMessages.add(new TwitMessage("[Pseudo]", "I disagree. You missed the point of the question. Despite the fact that he uses the phrase \"Time in seconds\" in the rest of the post he makes it clear that he doesn't actually want a plain conversion, he wants remainders."));
        twitMessages.add(new TwitMessage("[Pseudo]", "This is less efficient than the accepted answer (employing a method call, which even in JVM bytecode is a few instructions)"));
        twitMessages.add(new TwitMessage("[Pseudo]", "By the way, you should take care to leap seconds in your computation: the last minute of a year may have an additional leap second so it indeed lasts 61 seconds instead of expected 60 seconds. The ISO specification even plan for possibly 61 seconds. You can find detail in java.util.Date javadoc."));
        twitMessages.add(new TwitMessage("[Pseudo]", "This CircularProgressView is a (surprisingly) circular progress bar Android View that is designed to imitate the ... If not available, Material Blue 500 (#2196F3), The color of the progress bar. ... Will reset the animation if the value changes."));


        ListView twit_list_view = findViewById(R.id.list_view_profile);
        ArrayAdapter<TwitMessage> adapter = new HomeListViewAdapter(this);
        adapter.addAll(twitMessages);
        twit_list_view.setAdapter(adapter);

        ///////////////////////////////
    }
}
