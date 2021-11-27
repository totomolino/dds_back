package Notificar;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


public class twilio {


    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static final String telefono = "+17622145234";
    public static final String telefonoWarap = "+14155238886";

    public static void main(String[] args) {
        twilio.mandarWhatsapp("+5401166070996", "MASCOTA ENCONTRADA");
        twilio.mandarSMS("+541166070996", "MASCOTA ENCONTRADA");
    }


    public static void mandarSMS(String telefonoTo, String mensaje){

        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(telefonoTo),
                new com.twilio.type.PhoneNumber(telefono),
                mensaje
                ).create();

       // System.out.println(message.getSid());
    }

    public static void mandarEmail(String email, String mensaje){

    }

    public static void mandarWhatsapp(String telefono, String mensaje){

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:"+telefono),
                new com.twilio.type.PhoneNumber("whatsapp:" + telefonoWarap),
                mensaje)
                .create();

        //System.out.println(message.getSid());
    }
}








