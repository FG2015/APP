package fabertelecom.fabergroup.Activities;

import android.app.ListActivity;
import android.content.Intent;
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
        getMenuInflater().inflate(R.menu.menu_tareas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_newtask) {
            Intent i = new Intent(TareasActivity.this,NuevaTareaActivity.class);
            startActivity(i);
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
