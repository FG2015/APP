package fabertelecom.fabergroup;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class TareasActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Tareas> medios = inicializaTareas();
        TareasAdapter adaptador = new TareasAdapter(this,medios);
        setListAdapter(adaptador);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tareas, menu);
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

    private List<Tareas> inicializaTareas(){
        List<Tareas> tareas = new ArrayList<>();

        tareas.add(new Tareas("Antena rota", "Pepe García", "RMA 6/2015"));
        tareas.add(new Tareas("Router estropeado", "Antonia Rodríguez", "Pres. 786/2015"));
        tareas.add(new Tareas("No funciona Internet", "Ruperto Martínez", "RMA 52/2015"));
        tareas.add(new Tareas("Nueva instalación", "Clotilde Hernández", "Pres. 127/2015"));

        return tareas;
    }
}
