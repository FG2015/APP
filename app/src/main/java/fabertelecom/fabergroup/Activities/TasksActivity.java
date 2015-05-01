package fabertelecom.fabergroup.Activities;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import fabertelecom.fabergroup.Adapters.TasksAdapter;
import fabertelecom.fabergroup.Clients.APIClient;
import fabertelecom.fabergroup.Models.Task;
import fabertelecom.fabergroup.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class TasksActivity extends ListActivity {

    ProgressDialog pDialog;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerForContextMenu(getListView());

        pDialog = new ProgressDialog(TasksActivity.this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setTitle("Cargando tareas");
        pDialog.setMessage("Espere un momento, por favor");
        pDialog.setCancelable(false);
        pDialog.setMax(100);

        new BajarTareasTask().execute();

    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(TasksActivity.this, "Función no permitida", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(TasksActivity.this,DetailsActivity.class);
        TasksAdapter adapter = (TasksAdapter) getListAdapter();
        String taskId = adapter.getTaskId(position);
        i.putExtra(DetailsActivity.KEY_INTENT_TASK_ID, taskId);
        startActivity(i);
    }

    @Override public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tareas, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.detalles:
                onListItemClick(getListView(), getListView(), ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position, item.getItemId());
                return true;
            case R.id.mapa:
                TasksAdapter adapter = (TasksAdapter) getListAdapter();
                String taskId = adapter.getTaskId(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
                abrirMapa(taskId);
                return true;
            case R.id.llamarcliente:
                TasksAdapter adapter2 = (TasksAdapter) getListAdapter();
                String taskId2 = adapter2.getTaskId(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
                llamarCliente(taskId2);
                return true;
            case R.id.delete:
                Toast toast_borrar = Toast.makeText(TasksActivity.this, "Función sin implementar todavía", Toast.LENGTH_SHORT);
                toast_borrar.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.action_newtask:
                Intent i = new Intent(TasksActivity.this,NewTaskActivity.class);
                startActivity(i);
                break;

            case R.id.action_logout:
                logoutUser();
                break;

            case R.id.action_actualizar:
                Intent actualizar = getIntent();
                finish();
                startActivity(actualizar);
                break;

            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Task> updateTasks() {
        List<Task> task = null;
        APIClient.getInstance().getTasks(new Callback<List<Task>>() {
            @Override
            public void success(List<Task> tasks, Response response) {
                TasksAdapter adaptador = new TasksAdapter(TasksActivity.this, tasks);
                setListAdapter(adaptador);
            }

            @Override
            public void failure(RetrofitError error) {
                switch(error.getResponse().getStatus()){

                    case 401:
                        Toast toast401 = Toast.makeText(TasksActivity.this, "Acceso NO autorizado", Toast.LENGTH_SHORT);
                        toast401.show();
                        break;

                    default:
                        Toast toast400 = Toast.makeText(TasksActivity.this, "Error general. Inténtalo de nuevo", Toast.LENGTH_SHORT);
                        toast400.show();
                        break;
                }
            }
        });
        return task;
    }

    public void abrirMapa(String taskId)
    {
        APIClient.getInstance().getTask(taskId, new Callback<Task>() {
            @Override
            public void success(Task task, Response response) {
                TasksActivity.this.task = task;
                String url = "http://maps.google.com/maps?q=" + task.getClient_latitude() + "," + task.getClient_longitude() + "(" + task.getClient_name() + ")";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO - Show error
            }
        });
    }

    public void llamarCliente(String taskId)
    {
        APIClient.getInstance().getTask(taskId, new Callback<Task>() {
            @Override
            public void success(Task task, Response response) {
                TasksActivity.this.task = task;
                String tel = task.getClient_phone();
                Intent i = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+tel));
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO - Show error
            }
        });
    }

    private class BajarTareasTask extends AsyncTask<String, Integer, List<Task>> {
        @Override
        protected List<Task> doInBackground(String... urls) {
            pDialog.setProgress(100);
            pDialog.show();
            return updateTasks();
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            int progreso = values[0].intValue();
            pDialog.setProgress(progreso);
        }

        @Override
        protected void onPreExecute(){
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    BajarTareasTask.this.cancel(true);
                }
            });
            pDialog.setProgress(0);
            pDialog.show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(TasksActivity.this, "Tarea cancelada", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(List<Task> result){
            pDialog.dismiss();
            Toast.makeText(TasksActivity.this, "Tareas cargadas correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    public void logoutUser() {
                APIClient.clearAuth(TasksActivity.this);
                Intent i = new Intent(TasksActivity.this,LoginActivity.class);
                startActivity(i);
            }
}
