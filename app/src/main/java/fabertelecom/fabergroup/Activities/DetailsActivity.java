package fabertelecom.fabergroup.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonElement;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    TextView hinicioTextView;
    TextView hfinTextView;
    TextView problemaTextView;
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
        hinicioTextView = (TextView) findViewById(R.id.hinicio2_text_view);
        hfinTextView = (TextView) findViewById(R.id.hfin2_text_view);
        problemaTextView = (TextView) findViewById(R.id.problema2_text_view);
        solucionEditText = (EditText) findViewById(R.id.solucion_edit_text);
        mapaButton = (Button) findViewById(R.id.mapa_button);
        tareacompletadaCheckBox = (CheckBox) findViewById(R.id.tareacompletada_check_box);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalles, menu);
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

        tituloTextView.setText(task.getName());
        clienteTextView.setText(task.getClient_name());
        rmapresTextView.setText(task.getRma());

        Date date_ini =  new Date(task.getStart_date()*1000);
        Date date_fin =  new Date(task.getEnd_date()*1000);
        SimpleDateFormat hour_format = new SimpleDateFormat("HH:mm");
        String hour_ini_text = hour_format.format(date_ini);
        String hour_fin_text = hour_format.format(date_fin);

        hinicioTextView.setText(hour_ini_text);
        hfinTextView.setText(hour_fin_text);
        problemaTextView.setText(task.getProblem());

        mapaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMaps();
            }
        });

    }

    private void openMaps()
    {
        String url = "http://maps.google.com/maps?q="+task.getClient_latitude()+","+task.getClient_longitude()+"("+task.getClient_name()+")";
        Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
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
