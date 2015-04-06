package fabertelecom.fabergroup.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fabertelecom.fabergroup.Clients.APIClient;
import fabertelecom.fabergroup.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends ActionBarActivity {
    Button accederButton;
    Button cancelarButton;
    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();

        accederButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(email!=null&&!email.equals("")){
                    if(password!=null&&!password.equals("")){
                        loginUserWithEmail(email,password);
                    }else{
                        Toast toast = Toast.makeText(LoginActivity.this, "Campo contraseña vacío", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(LoginActivity.this, "Campo usuario vacío", Toast.LENGTH_SHORT);
                    toast.show();
                }
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

    private void findViews() {
        accederButton = (Button) findViewById(R.id.acceder);
        cancelarButton = (Button) findViewById(R.id.cancelar);
        emailEditText = (EditText) findViewById(R.id.ET_usuario);
        passwordEditText = (EditText) findViewById(R.id.ET_password);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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
                Intent i = new Intent(LoginActivity.this,TasksActivity.class);
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError error) {
                switch(error.getResponse().getStatus()){
                    case 404:
                        Toast toast404 = Toast.makeText(LoginActivity.this, "Usuario no existe", Toast.LENGTH_SHORT);
                        toast404.show();
                        break;

                    case 401:
                        Toast toast401 = Toast.makeText(LoginActivity.this, "Password no válida", Toast.LENGTH_SHORT);
                        toast401.show();
                        break;

                    case 400:
                        Toast toast400 = Toast.makeText(LoginActivity.this, "Error general. Inténtalo de nuevo", Toast.LENGTH_SHORT);
                        toast400.show();
                        break;
                }
            }
        });
    }
}
