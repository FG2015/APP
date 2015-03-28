package fabertelecom.fabergroup.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import fabertelecom.fabergroup.Clients.APIClient;
import fabertelecom.fabergroup.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button accederButton = (Button) findViewById(R.id.acceder);
        Button cancelarButton = (Button) findViewById(R.id.cancelar);

        loginUserWithEmail("pepibumur@gmail.com", "faberapp");
        accederButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,TareasActivity.class);
                startActivity(i);
                //Toast toast = Toast.makeText(LoginActivity.this, "Función sin implementar todavía", Toast.LENGTH_SHORT);
                //toast.show();
            }
        });

        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //region - Data Source

    public void loginUserWithEmail(final String email, String password) {
        APIClient.getInstance().loginUser(email, password, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement json, Response response) {
                JsonObject data = json.getAsJsonObject();
                String token = data.get("token").getAsString();
                APIClient.saveAuth(email, token, LoginActivity.this);
                //TODO - Open list activity
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO - Show error message
            }
        });
    }
}