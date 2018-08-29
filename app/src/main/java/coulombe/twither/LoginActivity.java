package coulombe.twither;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Identification Twither");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3e8dfb")));

        TextView signup = findViewById(R.id.textView2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });

        final Button signin = findViewById(R.id.button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });
    }

    private void openSignupActivity(){
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }

    private void signin(){
        openHomeActivity();
    }

    private void openHomeActivity(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
