package mappers;

import Business.Duenio;
import Business.Especie;
import Business.Foto;
import Business.Mascota;

import java.util.ArrayList;
import java.util.List;

public class mascotaPerfil {

    Long id;
    Especie especie;
    String nombre;
    String apodo;
    int edad;
    String sexo;
    String descripcion;
    boolean tieneChapita;
    List<Foto> fotos = new ArrayList<>();

    public mascotaPerfil(Mascota mascota) {
        this.id = mascota.getId();
        this.fotos = mascota.getFotos();
        this.especie = mascota.getEspecie();
        this.nombre = mascota.getNombre();
        this.apodo = mascota.getApodo();
        this.edad = mascota.getEdad();
        this.sexo = mascota.getSexo();
        this.descripcion = mascota.getDescripcion();
        this.tieneChapita = mascota.isTieneChapita();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isTieneChapita() {
        return tieneChapita;
    }

    public void setTieneChapita(boolean tieneChapita) {
        this.tieneChapita = tieneChapita;
    }
}
