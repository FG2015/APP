package fabertelecom.fabergroup.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        Task tareas = this.getItem(position);
        titulo.setText(tareas.getName());
        cliente.setText(tareas.getClient_name());
        rmapres.setText(tareas.getRma());

        return convertView;
    }
}


