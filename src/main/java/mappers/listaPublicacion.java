package mappers;


import Business.publicaciones.PublicacionDarEnAdopcion;


import java.util.List;

public class listaPublicacion {

    List<PublicacionDarEnAdopcion> publicaciones;

    public listaPublicacion(List<PublicacionDarEnAdopcion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<PublicacionDarEnAdopcion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<PublicacionDarEnAdopcion> publicaciones) {
        this.publicaciones = publicaciones;
    }
}
