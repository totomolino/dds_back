package Notificar;

import Business.Mascota;

public class SMS extends notificarStrategy{


    @Override
    public void notificarMascotaEncontrada(String email, String telefono, Mascota mascota) {
        twilio.mandarSMS(telefono, "Encontramos a tu mascota "+ mascota.getApodo());
    }

    @Override
    public void notificarAdopcion(String email, String telefono, String mensaje) {
        twilio.mandarSMS(telefono, mensaje);
    }

    @Override
    public void notificarEncuentro(String email, String telefono, String mensaje) {
        twilio.mandarSMS(telefono,mensaje);
    }

}
