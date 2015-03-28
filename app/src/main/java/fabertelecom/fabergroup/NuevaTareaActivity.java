package fabertelecom.fabergroup;

import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class NuevaTareaActivity extends ActionBarActivity {

    EditText titulo_EditText;
    EditText cliente_EditText;
    EditText rmapres_EditText;
    EditText hinicio_EditText;
    EditText hfin_EditText;
    EditText problema_EditText;
    EditText solucion_EditText;
    EditText direccion_EditText;
    RadioButton rma_RadioButton;
    RadioButton pres_RadioButton;
    Button cancelar_Button;
    Button aceptar_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);
        findViews();

    }

    private void findViews() {
        titulo_EditText = (EditText) findViewById(R.id.titulo_edit_text);
        cliente_EditText = (EditText) findViewById(R.id.cliente_edit_text);
        rmapres_EditText = (EditText) findViewById(R.id.rmapres_edit_text);
        hinicio_EditText = (EditText) findViewById(R.id.hinicio_edit_text);
        hfin_EditText = (EditText) findViewById(R.id.hfin_edit_text);
        problema_EditText = (EditText) findViewById(R.id.problema_edit_text);
        solucion_EditText = (EditText) findViewById(R.id.solucion_edit_text);
        direccion_EditText = (EditText) findViewById(R.id.direccion_edit_text);
        rma_RadioButton = (RadioButton) findViewById(R.id.rma_radio_button);
        pres_RadioButton = (RadioButton) findViewById(R.id.pres_radio_button);
        cancelar_Button = (Button) findViewById(R.id.cancelar_button);
        aceptar_Button = (Button) findViewById(R.id.aceptar_button);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nueva_tarea, menu);
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
