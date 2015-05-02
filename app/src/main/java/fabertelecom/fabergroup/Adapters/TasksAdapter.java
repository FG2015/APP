package fabertelecom.fabergroup.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        TextView dia = (TextView) convertView.findViewById(R.id.dia_text_view);
        TextView numero = (TextView) convertView.findViewById(R.id.numero_text_view);
        TextView mes = (TextView) convertView.findViewById(R.id.mes_text_view);
        TextView hinicio = (TextView) convertView.findViewById(R.id.hinicio_text_view);
        TextView hfin = (TextView) convertView.findViewById(R.id.hfin_text_view);

        Task tareas = this.getItem(position);
        titulo.setText(tareas.getName());
        cliente.setText(tareas.getClient_name());
        rmapres.setText(tareas.getRma());

        DateFormatSymbols simbolos_dias = new DateFormatSymbols(new Locale("es","ES"));
        DateFormatSymbols simbolos_meses = new DateFormatSymbols(new Locale("es","ES"));
        String[] diasModificados = {"", "Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        simbolos_dias.setShortWeekdays(diasModificados);
        String[] mesesModificados = {"", "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
        simbolos_meses.setShortWeekdays(mesesModificados);

        Date date_ini =  new Date(tareas.getStart_date()*1000);
        Date date_fin =  new Date(tareas.getEnd_date()*1000);

        SimpleDateFormat dia_format = new SimpleDateFormat("E", simbolos_dias);
        String dia_text = dia_format.format(date_ini);

        SimpleDateFormat numero_format = new SimpleDateFormat("d");
        String numero_text = numero_format.format(date_ini);

        SimpleDateFormat mes_format = new SimpleDateFormat("MMM", simbolos_meses);
        String mes_text = mes_format.format(date_ini);

        SimpleDateFormat hour_format = new SimpleDateFormat("HH:mm");
        String hour_ini_text = hour_format.format(date_ini);
        String hour_fin_text = hour_format.format(date_fin);

        dia.setText(dia_text);
        numero.setText(numero_text);
        mes.setText(mes_text);
        hinicio.setText(hour_ini_text);
        hfin.setText(hour_fin_text);

        if(tareas.getStatus().equals("idle")){
            convertView.setBackgroundResource(R.color.color_idle);
        }
        else if (tareas.getStatus().equals("working")) {
            convertView.setBackgroundResource(R.color.color_working);
        }
        else {
            convertView.setBackgroundResource(R.color.color_finish);
        }

        return convertView;
    }

    public String getTaskId(int position) {
        Task tarea = this.getItem(position);
        return tarea.getId();
    }
}


