package main.proyectoprogra;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;



/*
    COSAS POR HACER (general):
        - Serializar solamente el arraylist Jugadores
        - Validar unos errores
        - Refactorizar el codigo
*/


public class Ejecutora_2 {
    ArrayList<Persona> Jugadores = new ArrayList();
    ArrayList<Persona> Ranking = new ArrayList();
    Persona oponente1;
    Persona oponente2;
    int dado;
    
    public void menu(){
        int opcion = -1;
        
        Deserializar();
        
        while(opcion!=0){
            Scanner lectura = new Scanner(System.in);
            System.out.println("1.Iniciar Juego\n2.Crear juego\n3.Imprime Ranking\n");
            opcion = lectura.nextInt();
            switch(opcion){
                case 1:
                    iniciarJuego();
                    break;
                case 2:
                    crearJuego();
                    System.out.println(Jugadores.get(0).getNombre());
                    break; 
                case 3:
                    imprimeRanking();
                    break;
                default:
                    opcion =0;
                    break;
            }
        }
    }
    
    public void crearJuego(){
        //preguntar jugadores
        
        /*
            Falta validar que no ingrese dos veces el mismo jugador y que la opcion este dentro del rango
        */
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;
        System.out.println("Jugadores para ingresar : ");
        int a=0;
        for(Persona p : Jugadores){
            System.out.println(a+"."+p.nombre);
            a++;
        }
        System.out.println("Ingrese jugador j1 : ");
        opcion = entrada.nextInt();
        oponente1 = Jugadores.get(opcion);
        System.out.println("Ingrese jugador j2 : ");
        opcion = entrada.nextInt();
        oponente2 = Jugadores.get(opcion);
        //fecha
        // definir que jugador va ir primero
       
        
        
        
        
    }
    
    public void Deserializar(){
        try{
            FileInputStream archivo = new FileInputStream("personas.txt");
            ObjectInputStream objEntrada = new ObjectInputStream(archivo);
            Jugadores = (ArrayList<Persona>) objEntrada.readObject();
            objEntrada.close();
            archivo.close();
        }catch(IOException e){
            System.out.println("Error el archivo no existe : "+e.getStackTrace());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error el archivo no existe : "+ex.getStackTrace());
        }
    }
    
    public void Serializar(){
            try{
            FileOutputStream archivo = new FileOutputStream("personas.txt");
            ObjectOutputStream objSalida = new ObjectOutputStream(archivo);
            objSalida.writeObject(Jugadores);
            objSalida.close();
            archivo.close();
            System.out.println("Archivo serializado...");
        }catch(IOException e){
            System.out.println(e.getStackTrace());
        }
    }

