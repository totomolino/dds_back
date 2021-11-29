package mappers;

import dominioBD.PreferenciaXAdoptante;

import java.util.List;

public class preferencias {

    List<PreferenciaXAdoptante> preferencias;

    public preferencias(List<PreferenciaXAdoptante> preferencias) {
        this.preferencias = preferencias;
    }

    public List<PreferenciaXAdoptante> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<PreferenciaXAdoptante> preferencias) {
        this.preferencias = preferencias;
    }
}
