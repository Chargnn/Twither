package coulombe.twither.Home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

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

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Action");

                alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE, "Modifier",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                openUpdateMessageActivity();
                            }
                        });

                alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "Supprimer",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "Message supprimé", Toast.LENGTH_SHORT).show();
                            }
                        });

                alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEGATIVE, "Annuler",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });


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
            return seconds + " sec(s)";
        else if(minutes < 60)
            return minutes + " min(s)";
        else if(hours < 24)
            return hours + " hr(s)";
        else if(days < 365)
            return days + " " + getContext().getString(R.string.Days);
        else
            return years + " " + getContext().getString(R.string.Years);
    }

    private void openProfileActivity(){
        Intent i = new Intent(getContext(), ProfileActivity.class);
        getContext().startActivity(i);
    }

    private void openUpdateMessageActivity(){
        Intent i = new Intent(getContext(), UpdateMessage.class);
        getContext().startActivity(i);
    }
}
