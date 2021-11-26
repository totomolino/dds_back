package dominioBD;

import Business.Foto;
import Business.Mascota;
import Business.Rescate;
import Business.Rescatista;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "rescate_bd")
@Entity
public class RescateBD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resc_id;

    private String resc_descripcionEstado;
    private float resc_lugarEncuentroX;
    private float resc_lugarEncuentroY;

    @ManyToOne
    @JoinColumn(name = "pers_id")
    private RescatistaBD resc_rescatista;

    @ManyToOne
    @JoinColumn(name = "masc_id")
    private MascotaBD resc_mascota;

    @OneToMany(mappedBy = "fore_rescate")
    private List<FotoRescate>fotoRescates;

    public Long getResc_id() {
        return resc_id;
    }

    public void setResc_id(Long resc_id) {
        this.resc_id = resc_id;
    }

    public String getResc_descripcionEstado() {
        return resc_descripcionEstado;
    }

    public void setResc_descripcionEstado(String resc_descripcionEstado) {
        this.resc_descripcionEstado = resc_descripcionEstado;
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

    public RescatistaBD getResc_rescatista() {
        return resc_rescatista;
    }

    public void setResc_rescatista(RescatistaBD resc_rescatista) {
        this.resc_rescatista = resc_rescatista;
    }

    public MascotaBD getResc_mascota() {
        return resc_mascota;
    }

    public void setResc_mascota(MascotaBD resc_mascota) {
        this.resc_mascota = resc_mascota;
    }

    public List<FotoRescate> getFotoRescates() {
        return fotoRescates;
    }

    public void setFotoRescates(List<FotoRescate> fotoRescates) {
        this.fotoRescates = fotoRescates;
    }

    public Rescate transformar(){
        Long id = getResc_id();
        String descripcion = getResc_descripcionEstado();
        float lugarX = getResc_lugarEncuentroX();
        float lugarY = getResc_lugarEncuentroY();
        Rescatista rescatista = getResc_rescatista().transformar();
        Mascota mascota = getResc_mascota().transformar();
        List<Foto> fotos = getFotoRescates().stream().map(fotoRescate -> fotoRescate.transformar()).collect(Collectors.toList());
        Rescate rescate = new Rescate(Math.toIntExact(id),fotos,descripcion,lugarX,lugarY);
        return rescate;

    }
}