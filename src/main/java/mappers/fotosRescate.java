package mappers;

import dominioBD.FotoRescate;

import java.util.List;

public class fotosRescate {

    List<FotoRescate> fotos;

    public fotosRescate(List<FotoRescate> fotos) {
        this.fotos = fotos;
    }

    public List<FotoRescate> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoRescate> fotos) {
        this.fotos = fotos;
    }
}
