package fabertelecom.fabergroup.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonElement;

import fabertelecom.fabergroup.Clients.APIClient;
import fabertelecom.fabergroup.Models.Task;
import fabertelecom.fabergroup.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DetailsActivity extends ActionBarActivity {

    public static final String KEY_INTENT_TASK_ID = "task_id";
    private static String TAG = "DetailsActivity";
    private Task task;
    String taskId;
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
        taskId = getIntent().getStringExtra(KEY_INTENT_TASK_ID);
        findViews();
        updateTaskInfo();
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

    public void showLoading()
    {
        //TODO
    }

    public void hideLoading()
    {
        //TODO
    }


    private void updateViewContent()
    {
        //TODO - Fill the views with the task content

    }

    private void openMaps()
    {
        String url = "comgooglemaps://?center="+task.getClient_latitude()+","+task.getClient_longitude()+"&zoom=12";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    //region - Data source

    private void updateTaskInfo()
    {
        showLoading();
        APIClient.getInstance().getTask(taskId, new Callback<Task>() {
            @Override
            public void success(Task task, Response response) {
                Log.i(TAG, "The task info was downloaded");
                hideLoading();
                DetailsActivity.this.task = task;
                updateViewContent();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "The task download did fail");
                hideLoading();
                //TODO - Show error
            }
        });
    }

    private void resolveTask(String solution) {
        APIClient.getInstance().resolveTask(taskId, solution, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                //TODO
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO
            }
        });
    }
}
