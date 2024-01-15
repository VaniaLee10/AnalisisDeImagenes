/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * Se debe determinar el divisor
 * Offset es un valor que se añade al resultante, es un aumento o disminución a la iluminación
 * 
 * Convolución (con una mascara) :
 ** Proceso iterativo
 *** Aplicacion de convolución 
 * 1. Se coloca la mascara
 * 2. se itera la mascara
 ***** | x-1, y-1 | x-1, y | x-1, y+1 |
 ***** | x  , y-1 | x  , y | x,   y+1 |
 ***** | x+1, y-1 | x+1, y | x+1, y+1 |
 * 3. se multiplica (Vm * Vb) dada la colocación de la mascara
 * 4. para poder aplicar (aux)
 **** Proceso de validación
 * 
 * 1. Verificar si la mascara es diferente a 0
 * 2. Si lo es se debe de verificar si el buffer contiene información
 * 3. Si mascara != 0 y buffer es valido entonces convolucionamos
 * 
 */
public class Convolucion {
    
    BufferedImage imagenOriginal;

    public Convolucion(BufferedImage imagenOriginal) {
        this.imagenOriginal = imagenOriginal;
    }
    
    //Convolución simple
    public Image convolucionar(int[] mascara, double div){
        //El proceo de convolución se hace a cada pixel, se recorre el buffer
        Color nuevo;
        //System.out.println("Original: "+this.imagenOriginal.getWidth()+", "+this.imagenOriginal.getHeight());
        BufferedImage aux = new BufferedImage(this.imagenOriginal.getWidth(), this.imagenOriginal.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int x=0; x<this.imagenOriginal.getWidth(); x++){
            for(int y=0; y<this.imagenOriginal.getHeight(); y++){
                //System.out.println(x+", "+y);
                //Color viejo = new Color(this.imagenOriginal.getRGB(x, y));
                //Se tiene un nuevo tono para setear
                int rgb = convolucionar(x,y, mascara, div);
                nuevo = new Color(rgb);
                aux.setRGB(x, y, nuevo.getRGB());
            }
        }
        return HerramientasImagen.toImage(aux);
    }   
    
    public Image convolucionar5x5(int[] mascara, double div){
        Color nuevo;
        //System.out.println("Original: "+this.imagenOriginal.getWidth()+", "+this.imagenOriginal.getHeight());
        BufferedImage aux = new BufferedImage(this.imagenOriginal.getWidth(), this.imagenOriginal.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int x=0; x<this.imagenOriginal.getWidth(); x++){
            for(int y=0; y<this.imagenOriginal.getHeight(); y++){
                //System.out.println(x+", "+y);
                //Color viejo = new Color(this.imagenOriginal.getRGB(x, y));
                int rgb = convolucionar5x5(x,y, mascara, div);
                nuevo = new Color(rgb);
                aux.setRGB(x, y, nuevo.getRGB());
            }
        }
        return HerramientasImagen.toImage(aux);
    }   

    private int convolucionar(int x, int y, int[] mascara, double div) {
        int rgb = 0;
        //Se recorre la mascara
//        for(int j=0; j<mascara.length; j++){
//            for(int k=0; k<mascara.length; k++){
//                //Verificar si la mascara es diferente a 0
//                if(mascara[j][k] != 0){
//                    //Convolucionar
//                }
//            }
//        }
        Color aux;
        aux = new Color(this.imagenOriginal.getRGB(x,y));
        int valorRojo = aux.getRed();
        
        //Se usan 3 acumuladores, uno por canal
        int aR = 0;
        int aG = 0;
        int aB = 0;
        for(int j=0; j<9; j++){
            //Canales
            rgb = obtenerRGB(x,y,j);
            //System.out.println(j+" "+rgb);
            if(rgb != 0){
                aux = new Color(rgb);
                aR += aux.getRed()   * mascara[j];
                aG += aux.getGreen() * mascara[j];
                aB += aux.getBlue()  * mascara[j];
            }
        }
        aR /= div;
        aG /= div;
        aB /= div;
        aR = validar(aR);
        aG = validar(aG);
        aB = validar(aB);
        aux = new Color(aR, aG, aB);
        return aux.getRGB();
    }

