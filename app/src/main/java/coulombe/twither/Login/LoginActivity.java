package coulombe.twither.Login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.coulombe.User;
import org.coulombe.UserLogin;

import coulombe.twither.Home.HomeActivity;
import coulombe.twither.R;
import coulombe.twither.Service.HttpService;
import coulombe.twither.Service.session.SessionService;
import coulombe.twither.Service.user.UserService;
import coulombe.twither.Signup.SignupActivity;
import coulombe.twither.Singleton.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle(getString(R.string.Login) + " " + getString(R.string.app_name));
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
        final Intent i = new Intent(this, HomeActivity.class);
        SessionService service = HttpService.getSession();
        final EditText username = findViewById(R.id.editText);
        final EditText password = findViewById(R.id.editText2);
        service.login(new UserLogin(username.getText().toString(), username.getText().toString(), password.getText().toString())).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(username == null || username.getText().equals("")) {
                    loginFailure();
                    return;
                }

                if(password == null || password.getText().equals("")) {
                    loginFailure();
                    return;
                }

                if(response.body().booleanValue()){
                    UserService userService = HttpService.getUser();
                    userService.getByNicknameOrEmail(username.getText().toString()).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();
                            Session.setInstance(user);
                            startActivity(i);
                            Toast.makeText(LoginActivity.this, "Vous êtes connecté", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    loginFailure();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Can't reach server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginFailure(){
        Toast.makeText(LoginActivity.this, "Mauvais compte (temporaire)", Toast.LENGTH_SHORT).show();
    }
}
