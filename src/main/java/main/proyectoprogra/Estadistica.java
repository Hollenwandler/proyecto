package main.proyectoprogra;

import java.io.Serializable;


public class Estadistica implements Serializable {
    int ganadas,perdidas,empatadas,disputadas;

    public Estadistica() {
        this.ganadas = 0;
        this.perdidas = 0;
        this.empatadas = 0;
        this.disputadas = 0;
    }
   
    

    public int getGanadas() {
        return ganadas;
    }

    public void agregarGanadas() {
        this.ganadas++;
    }

    public int getPerdidas() {
        return perdidas;
    }

    public void agregarPerdidas() {
        this.perdidas++;
    }

    public int getEmpatadas() {
        return empatadas;
    }

    public void agregarEmpatadas() {
        this.empatadas++;
    }

    public int getDisputadas() {
        return disputadas;
    }

    public void agregarDisputadas() {
        this.disputadas++;
    }
    
    public double calcularPorcentaje(){
        if(disputadas != 0) return (double ) ganadas / (double) disputadas;
        return 0;
    }
    
    
}
