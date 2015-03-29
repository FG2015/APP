package fabertelecom.fabergroup.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fabertelecom.fabergroup.Models.Task;
import fabertelecom.fabergroup.R;

/**
 * Created by Aitor on 26/03/2015.
 */

public class TasksAdapter extends ArrayAdapter<Task> {

    public TasksAdapter(Context context, List<Task> objects) {
        super(context, R.id.title, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.item, null);
        }
        TextView titulo = (TextView) convertView.findViewById(R.id.title);
        TextView cliente = (TextView) convertView.findViewById(R.id.cliente);
        TextView rmapres = (TextView) convertView.findViewById(R.id.rmapres);
        TextView hinicio = (TextView) convertView.findViewById(R.id.hinicio_text_view);
        TextView hfin = (TextView) convertView.findViewById(R.id.hfin_text_view);

        Task tareas = this.getItem(position);
        titulo.setText(tareas.getName());
        cliente.setText(tareas.getClient_name());
        rmapres.setText(tareas.getRma());

        Date date_ini =  new Date(tareas.getStart_date()*1000);
        Date date_fin =  new Date(tareas.getEnd_date()*1000);

        SimpleDateFormat hour_format = new SimpleDateFormat("HH:mm");
        String hour_ini_text = hour_format.format(date_ini);
        String hour_fin_text = hour_format.format(date_fin);

        hinicio.setText(hour_ini_text);
        hfin.setText(hour_fin_text);

        return convertView;
    }

    public String getTaskId(int position) {
        Task tarea = this.getItem(position);
        return tarea.getId();
    }
}


