package Business.services.apiHogares.entities;


import java.util.List;



public class Hogar {
    public String id;
    public String nombre;
    public Ubicacion ubicacion;
    public String telefono;
    public Admisiones admisiones;
    public int capacidad;
    public int lugaresDisponibles;
    public boolean patio;
    public List <String> caracteristicas;;


    public class Ubicacion {

        String direccion;
        double lat;
        double longi;
    }

    public class Admisiones {

        public boolean gato;
        public boolean perro;
    }







}
