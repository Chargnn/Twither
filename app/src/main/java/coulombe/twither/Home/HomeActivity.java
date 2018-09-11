package coulombe.twither.Home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import coulombe.twither.Global.TwitMessage;
import coulombe.twither.Home.TwitMessage.SeeMessageActivity;
import coulombe.twither.Profile.ProfileActivity;
import coulombe.twither.R;

public class HomeActivity extends AppCompatActivity {

    ActionBarDrawerToggle toggleDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle(R.string.Home);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        FloatingActionButton sendMessage = findViewById(R.id.floatingActionButton);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSendMessageActivity();
            }
        });

        /////////////////////////////// TEMPORARY
        List<TwitMessage> twitMessages = new ArrayList<>();
        twitMessages.add(new TwitMessage("quelqu'un", "I disagree. You missed the point of the question. Despite the fact that he uses the phrase \"Time in seconds\" in the rest of the post he makes it clear that he doesn't actually want a plain conversion, he wants remainders."));
        twitMessages.add(new TwitMessage("un pseudo", "This is less efficient than the accepted answer (employing a method call, which even in JVM bytecode is a few instructions)"));
        twitMessages.add(new TwitMessage("Google", "By the way, you should take care to leap seconds in your computation: the last minute of a year may have an additional leap second so it indeed lasts 61 seconds instead of expected 60 seconds. The ISO specification even plan for possibly 61 seconds. You can find detail in java.util.Date javadoc."));
        twitMessages.add(new TwitMessage("Jo la patate", "This CircularProgressView is a (surprisingly) circular progress bar Android View that is designed to imitate the ... If not available, Material Blue 500 (#2196F3), The color of the progress bar. ... Will reset the animation if the value changes."));


        ListView twit_list_view = findViewById(R.id.home_list_view);
        ArrayAdapter<TwitMessage> adapter = new HomeListViewAdapter(this);
        adapter.addAll(twitMessages);
        twit_list_view.setAdapter(adapter);
        ///////////////////////////////

        twit_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openSeeMessageActivity();
            }
        });

        final NavigationView drawer = findViewById(R.id.drawer);
        final DrawerLayout drawer_layout = findViewById(R.id.drawer_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggleDrawer = new ActionBarDrawerToggle(this, drawer_layout, R.string.OpenDrawer, R.string.CloseDrawer);
        drawer_layout.addDrawerListener(toggleDrawer);
        toggleDrawer.syncState();

        drawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                int id = menuItem.getItemId();

                switch(id){
                    case R.id.navigation_item_1:{
                        ListView lv = findViewById(R.id.home_list_view);
                        lv.smoothScrollToPosition(0);
                        break;
                    }
                    case R.id.navigation_item_2:{
                        openProfileActivity();
                        break;
                    }
                    case R.id.navigation_sub_item_1:{
                        break;
                    }
                    case R.id.navigation_sub_item_2:{
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
                        break;
                    }
                }

                drawer_layout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggleDrawer.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggleDrawer.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        toggleDrawer.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    private void openSendMessageActivity(){
        Intent i = new Intent(this, SendMessageActivity.class);
        startActivity(i);
    }

    private void openProfileActivity(){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    private void openSeeMessageActivity(){
        Intent i = new Intent(this, SeeMessageActivity.class);
        startActivity(i);
    }
}
