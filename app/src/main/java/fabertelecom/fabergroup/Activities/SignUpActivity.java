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

public class SignUpActivity extends ActionBarActivity {

    EditText nombre_EditText;
    EditText email_EditText;
    EditText contrasenya_EditText;
    EditText repcontrasenya_EditText;
    Button aceptar_Button;
    Button cancelar_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        findViews();

        aceptar_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_EditText.getText().toString();
                String password = contrasenya_EditText.getText().toString();
                String reppassword = repcontrasenya_EditText.getText().toString();
                String nombre = nombre_EditText.getText().toString();

                if(nombre!=null&&!nombre.equals("")) {
                    if (email != null && !email.equals("")) {
                        if (password != null && !password.equals("")) {
                            if(reppassword!=null&&!reppassword.equals("")) {
                                if(password.equals(reppassword)) {
                                    if(password.length()>=8) {
                                        signUpUser(email, password, nombre);
                                        }else{
                                            Toast toast = Toast.makeText(SignUpActivity.this, "La contraseña debe tener como mínimo 8 caracteres", Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                    }else {
                                        Toast toast = Toast.makeText(SignUpActivity.this, "Contraseñas no coinciden", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }else{
                                    Toast toast = Toast.makeText(SignUpActivity.this, "Campo repite contraseña vacío", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            } else {
                                Toast toast = Toast.makeText(SignUpActivity.this, "Campo contraseña vacío", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } else {
                            Toast toast = Toast.makeText(SignUpActivity.this, "Campo E-mail vacío", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(SignUpActivity.this, "Campo nombre vacío", Toast.LENGTH_SHORT);
                        toast.show();
                        }
            }
        });

        cancelar_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void findViews() {
        nombre_EditText = (EditText) findViewById(R.id.nombre_edit_text);
        email_EditText = (EditText) findViewById(R.id.email_edit_text);
        contrasenya_EditText = (EditText) findViewById(R.id.contrasenya_edit_text);
        repcontrasenya_EditText = (EditText) findViewById(R.id.repcontrasenya_edit_text);
        aceptar_Button = (Button) findViewById(R.id.aceptar_button);
        cancelar_Button = (Button) findViewById(R.id.cancelar_button);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SignUpActivity.this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registro, menu);
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

    public void signUpUser(final String email, String password, String name) {
        APIClient.getInstance().signUpUser(email, password, name, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement json, Response response) {
                JsonObject data = json.getAsJsonObject();
                String token = data.get("token").getAsString();
                APIClient.saveAuth(email, token, SignUpActivity.this);
                Intent i = new Intent(SignUpActivity.this, TasksActivity.class);
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError error) {
                switch (error.getResponse().getStatus()) {
                    case 404:
                        Toast toast404 = Toast.makeText(SignUpActivity.this, "Usuario no existe", Toast.LENGTH_SHORT);
                        toast404.show();
                        break;
                    case 401:
                        Toast toast401 = Toast.makeText(SignUpActivity.this, "Password no válida", Toast.LENGTH_SHORT);
                        toast401.show();
                        break;
                    default:
                        Toast toastdefault = Toast.makeText(SignUpActivity.this, "Error general. Inténtalo de nuevo", Toast.LENGTH_SHORT);
                        toastdefault.show();
                        break;
                }
            }
        });
    }
}
