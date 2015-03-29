package fabertelecom.fabergroup.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import fabertelecom.fabergroup.R;


public class DetailsActivity extends ActionBarActivity {

    TextView tituloTextView;
    TextView clienteTextView;
    TextView rmapresTextView;
    EditText hinicioEditText;
    EditText hfinEditText;
    EditText problemaEditText;
    EditText solucionEditText;
    Button mapaButton;
    CheckBox tareacompletadaCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        findViews();
    }

    private void findViews() {
        tituloTextView = (TextView) findViewById(R.id.titulo_text_view);
        clienteTextView = (TextView) findViewById(R.id.cliente_text_view);
        rmapresTextView = (TextView) findViewById(R.id.rmapres_text_view);
        hinicioEditText = (EditText) findViewById(R.id.hinicio_edit_text);
        hfinEditText = (EditText) findViewById(R.id.hfin_edit_text);
        problemaEditText = (EditText) findViewById(R.id.problema_edit_text);
        solucionEditText = (EditText) findViewById(R.id.solucion_edit_text);
        mapaButton = (Button) findViewById(R.id.mapa_button);
        tareacompletadaCheckBox = (CheckBox) findViewById(R.id.tareacompletada_check_box);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalles, menu);
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
