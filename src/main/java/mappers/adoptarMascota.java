package mappers;

public class adoptarMascota {

    int mascota;

    int adoptante;

    public adoptarMascota(int mascota, int adoptante) {
        this.mascota = mascota;
        this.adoptante = adoptante;
    }

    public int getMascota() {
        return mascota;
    }

    public void setMascota(int mascota) {
        this.mascota = mascota;
    }

    public int getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(int adoptante) {
        this.adoptante = adoptante;
    }
}
