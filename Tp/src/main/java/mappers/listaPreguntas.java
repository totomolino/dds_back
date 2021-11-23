package mappers;


import dominioBD.PreguntaOrg;
import java.util.List;

public class listaPreguntas  {

    List<PreguntaOrg> preguntas;

    public listaPreguntas(List<PreguntaOrg> preguntas) {
        this.preguntas = preguntas;
    }

    public List<PreguntaOrg> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaOrg> preguntas) {
        this.preguntas = preguntas;
    }
}
