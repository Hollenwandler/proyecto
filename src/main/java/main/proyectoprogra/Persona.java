package main.proyectoprogra;

import java.io.Serializable;

public class Persona implements Serializable {
     String nombre,rut,telefono,correo;
     double porcentajeRanking;
     Estadistica e1 = new Estadistica();
     transient int ficha;
    public Persona(String nombre, String rut, String telefono, String correo) {
        
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getFicha() {
        return ficha;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

    public void setPorcentajeRanking(){
        porcentajeRanking = e1.calcularPorcentaje();
    }
    
    public double getPorcentajeRanking() {
       
        return e1.calcularPorcentaje();
    }

    
    
    
     
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Estadistica getE1() {
        return e1;
    }

    public void setE1(Estadistica e1) {
        this.e1 = e1;
    }

    
     
     
     
}
