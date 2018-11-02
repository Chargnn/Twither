package coulombe.twither.Home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.coulombe.Message;
import org.coulombe.User;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import coulombe.twither.Global.ErrorParser;
import coulombe.twither.Profile.ProfileActivity;
import coulombe.twither.R;
import coulombe.twither.Service.HttpService;
import coulombe.twither.Service.message.MessageService;
import coulombe.twither.Service.user.UserService;
import coulombe.twither.Singleton.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeListViewAdapter extends ArrayAdapter<Message> {

    public HomeListViewAdapter(@NonNull Context context) {
        super(context, R.layout.home_list_view);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View v = inflater.inflate(R.layout.home_list_view, null);

        final Message twitMessageInfo = getItem(position);

        final UserService userService = HttpService.getUser();
        final TextView author = v.findViewById(R.id.textView5);
        final MessageService service = HttpService.getMessage();
        service.get(twitMessageInfo.author_id, twitMessageInfo.id).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

                userService.get(twitMessageInfo.author_id).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        author.setText(response.body().nickname);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        TextView author = v.findViewById(R.id.textView5);
                        author.setText("erreur");
                    }
                });

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity(twitMessageInfo);
            }
        });

        TextView message = v.findViewById(R.id.textView6);
        message.setText(twitMessageInfo.message);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (twitMessageInfo.author_id == Session.getInstance().id) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Action");

                    alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE, "Modifier",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    openUpdateMessageActivity(twitMessageInfo);
                                }
                            });

                    alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "Supprimer",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    service.remove(twitMessageInfo.author_id, twitMessageInfo.id).enqueue(new Callback<Boolean>() {
                                        @Override
                                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                            if(response.body() == null) {
                                                try {
                                                    Toast.makeText(getContext(), ErrorParser.parse(response.errorBody().string()), Toast.LENGTH_SHORT).show();
                                                    return;
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            if (!response.body())
                                                Toast.makeText(getContext(), "Impossible de retirer ce message", Toast.LENGTH_SHORT).show();

                                            remove(twitMessageInfo);
                                            Toast.makeText(getContext(), "Le message à été supprimé", Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onFailure(Call<Boolean> call, Throwable t) {
                                            Toast.makeText(getContext(), "Can't reach server", Toast.LENGTH_SHORT).show();
                                        }
                                    });
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
            }
        });


        TextView date = v.findViewById(R.id.textView7);
        date.setText(formatDate(twitMessageInfo.date));

        return v;
    }

    private String formatDate(Date date){
        Date today = new Date();

        long diff = Math.abs(date.getTime() - today.getTime());

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

    private void openProfileActivity(Message message){
        Intent i = new Intent(getContext(), ProfileActivity.class);
        i.putExtra("id", message.author_id + "");
        getContext().startActivity(i);
    }

    private void openUpdateMessageActivity(Message message){
        Intent i = new Intent(getContext(), UpdateMessage.class);
        i.putExtra("id", message.author_id + "");
        i.putExtra("messageid", message.id + "");
        getContext().startActivity(i);
    }
}
