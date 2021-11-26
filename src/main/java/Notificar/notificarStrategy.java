package Notificar;

import Business.Mascota;

public abstract class notificarStrategy {

    public abstract void notificarMascotaEncontrada(String email, String telefono, Mascota mascota);

    public abstract void notificarAdopcion(String email, String telefono, String mensaje);

}
