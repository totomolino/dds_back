package mappers;

import Business.services.apiHogares.entities.Hogar;

import java.util.List;

public class listaHogares {

    List<Hogar> hogares;

    public listaHogares(List<Hogar> hogares) {
        this.hogares = hogares;
    }

    public List<Hogar> getHogares() {
        return hogares;
    }

    public void setHogares(List<Hogar> hogares) {
        this.hogares = hogares;
    }
}
