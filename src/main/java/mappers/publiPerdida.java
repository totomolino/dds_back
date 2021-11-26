package mappers;

import Business.Rescatista;
import dominioBD.RescatistaBD;


public class publiPerdida {

     String resc_descripcion;
     String estado;
     float resc_lugarEncuentroX;
     float resc_lugarEncuentroY;
     Rescatista resc_rescatista;

    public publiPerdida(String resc_descripcion, String estado, float resc_lugarEncuentroX, float resc_lugarEncuentroY, RescatistaBD resc_rescatista) {
        this.resc_descripcion = resc_descripcion;
        this.estado = estado;
        this.resc_lugarEncuentroX = resc_lugarEncuentroX;
        this.resc_lugarEncuentroY = resc_lugarEncuentroY;
        this.resc_rescatista = resc_rescatista.transformar();
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
