package fabertelecom.fabergroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aitor on 26/03/2015.
 */

public class TareasAdapter extends ArrayAdapter<Tareas> {

    public TareasAdapter(Context context, List<Tareas> objects) {
        super(context, R.id.titulo, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.item, null);
        }
        TextView titulo = (TextView) convertView.findViewById(R.id.titulo);
        TextView cliente = (TextView) convertView.findViewById(R.id.cliente);
        TextView rmapres = (TextView) convertView.findViewById(R.id.rmapres);
        Tareas tareas = this.getItem(position);
        titulo.setText(tareas.getTitulo());
        cliente.setText(tareas.getCliente());
        rmapres.setText(tareas.getRmapres());

        return convertView;
    }
}


