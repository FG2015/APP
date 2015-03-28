package fabertelecom.fabergroup.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import fabertelecom.fabergroup.R;

public class RegistroActivity extends ActionBarActivity {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
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
}
