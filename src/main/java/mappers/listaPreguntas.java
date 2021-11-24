package mappers;


import dominioBD.PreguntaOrg;
import java.util.List;
import java.util.stream.Collectors;

public class listaPreguntas  {

    List<String> preguntas;

    public listaPreguntas(List<PreguntaOrg> preguntas) {
        this.preguntas = preguntas.stream().map(preg -> preg.getClave()).collect(Collectors.toList());
    }

    public List<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<String> preguntas) {
        this.preguntas = preguntas;
    }
}
