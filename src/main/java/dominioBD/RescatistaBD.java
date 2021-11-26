package dominioBD;

import Business.Contacto;
import Business.Duenio;
import Business.Rescatista;
import Business.Usuario;
import Notificar.notificarStrategy;
import utils.BDUtils;

import javax.persistence.*;
import java.util.List;

//@Table(name = "rescatista_bd")
@Entity
public class RescatistaBD extends PersonaBD {

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resc_id;*/


    @OneToMany(mappedBy = "resc_rescatista")
    private List<RescateBD>rescateBDS;

    @ManyToOne
    @JoinColumn(name = "orga_id")
    private OrganizacionBD resc_organizacion;


    //TODO falta agregarle el duenio


   /* public Long getResc_id() {
        return resc_id;
    }

    public void setResc_id(Long resc_id) {
        this.resc_id = resc_id;
    }*/

    public List<RescateBD> getRescateBDS() {
        return rescateBDS;
    }

    public void setRescateBDS(List<RescateBD> rescateBDS) {
        this.rescateBDS = rescateBDS;
    }

    public OrganizacionBD getResc_organizacion() {
        return resc_organizacion;
    }

    public void setResc_organizacion(OrganizacionBD resc_organizacion) {
        this.resc_organizacion = resc_organizacion;
    }

    public Rescatista transformar() {
        Long id = this.getPers_id();
        String nombre = this.getPers_nombre();
        String apellido = this.getPers_apellido();
        String telefono = this.getPers_telefono();
        String fechaNacimiento = this.getPers_fechaNacimiento();
        String tipoDocumento = this.getPers_tipoDocumento();
        int numeroDoc = this.getPers_documento();
        List<notificarStrategy> listaNotif = BDUtils.dameListaNotif(id);
        List<Contacto> listaContactos = BDUtils.dameContactos(id);
        Usuario usuario = this.getPers_usuario().transformar();
        Rescatista rescatista = new Rescatista(id,nombre,apellido,telefono,fechaNacimiento,tipoDocumento,numeroDoc,listaNotif,listaContactos,usuario);
        return rescatista;
    }
}