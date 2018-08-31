package coulombe.twither.Home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import coulombe.twither.R;

public class HomeListViewAdapter extends ArrayAdapter<Twit> {

    public HomeListViewAdapter(@NonNull Context context) {
        super(context, R.layout.home_list_view);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.home_list_view, null);

        Twit twitInfo = getItem(position);

        TextView author = v.findViewById(R.id.textView5);
        author.setText(twitInfo.author);

        TextView message = v.findViewById(R.id.textView6);
        message.setText(twitInfo.message);

        TextView date = v.findViewById(R.id.textView7);
        date.setText(formatDate(twitInfo.date));

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
}