    public void iniciarJuego(){
        //Logica de juego
        // falta validar para que no se caiga
        
        Tablero tab = new Tablero();
                              
        Scanner lecturaInt = new Scanner(System.in);
                
        
        /*
        Primero y segundo son los jugadores temporales que disputaran la serie de partidas
        al finalizar todas las partidas se comprueba que el nombre de primero sea igual a oponente1
        para asi poder asignar las ganadas perdidas etc..
        */
        Persona primero,segundo;
        
        // valor dde los dados por defecto
        int valorOponente1 = 0, valorOponente2 = 0;
        
        //lanzamiento de dados 
        while(valorOponente1 ==0 && valorOponente2 == 0 ){
            valorOponente1 = generarDado();
            valorOponente2 = generarDado();
        }
        //se elige quien va primero
        if (valorOponente1 > valorOponente2){
            System.out.println("El jugador "+oponente1.getNombre()+" ira primero\n");
            primero = oponente1;
            segundo = oponente2;
        }else{
            System.out.println("El jugador "+oponente2.getNombre()+" ira primero\n");
            primero = oponente2;
            segundo = oponente1;
        }
        
        System.out.println("Inicio oponente1  --> "+oponente1.getNombre()+"\n"+"Oponente2 --> "+oponente2.getNombre()+"\n");
        
        //esto es de pruebea eliminar proximamente
        for(Persona p : Jugadores){
            System.out.println(p.getNombre()+" victorias -> "+p.e1.getGanadas());
        }
        System.out.println();
        
        
        //eleccion de ficha 
        System.out.println(primero.getNombre()+" Elija su ficha ( 0 o 1 ) : \n");
        int opcionFicha = lecturaInt.nextInt();
        
        if (opcionFicha == 0){
            primero.setFicha(0);
            segundo.setFicha(1);
            
        }else{
            primero.setFicha(1);
            segundo.setFicha(0);
            
        }
        System.out.println("Jugador 1  : "+primero.getNombre()+" Jugara con : "+primero.getFicha()+"\nJugador 2 : "+segundo.getNombre()+" Jugara con : "+segundo.getFicha());
        
        
        /*
        bucles para el juego ->
        primer bucle : 
            El primer bucle sirve para mantener la serie de partidas
        Segundo bucle : 
            El segundo bucle es la partida en sí (falta limpiar el codigo)
        */
        Persona temp;
        boolean salir = true;
        while(salir){
            
            tab = new Tablero();
            int pos = 0;
            int contador= 0;
            int fichaGanador;
           
            
            /*
            La serie de partidas no deben superar los 9 movimientos por lo tanto se usa un contador
            para poder ir validando el numero de movientos que será 1 por cada jugador
            */
            while(tab.validarGanador() && contador < 9){
                //impresion de matriz de ejemplo con numeros
                System.out.println("\nMatriz de ejemplo : ");
                tab.imprimeTableroEjemplo();
                System.out.println("escriba -1 para salir");   
                
                
                //Entrada de posicion del quien va primero
                do{
                    System.out .println(primero.getNombre()+" Ingresa posicion del tablero 1-9 : ");
                    pos = lecturaInt.nextInt();
                    if(pos == -1){ // esto valida si quiere salir 
                        
                        salir = false;
                        break;
                    }
                }while((tab.validaPos(pos)));
                
                //si el elemento esta permitido se agrega al tablero y se aumenta el contador
                tab.agregarElemento(pos,primero.getFicha());
                contador++;

                tab.imprimeTablero();

                
                //valida si ya ganó el jugador anterior
                if (!tab.validarGanador() ){
                    break;
                }
                /*
                valida si decidió salir entonces el segundo no debe jugar
                ademas de jugar el ultimo moviento
                */
                if(contador > 8 || !salir){
                    System.out.println("<---------------------------------fin del juego--------------------------------->");
                    break;
                }
                
                do{
                    System.out.println(segundo.getNombre()+" Ingresa posicion del tablero 1-9 : ");
                    pos = lecturaInt.nextInt();
                    if(pos == -1){
                        salir = false;
                        break;
                    }
                }while((tab.validaPos(pos)));
                //misma validacion de arriba si elige salir 
                if(!salir){
                    System.out.println("<---------------------------------fin del juego--------------------------------->");
                    break;
                }
                tab.agregarElemento(pos,segundo.getFicha());

                tab.imprimeTablero();
                contador++;
            
        }
        
        fichaGanador = tab.retornaFichaganadora();
        
        /*
        En esta parte se determina el ganador de la ficha
        si es empate ambos se les suma +1 en estadistica, luego se intercambia primero por segundo
        si gana el primero se debe sumar su respectivo punto para luego cambiar primero por segundo, caso contrario
        si gana el segundo se debe intercambiar segundo por primero.
        */
        if(fichaGanador == -1){
            System.out.println("empate");
            temp = segundo;
            segundo = primero;
            primero = temp;
            primero.e1.agregarDisputadas();
            segundo.e1.agregarDisputadas();
        }else if(fichaGanador == primero.getFicha()){ // si gana primero en la siguente debe ser segundo
            
            System.out.println("gana primero "+primero.getNombre()+" con la ficha : "+primero.getFicha());
            primero.e1.agregarGanadas();
            primero.e1.agregarDisputadas();
            segundo.e1.agregarDisputadas();
            
            
            
        }else if(fichaGanador == segundo.getFicha()){ // si gana segundo en la siguiente deber ser primero
            System.out.println("gana segundo "+segundo.getNombre()+" con la ficha : "+segundo.getFicha());
            segundo.e1.agregarGanadas();
            primero.e1.agregarDisputadas();
            segundo.e1.agregarDisputadas();
            
            temp = segundo;
            segundo = primero;
            primero = temp;
        }
       
        
    }
    
        
        // se busca quien es primero 
        if(primero.getNombre().equals(oponente1.getNombre())){
            oponente1 = primero;
            oponente2 = segundo;
        }else{
            oponente1 = segundo;
            oponente2 = primero;
        }
       
        System.out.println("Final oponente1  --> "+oponente1.getNombre()+"Victorias --> "+oponente1.e1.getGanadas()+"\n"+"Oponente2 --> "+oponente2.getNombre()+"Victorias --> "+oponente2.e1.getGanadas()+"\n");
        
        // se busca en la lista la posicion de oponente 1 para actualizarlo
        for (int i = 0; i < Jugadores.size(); i++) {
            if(Jugadores.get(i).getNombre().equals(oponente1.getNombre())){
                Jugadores.set(i, oponente1);
            }
            if(Jugadores.get(i).getNombre().equals(oponente2.getNombre())){
                Jugadores.set(i, oponente2);
            }
        
        }
        for(Persona p : Jugadores){
            System.out.println(p.getNombre()+" victorias -> "+p.e1.getGanadas());
        }
        System.out.println();
        
}
    
    public void imprimeRanking(){
        Ranking = (ArrayList<Persona>) Jugadores.clone();
        /*
            RECORDAR ESTO ES MUUUUUUY IMPORTANTE
            En esta funcion se debe implementar un algoritmo que muestre una tabla con los datos de cada jugadores ordenados por su ranking
            Ejemplos -> 
                        
                Nombre Ganadas Perdidas Empatadas Disputadas Win rate
                Juan    x       x       x           x          x(redondeado)
                Pepe    x       x       x           x          x(redondeado)
                Kaka    x       x       x           x          x(redondeado)
        */
        
        Collections.sort(Ranking, new comparaRanking());//esto te ordena la lista de mayor a menor
        
        for(Persona p : Jugadores){
            System.out.println(p.getNombre()+" kdr-> "+p.getPorcentajeRanking());
        }
        System.out.println();
        
        System.out.println("imprime lista ranking : ");
        for(Persona p : Ranking){
            System.out.println(p.getNombre()+" kdr-> "+p.getPorcentajeRanking());
        }
        System.out.println();
        
    }
    
    public int generarDado(){
        int numTemp  = (int) Math.round(Math.random()*10);
        if(numTemp <= 6 && numTemp>0){
            return numTemp;
        }else{
           return generarDado();
        }
    }
}

class comparaRanking implements Comparator<Persona>{
    @Override
    public int compare(Persona p1, Persona p2){
         if (p1.getPorcentajeRanking() == p2.getPorcentajeRanking())
            return 0;
        else if (p1.getPorcentajeRanking() < p2.getPorcentajeRanking())
            return 1;
        else
            return -1;
    }
    
}