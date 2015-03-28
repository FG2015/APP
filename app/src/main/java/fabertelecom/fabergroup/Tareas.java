package fabertelecom.fabergroup;

import android.graphics.Bitmap;

/**
 * Created by Aitor on 26/03/2015.
 */

public class Tareas {

    String titulo;
    String cliente;
    String rmapres;
    String url;
    String mapa;
    Bitmap imagen;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getRmapres() {
        return rmapres;
    }

    public void setRmapres(String rmapres) {
        this.rmapres = rmapres;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Tareas(String titulo, String cliente, String rmapres) {
        this.titulo = titulo;
        this.cliente = cliente;
        this.rmapres = rmapres;
    }
}
