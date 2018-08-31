package coulombe.twither.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import coulombe.twither.R;

public class HomeActivity extends AppCompatActivity {

    private List<Twit> twits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Accueil");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        FloatingActionButton sendMessage = findViewById(R.id.floatingActionButton);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSendMessageActivity();
            }
        });

        /////////////////////////////// TEMPORARY
        twits.add(new Twit("quelqu'un", "I disagree. You missed the point of the question. Despite the fact that he uses the phrase \"Time in seconds\" in the rest of the post he makes it clear that he doesn't actually want a plain conversion, he wants remainders."));
        twits.add(new Twit("un pseudo", "This is less efficient than the accepted answer (employing a method call, which even in JVM bytecode is a few instructions)"));
        twits.add(new Twit("Google", "By the way, you should take care to leap seconds in your computation: the last minute of a year may have an additional leap second so it indeed lasts 61 seconds instead of expected 60 seconds. The ISO specification even plan for possibly 61 seconds. You can find detail in java.util.Date javadoc."));
        twits.add(new Twit("Jo la patate", "This CircularProgressView is a (surprisingly) circular progress bar Android View that is designed to imitate the ... If not available, Material Blue 500 (#2196F3), The color of the progress bar. ... Will reset the animation if the value changes."));


        ListView twit_list_view = findViewById(R.id.home_list_view);
        ArrayAdapter<Twit> adapter = new HomeListViewAdapter(this);
        adapter.addAll(twits);
        twit_list_view.setAdapter(adapter);

        ///////////////////////////////
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuItem back2Top = menu.add("Accueil");
        back2Top.setIcon(R.drawable.ic_home);
        back2Top.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        back2Top.setShowAsAction(back2Top.SHOW_AS_ACTION_ALWAYS);
        back2Top.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ListView lv = findViewById(R.id.home_list_view);
                lv.smoothScrollToPosition(0);
                return true;
            }
        });

        MenuItem profile = menu.add("Profile");
        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        MenuItem about = menu.add("À propos");
        about.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
                alertDialog.setTitle("À propos de Twither");
                alertDialog.setMessage(Html.fromHtml("<p>Fait par: <b>Alexis Coulombe</b></p>"));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            }
        });

        MenuItem quit = menu.add("Quitter");
        quit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                finishAffinity();
                return true;
            }
        });
        return true;
    }

    private void openSendMessageActivity(){
        Intent i = new Intent(this, SendMessageActivity.class);
        startActivity(i);
    }
}
