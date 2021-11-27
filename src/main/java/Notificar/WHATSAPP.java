package Notificar;

import Business.Mascota;

public class WHATSAPP extends notificarStrategy{

    @Override
    public void notificarMascotaEncontrada(String email, String telefono, Mascota mascota) {

        twilio.mandarWhatsapp(telefono, "Encontramos a tu mascota "+ mascota.getApodo());


    }

    @Override
    public void notificarAdopcion(String email, String telefono, String mensaje) {
        twilio.mandarWhatsapp(telefono, mensaje);
    }

    @Override
    public void notificarEncuentro(String email, String telefono, String mensaje) {
        twilio.mandarWhatsapp(telefono,mensaje);
    }
}


