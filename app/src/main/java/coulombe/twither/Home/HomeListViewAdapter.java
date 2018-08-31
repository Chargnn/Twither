package coulombe.twither.Home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import coulombe.twither.Global.TwitMessage;
import coulombe.twither.Profile.ProfileActivity;
import coulombe.twither.R;

public class HomeListViewAdapter extends ArrayAdapter<TwitMessage> {

    public HomeListViewAdapter(@NonNull Context context) {
        super(context, R.layout.home_list_view);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.home_list_view, null);

        TwitMessage twitMessageInfo = getItem(position);

        TextView author = v.findViewById(R.id.textView5);
        author.setText(twitMessageInfo.author);
        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });

        TextView message = v.findViewById(R.id.textView6);
        message.setText(twitMessageInfo.message);

        TextView date = v.findViewById(R.id.textView7);
        date.setText(formatDate(twitMessageInfo.date));

        return v;
    }

    private String formatDate(Date date){
        Date today = new Date();

        long diff = date.getTime() - today.getTime();

        long years = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) % 365;
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);

        if(seconds < 60)
            return "Il y a " + seconds + " sec(s)";
        else if(minutes < 60)
            return "Il y a " + minutes + " min(s)";
        else if(hours < 24)
            return "Il y a " + hours + " hr(s)";
        else if(days < 365)
            return "Il y a " + days + " jour(s)";
        else
            return "Il y a " + years + " an(s)";
    }

    private void openProfileActivity(){
        Intent i = new Intent(getContext(), ProfileActivity.class);
        getContext().startActivity(i);
    }
}
