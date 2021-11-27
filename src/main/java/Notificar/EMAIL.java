package Notificar;

import Business.Mascota;

public class EMAIL extends notificarStrategy{

    @Override
    public void notificarMascotaEncontrada(String email, String telefono, Mascota mascota) {
        twilio.mandarEmail(email, "Encontramos a tu mascota "+ mascota.getApodo());
    }

    @Override
    public void notificarAdopcion(String email, String telefono, String mensaje) {
        twilio.mandarEmail(email, mensaje);
    }

    @Override
    public void notificarEncuentro(String email, String telefono, String mensaje) {
        twilio.mandarEmail(email,mensaje);
    }


}
