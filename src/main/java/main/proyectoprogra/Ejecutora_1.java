package main.proyectoprogra;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejecutora_1 {
    ArrayList<Persona> listaPersonas = new ArrayList();
    
    
    public void menu(){
        Scanner lectura = new Scanner(System.in);
        
        int opcion = -1;
        
        
        Deserializar();
        while(opcion!=0){
            System.out.println("1.Agregar Jugador\n2.Imprime Jugadores\n3.Salir");
            opcion = lectura.nextInt();
            
            switch(opcion){
                case 1 :
                    
                    crearJugador(); 
                    break;
                case  2:
                    
                    for(Persona p : listaPersonas){
                        System.out.println(p.getNombre());
                    }
                    
                    //System.out.println(listaPersonas.get(0).getNombre());
                    break;
                default:
                    
                    Serializar();
                    
                    opcion = 0;
                    break;
            }
        }
        
        
    }
    
    public void crearJugador(){
        Scanner lecturaInt = new Scanner(System.in);
        Scanner lecturaStr = new Scanner(System.in);
        
        Persona p1;
        String nombre,rut,telefono,correo;
        
        System.out.println("\n<--------------------CREACION ESTUDIANTE-------------------->\n");
        System.out.println("Nombre: ");
        nombre  = lecturaStr.nextLine();
        System.out.println("rut: ");
        rut = lecturaStr.nextLine();
        System.out.println("Telefono: ");
        telefono =lecturaStr.nextLine();
        System.out.println("Correo: ");
        correo = lecturaStr.nextLine();
        
        
        p1 = new Persona(nombre,rut, telefono, correo);
       
        listaPersonas.add(p1);
             
    }
    
    public void Deserializar(){
        try{
            FileInputStream archivo = new FileInputStream("personas.txt");
            ObjectInputStream objEntrada = new ObjectInputStream(archivo);
            listaPersonas = (ArrayList<Persona>) objEntrada.readObject();
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
            objSalida.writeObject(listaPersonas);
            objSalida.close();
            archivo.close();
            System.out.println("Archivo serializado...");
        }catch(IOException e){
            System.out.println(e.getStackTrace());
        }
    }
}
