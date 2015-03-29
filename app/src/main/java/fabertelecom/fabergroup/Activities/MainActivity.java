package fabertelecom.fabergroup.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fabertelecom.fabergroup.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.login);
        Button registroButton = (Button) findViewById(R.id.registro);

        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(i);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Toast toast = Toast.makeText(MainActivity.this, "Función sin implementar", Toast.LENGTH_SHORT);
        switch (id){
            case R.id.action_settings:
                toast.show();
            case R.id.action_help:
                toast.show();
            case R.id.action_about:
                toast.show();
        }


        return super.onOptionsItemSelected(item);
    }
}
