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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
    TextView fechaTextView;
    TextView hinicioTextView;
    TextView hfinTextView;
    TextView problemaTextView;
    TextView solucionTextView;
    EditText solucionEditText;
    Button mapaButton;
    Button llamarclienteButton;
    Button llamaroficinaButton;
    LinearLayout verticalLayout;
    LinearLayout solucionarLayout;
    LinearLayout resueltoLayout;
    LinearLayout comenzarLayout;
    Button comenzarButton;
    Button resolverButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        taskId = getIntent().getStringExtra(KEY_INTENT_TASK_ID);
        findViews();
        updateTaskInfo();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        comenzarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask();
            }
        });

        resolverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String solution = solucionEditText.getText().toString();
               resolveTask(solution);
            }
        });
    }

    private void findViews() {
        tituloTextView = (TextView) findViewById(R.id.titulo_text_view);
        clienteTextView = (TextView) findViewById(R.id.cliente_text_view);
        rmapresTextView = (TextView) findViewById(R.id.rmapres_text_view);
        fechaTextView = (TextView) findViewById(R.id.fecha2_text_view);
        hinicioTextView = (TextView) findViewById(R.id.hinicio2_text_view);
        hfinTextView = (TextView) findViewById(R.id.hfin2_text_view);
        problemaTextView = (TextView) findViewById(R.id.problema2_text_view);
        solucionTextView = (TextView) findViewById(R.id.solucion3_edit_text);
        solucionEditText = (EditText) findViewById(R.id.solucion_edit_text);
        mapaButton = (Button) findViewById(R.id.mapa_button);
        llamarclienteButton = (Button) findViewById(R.id.llamar_cliente_button);
        llamaroficinaButton = (Button) findViewById(R.id.llamar_oficina_button);
        verticalLayout = (LinearLayout) findViewById(R.id.vertical_layout);
        solucionarLayout = (LinearLayout) findViewById(R.id.solucionar_layout);
        resueltoLayout = (LinearLayout)findViewById(R.id.resuelto_layout);
        comenzarLayout = (LinearLayout)findViewById(R.id.comenzar_layout);
        comenzarButton = (Button)findViewById(R.id.comenzar_button);
        resolverButton = (Button)findViewById(R.id.resolver_button);
        progressBar = (ProgressBar) findViewById(R.id.barraprogreso);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(DetailsActivity.this,TasksActivity.class);
        startActivity(i);
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

    public void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
        verticalLayout.setVisibility(View.INVISIBLE);
    }

    public void hideLoading(){
        progressBar.setVisibility(View.INVISIBLE);
        verticalLayout.setVisibility(View.VISIBLE);
    }


    private void updateViewContent()
    {
        tituloTextView.setText(task.getName());
        clienteTextView.setText(task.getClient_name());
        rmapresTextView.setText(task.getRma());

        Date fecha = new Date(task.getStart_date()*1000);
        Date hora_ini =  new Date(task.getStart_date()*1000);
        Date hora_fin =  new Date(task.getEnd_date()*1000);
        SimpleDateFormat fecha_format = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat hour_format = new SimpleDateFormat("HH:mm");
        String fecha_text = fecha_format.format(fecha);
        String hour_ini_text = hour_format.format(hora_ini);
        String hour_fin_text = hour_format.format(hora_fin);

        fechaTextView.setText(fecha_text);
        hinicioTextView.setText(hour_ini_text);
        hfinTextView.setText(hour_fin_text);
        problemaTextView.setText(task.getProblem());
        solucionTextView.setText(task.getSolution());


        mapaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMapa();
            }
        });

        llamarclienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLlamada();
            }
        });

        llamaroficinaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:966261111"));
                startActivity(i);
            }
        });

        comenzarLayout.setVisibility(View.INVISIBLE);
        resueltoLayout.setVisibility(View.INVISIBLE);
        solucionarLayout.setVisibility(View.INVISIBLE);

        if (task.getStatus().equals("idle")) {
           comenzarLayout.setVisibility(View.VISIBLE);
           resueltoLayout.setVisibility(View.INVISIBLE);
           solucionarLayout.setVisibility(View.INVISIBLE);
        }
        else if (task.getStatus().equals("working")) {
            comenzarLayout.setVisibility(View.INVISIBLE);
            resueltoLayout.setVisibility(View.INVISIBLE);
            solucionarLayout.setVisibility(View.VISIBLE);
        }
        else {
            comenzarLayout.setVisibility(View.INVISIBLE);
            resueltoLayout.setVisibility(View.VISIBLE);
            solucionarLayout.setVisibility(View.INVISIBLE);
        }
    }

    private void abrirMapa()
    {
        String url = "http://maps.google.com/maps?q="+task.getClient_latitude()+","+task.getClient_longitude()+"("+task.getClient_name()+")";
        Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(i);
    }

    private void abrirLlamada()
    {
        String tel = task.getClient_phone();
        Intent i = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+tel));
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

    private void startTask() {
        APIClient.getInstance().startTask(taskId, new Callback<Task>() {
            @Override
            public void success(Task task, Response response) {
                DetailsActivity.this.task = task;
                updateViewContent();
            }

            @Override
            public void failure(RetrofitError error) {
               //TODO
            }
        });
    }

    private void resolveTask(String solution) {
        APIClient.getInstance().resolveTask(taskId, solution, new Callback<Task>() {
            @Override
            public void success(Task jsonElement, Response response) {
                DetailsActivity.this.task = task;
                updateViewContent();
            }

            @Override
            public void failure(RetrofitError error) {
                //TODO
            }
        });
    }
}
