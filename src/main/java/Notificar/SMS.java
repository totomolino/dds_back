package Notificar;

import Business.Mascota;

public class SMS extends notificarStrategy{


    @Override
    public void notificarMascotaEncontrada(String email, String telefono, Mascota mascota) {
        twilio.mandarSMS("+54"+telefono, "Encontramos a tu mascota "+ mascota.getApodo());
    }

    @Override
    public void notificarAdopcion(String email, String telefono, String mensaje) {
        twilio.mandarSMS("+54"+telefono, mensaje);
    }

    @Override
    public void notificarEncuentro(String email, String telefono, String mensaje) {
        twilio.mandarSMS("+54"+telefono,mensaje);
    }

}
