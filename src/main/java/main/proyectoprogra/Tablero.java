
package main.proyectoprogra;

public class Tablero {
    int[][] matriz = new int[3][3];
    
    public Tablero(){
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = -5;
               
            }            
        }
    }
    
    public void agregarElemento(int pos,int tipo){
        
        
        
        switch(pos){
            case 1:
                matriz[0][0] = tipo;
                                
                break;
            case 2:
                matriz[0][1] = tipo; 
                
                break;    
            case 3:
                matriz[0][2] = tipo;
                
                break;
            case 4:
                matriz[1][0] = tipo;
                
                break;
            case 5:
                matriz[1][1] = tipo;
                
                break;
            case 6:
                matriz[1][2] = tipo;
                
                break;
            case 7:
                matriz[2][0] = tipo;
                
                break;
            case 8:
                matriz[2][1] = tipo;
                
                break;
            case 9:
                
                matriz[2][2] = tipo;
                
                
                break;
            
            
        }
    }
    /* para validar se podría verificar si la suma da 111 o 0
    casos que retornta true -> 3 0
    casos que retorna false ->  resultado<3  && (resultado > 0 || resultado < 0)  
    
    0 0 0 
    0 0 0
    0 0 0
    */
    public boolean validarGanador(){
        
        for (int i = 0; i < 9; i++) {
            int total = 999 ;
            switch(i){
                case 1:
                    total = matriz[0][0]+matriz[0][1]+matriz[0][2];
                    break;
                case 2:
                    total = matriz[1][0]+matriz[1][1]+matriz[1][2];
                    break;
                case 3:
                    total = matriz[2][0]+matriz[2][1]+matriz[2][2];
                    break;  
                case 4:
                    total = matriz[0][0]+matriz[1][0]+matriz[2][0];
                    break;
                case 5:
                    total = matriz[0][1]+matriz[1][1]+matriz[2][1];
                    break;
                case 6:
                    total = matriz[0][2]+matriz[1][2]+matriz[2][2];
                    break;
                case 7:
                    total = matriz[0][0]+matriz[1][1]+matriz[2][2];
                    break;
                case 8:
                    total = matriz[0][2]+matriz[1][1]+matriz[2][0];
                    break;
            
            }
            
            if (total == 3 || total == 0) {
                
                return false;
            }
            
        }
        
        return true;
    }
    
    public boolean validaPos(int pos){
       
        int temp = 0;
        switch(pos){
            case 1:
                temp = matriz[0][0];
                      
                break;
            case 2:
                temp = matriz[0][1];
                
                break;    
            case 3:
                temp = matriz[0][2];
                break;
            case 4:
                temp = matriz[1][0];
                break;
            case 5:
                temp = matriz[1][1];
                break;
            case 6:
                temp = matriz[1][2];
                break;
            case 7:
                temp = matriz[2][0];
                break;
            case 8:
                temp = matriz[2][1];
                break;
            case 9:
                temp = matriz[2][2];
                break;
            }
                
        if(temp == -5){
            return false;
        }else{
            return true;
        }
                
                
        
        
    }
    
    public int retornaFichaganadora(){
        
        for (int i = 0; i < 9; i++) {
            int total = 999 ;
            switch(i){
                case 1:
                    total = matriz[0][0]+matriz[0][1]+matriz[0][2];
                    break;
                case 2:
                    total = matriz[1][0]+matriz[1][1]+matriz[1][2];
                    break;
                case 3:
                    total = matriz[2][0]+matriz[2][1]+matriz[2][2];
                    break;  
                case 4:
                    total = matriz[0][0]+matriz[1][0]+matriz[2][0];
                    break;
                case 5:
                    total = matriz[0][1]+matriz[1][1]+matriz[2][1];
                    break;
                case 6:
                    total = matriz[0][2]+matriz[1][2]+matriz[2][2];
                    break;
                case 7:
                    total = matriz[0][0]+matriz[1][1]+matriz[2][2];
                    break;
                case 8:
                    total = matriz[0][2]+matriz[1][1]+matriz[2][0];
                    break;
            
            }
            if (total == 3) {
                //gana el jugador con la ficha 
                return 1;
                
                
            }else if (total == 0){
                //gana el jugador con la ficha 0
               return 0;
            
            }else if(i == 9){
                //empate
                return -1;
            }
            
        }
        return -1;
        
    }
    
    public void imprimeTableroEjemplo(){
        int k  = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(k+" ");
                k++;
            }
            System.out.println();
        }
    }
    
    
    public void imprimeTablero(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" "+matriz[i][j]);
                
            }
            System.out.println();
        }
    }

}
