package mappers;

import com.google.gson.JsonElement;
import dominioBD.pregPublicacionDarEnAdopcion;

import java.util.List;

public class listaPreguntasMasc {

    List<pregPublicacionDarEnAdopcion> preguntas;

    public listaPreguntasMasc(List<pregPublicacionDarEnAdopcion> preguntas) {
        this.preguntas = preguntas;
    }

    public List<pregPublicacionDarEnAdopcion> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<pregPublicacionDarEnAdopcion> preguntas) {
        this.preguntas = preguntas;
    }
}
