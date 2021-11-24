package mappers;

import dominioBD.pregPublicacionDarEnAdopcion;

import java.util.List;

public class pregPublicacionDarEnAdopcionLista {

    List<pregPublicacionDarEnAdopcion> preguntas;

    public pregPublicacionDarEnAdopcionLista(List<pregPublicacionDarEnAdopcion> preguntas) {
        this.preguntas = preguntas;
    }

    public List<pregPublicacionDarEnAdopcion> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<pregPublicacionDarEnAdopcion> preguntas) {
        this.preguntas = preguntas;
    }
}
