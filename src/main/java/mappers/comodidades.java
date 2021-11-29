package mappers;

import dominioBD.ComodidadesXadoptante;

import java.util.List;

public class comodidades {

    List<ComodidadesXadoptante> comodidades;

    public comodidades(List<ComodidadesXadoptante> comodidades) {
        this.comodidades = comodidades;
    }

    public List<ComodidadesXadoptante> getComodidades() {
        return comodidades;
    }

    public void setComodidades(List<ComodidadesXadoptante> comodidades) {
        this.comodidades = comodidades;
    }
}
