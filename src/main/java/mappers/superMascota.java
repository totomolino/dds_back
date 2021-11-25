package mappers;

import dominioBD.FotoAnimales;
import dominioBD.MascotaBD;
import dominioBD.pregPublicacionDarEnAdopcion;

import java.util.List;

public class superMascota {

    mascotaPerfil mascota;

    List<pregPublicacionDarEnAdopcion> preguntas;

    List<hashmapJSON> caracJSON;


    public superMascota(MascotaBD mascota, List<pregPublicacionDarEnAdopcion> preguntas, List<hashmapJSON> caracJSON) {
        this.mascota = new mascotaPerfil(mascota.transformar());
        this.preguntas = preguntas;
        this.caracJSON = caracJSON;
    }

    public mascotaPerfil getMascota() {
        return mascota;
    }

    public void setMascota(mascotaPerfil mascota) {
        this.mascota = mascota;
    }

    public List<pregPublicacionDarEnAdopcion> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<pregPublicacionDarEnAdopcion> preguntas) {
        this.preguntas = preguntas;
    }

    public List<hashmapJSON> getCaracJSON() {
        return caracJSON;
    }

    public void setCaracJSON(List<hashmapJSON> caracJSON) {
        this.caracJSON = caracJSON;
    }
}