    private int obtenerRGB(int x, int y, int j) {
        int rgb = 0;
        //System.out.println("x="+x+", y="+y);
        switch(j){
            case 0:
                if( (x-1 < 0) || (y-1 < 0) ) return 0;
                if( (x-1 >= this.imagenOriginal.getWidth()) || (y-1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-1, y-1);
                break;
            case 1:
                if( (x-1 < 0) || (y < 0) ) return 0;
                if( (x-1 >= this.imagenOriginal.getWidth()) || (y >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-1, y);
                break;
            case 2:
                //System.out.println("Caso 2: "+(x-1)+","+(y+1));
                if( (x-1 < 0) || (y+1 < 0) ) return 0;
                if( (x-1 >= this.imagenOriginal.getWidth()) || (y+1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-1, y+1);
                break;
            case 3:
                if( (x < 0) || (y-1 < 0) ) return 0;
                if( (x >= this.imagenOriginal.getWidth()) || (y-1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x, y-1);
                break;
            case 4:
                if( (x < 0) || (y < 0) ) return 0;
                if( (x >= this.imagenOriginal.getWidth()) || (y >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x, y);
                break;
            case 5:
                //System.out.println("Caso 5: "+(x)+","+(y+1));
                if( (x < 0) || (y+1 < 0) ) return 0;
                if( (x >= this.imagenOriginal.getWidth()) || (y+1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x, y+1);
                break;
            case 6:
                //System.out.println("Caso 6: "+(x+1)+","+(y-1));
                if( (x+1 < 0) || (y-1 < 0) ) return 0;
                if( (x+1 >= this.imagenOriginal.getWidth()) || (y-1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+1, y-1);
                //System.out.println("RGB: "+rgb);
                break;
            case 7:
                if( (x+1 < 0) || (y < 0) ) return 0;
                if( (x+1 >= this.imagenOriginal.getWidth()) || (y >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+1, y);
                break;
            case 8:
                if( (x+1 < 0) || (y+1 < 0) ) return 0;
                if( (x+1 >= this.imagenOriginal.getWidth()) || (y+1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+1, y+1);
                break;
        }
        return rgb;
    }
    
    private int convolucionar5x5(int x, int y, int[] mascara, double div) {
        int rgb = 0;
        Color aux;
        aux = new Color(this.imagenOriginal.getRGB(x,y));
        int aR = 0;
        int aG = 0;
        int aB = 0;
        for(int j=0; j<25; j++){
            rgb = obtenerRGB5x5(x,y,j);
            if(rgb != 0){
                aux = new Color(rgb);
                aR += aux.getRed()   * mascara[j];
                aG += aux.getGreen() * mascara[j];
                aB += aux.getBlue()  * mascara[j];
            }
        }
        aR /= div;
        aG /= div;
        aB /= div;
        aR = validar(aR);
        aG = validar(aG);
        aB = validar(aB);
        aux = new Color(aR, aG, aB);
        return aux.getRGB();
    }
    
    private int obtenerRGB5x5(int x, int y, int j) {
        int rgb = 0;
        //System.out.println("x="+x+", y="+y);
        switch(j){
            case 0:
                if( (x-2 < 0) || (y-2 < 0) ) return 0;
                if( (x-2 >= this.imagenOriginal.getWidth()) || (y-2 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-2, y-2);
                break;
            case 1:
                if( (x-2 < 0) || (y-1 < 0) ) return 0;
                if( (x-2 >= this.imagenOriginal.getWidth()) || (y-1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-2, y-1);
                break;
            case 2:
                if( (x-2 < 0) || (y < 0) ) return 0;
                if( (x-2 >= this.imagenOriginal.getWidth()) || (y >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-2, y);
                break;
            case 3:
                if( (x-2 < 0) || (y+1 < 0) ) return 0;
                if( (x-2 >= this.imagenOriginal.getWidth()) || (y+1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-2, y+1);
                break;
            case 4:
                if( (x-2 < 0) || (y+2 < 0) ) return 0;
                if( (x-2 >= this.imagenOriginal.getWidth()) || (y+2 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-2, y+2);
                break;
                
            case 5:
                if( (x-1 < 0) || (y-2 < 0) ) return 0;
                if( (x-1 >= this.imagenOriginal.getWidth()) || (y-2 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-1, y-2);
                break;
            case 6:
                if( (x-1 < 0) || (y-1 < 0) ) return 0;
                if( (x-1 >= this.imagenOriginal.getWidth()) || (y-1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-1, y-1);
                break;
            case 7:
                if( (x-1 < 0) || (y < 0) ) return 0;
                if( (x-1 >= this.imagenOriginal.getWidth()) || (y >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-1, y);
                break;
            case 8:
                if( (x-1 < 0) || (y+1 < 0) ) return 0;
                if( (x-1 >= this.imagenOriginal.getWidth()) || (y+1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-1, y+1);
                break;
            case 9:
                if( (x-1 < 0) || (y+2 < 0) ) return 0;
                if( (x-1 >= this.imagenOriginal.getWidth()) || (y+2 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x-1, y+2);
                break;
                
            case 10:
                if( (x < 0) || (y-2 < 0) ) return 0;
                if( (x >= this.imagenOriginal.getWidth()) || (y-2 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x, y-2);
                break;
            case 11:
                if( (x < 0) || (y-1 < 0) ) return 0;
                if( (x >= this.imagenOriginal.getWidth()) || (y-1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x, y-1);
                break;
            case 12:
                if( (x < 0) || (y < 0) ) return 0;
                if( (x >= this.imagenOriginal.getWidth()) || (y >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x, y);
                break;
            case 13:
                if( (x < 0) || (y+1 < 0) ) return 0;
                if( (x >= this.imagenOriginal.getWidth()) || (y+1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x, y+1);
                break;
            case 14:
                if( (x < 0) || (y+2 < 0) ) return 0;
                if( (x >= this.imagenOriginal.getWidth()) || (y+2 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x, y+2);
                break;
            
            case 15:
                if( (x+1 < 0) || (y-2 < 0) ) return 0;
                if( (x+1 >= this.imagenOriginal.getWidth()) || (y-2 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+1, y-2);
                break;
            case 16:
                if( (x+1 < 0) || (y-1 < 0) ) return 0;
                if( (x+1 >= this.imagenOriginal.getWidth()) || (y-1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+1, y-1);
                break;
            case 17:
                if( (x+1 < 0) || (y < 0) ) return 0;
                if( (x+1 >= this.imagenOriginal.getWidth()) || (y >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+1, y);
                break;
            case 18:
                if( (x+1 < 0) || (y+1 < 0) ) return 0;
                if( (x+1 >= this.imagenOriginal.getWidth()) || (y+1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+1, y+1);
                break;
            case 19:
                if( (x+1 < 0) || (y+2 < 0) ) return 0;
                if( (x+1 >= this.imagenOriginal.getWidth()) || (y+2 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+1, y+2);
                break;
                
            case 20:
                if( (x+2 < 0) || (y-2 < 0) ) return 0;
                if( (x+2 >= this.imagenOriginal.getWidth()) || (y-2 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+2, y-2);
                break;
            case 21:
                if( (x+2 < 0) || (y-1 < 0) ) return 0;
                if( (x+2 >= this.imagenOriginal.getWidth()) || (y-1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+2, y-1);
                break;
            case 22:
                if( (x+2 < 0) || (y < 0) ) return 0;
                if( (x+2 >= this.imagenOriginal.getWidth()) || (y >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+2, y);
                break;
            case 23:
                if( (x+2 < 0) || (y+1 < 0) ) return 0;
                if( (x+2 >= this.imagenOriginal.getWidth()) || (y+1 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+2, y+1);
                break;
            case 24:
                if( (x+2 < 0) || (y+2 < 0) ) return 0;
                if( (x+2 >= this.imagenOriginal.getWidth()) || (y+2 >= this.imagenOriginal.getHeight()) ) return 0;
                rgb = this.imagenOriginal.getRGB(x+2, y+2);
                break;
            
        }
        return rgb;
    }
    
    
    private static int validar(int i) {
        if (i < 0) {
            return 0;
        }
        if (i > 255) {
            return 255;
        }
        return i;
    }
}
