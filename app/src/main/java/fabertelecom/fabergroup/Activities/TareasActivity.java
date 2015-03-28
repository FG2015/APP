package fabertelecom.fabergroup.Activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.List;
import fabertelecom.fabergroup.Clients.APIClient;
import fabertelecom.fabergroup.R;
import fabertelecom.fabergroup.Models.Tarea;
import fabertelecom.fabergroup.Adapters.TareasAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class TareasActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateTasks();
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

    public void showLoading()
    {
        //TODO
    }

    public void hideLoading()
    {
        //TODO
    }

    public void showAlert(String alert) {
        Toast.makeText(this, alert, Toast.LENGTH_LONG).show();
    }

    //region - Data Source

    public void updateTasks()
    {
        showLoading();
        APIClient.getInstance().getTasks(new Callback<List<Tarea>>() {
            @Override
            public void success(List<Tarea> tareas, Response response) {
                hideLoading();
                TareasAdapter adaptador = new TareasAdapter(TareasActivity.this, tareas);
                setListAdapter(adaptador);
            }

            @Override
            public void failure(RetrofitError error) {
                hideLoading();
                showAlert("Something failed!");
            }
        });
    }

}
