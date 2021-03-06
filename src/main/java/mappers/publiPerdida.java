package mappers;

import Business.Foto;
import Business.Rescatista;
import dominioBD.RescatistaBD;

import java.util.List;


public class publiPerdida {

     Long id;
     String resc_descripcion;
     String estado;
     float resc_lugarEncuentroX;
     float resc_lugarEncuentroY;
     Rescatista resc_rescatista;
     List<Foto> fotos;


    public publiPerdida(Long id, String resc_descripcion, String estado, float resc_lugarEncuentroX, float resc_lugarEncuentroY, RescatistaBD resc_rescatista, List<Foto> fotos) {
        this.id = id;
        this.resc_descripcion = resc_descripcion;
        this.estado = estado;
        this.resc_lugarEncuentroX = resc_lugarEncuentroX;
        this.resc_lugarEncuentroY = resc_lugarEncuentroY;
        this.resc_rescatista = resc_rescatista.transformar();
        this.fotos = fotos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResc_descripcion() {
        return resc_descripcion;
    }

    public void setResc_descripcion(String resc_descripcion) {
        this.resc_descripcion = resc_descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getResc_lugarEncuentroX() {
        return resc_lugarEncuentroX;
    }

    public void setResc_lugarEncuentroX(float resc_lugarEncuentroX) {
        this.resc_lugarEncuentroX = resc_lugarEncuentroX;
    }

    public float getResc_lugarEncuentroY() {
        return resc_lugarEncuentroY;
    }

    public void setResc_lugarEncuentroY(float resc_lugarEncuentroY) {
        this.resc_lugarEncuentroY = resc_lugarEncuentroY;
    }

    public Rescatista getResc_rescatista() {
        return resc_rescatista;
    }

    public void setResc_rescatista(Rescatista resc_rescatista) {
        this.resc_rescatista = resc_rescatista;
    }
}
