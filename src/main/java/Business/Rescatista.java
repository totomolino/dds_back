package Business;

import Notificar.notificarStrategy;
import Sistema.Sistema;

import java.util.ArrayList;
import java.util.List;

public class Rescatista extends Persona {

    public Rescatista(Long id, String nombre, String apellido, String telefono, String fechaNacimiento, String tipoDocumento, int numeroDocumento, List<notificarStrategy> formaNotificacion, List<Contacto> contactos, Usuario usuario) {
        super(id, nombre, apellido, telefono, fechaNacimiento, tipoDocumento, numeroDocumento, formaNotificacion, contactos);
        this.usuario = usuario;
    }

    public List<hogarDeTransito> getHogaresDeTransito() {
       return Sistema.getHogaresDeTransito();
    }

    public void notificarEncontrar() {
        for(int i = 0 ; i < formaNotificacion.size() ; i++) {
            notificarStrategy notif = formaNotificacion.get(i);
            notif.notificarEncuentro(usuario.email, telefono, "El duenio encontro a su mascota entre las publicaciones");
        }

        for (Contacto c : contactos ) {
            c.notificarEncontrar();
        }


    }
}
