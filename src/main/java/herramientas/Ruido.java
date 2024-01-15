/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Eduardo
 */
public class Ruido {
    
    public static Image agregarRuidoAditivo(Image original, double porcentaje){
        BufferedImage bi = HerramientasImagen.toBufferedImage(original);
        int cp = (int) ((porcentaje/100)*(bi.getHeight() * bi.getWidth()));
        Color aditivo = new Color(255,255,255);
        Random ran = new Random();
        int x, y;
        for(int j=0; j<cp; j++){
            //Posición aleatoria dentrp de la imagen
            x = ran.nextInt(bi.getWidth());
            y = ran.nextInt(bi.getHeight());
            bi.setRGB(x, y, aditivo.getRGB());
        }
        //System.out.println("");
        return bi;
    }
    
    public static Image agregarRuidoSustractivo(Image original, double porcentaje){
        BufferedImage bi = HerramientasImagen.toBufferedImage(original);
        int cp = (int) ((porcentaje/100)*(bi.getHeight() * bi.getWidth()));
        Color sustractivo = new Color(0,0,0);
        Random ran = new Random();
        int x, y;
        for(int j=0; j<cp; j++){
            //Posición aleatoria dentrp de la imagen
            x = ran.nextInt(bi.getWidth());
            y = ran.nextInt(bi.getHeight());
            bi.setRGB(x, y, sustractivo.getRGB());
        }
        //System.out.println("");
        return bi;
    }
    
    public static Image agregarRuidoSalYPimienta(Image original, double porcentaje){
        BufferedImage bi = HerramientasImagen.toBufferedImage(original);
        int cp = (int) ((porcentaje/100)*(bi.getHeight() * bi.getWidth()));
        Color aditivo = new Color(255,255,255);
        Color sustractivo = new Color(0,0,0);
        Random ran = new Random();
        int x, y, rand;
        for(int j=0; j<cp; j++){
            //Posición aleatoria dentro de la imagen
            rand = ran.nextInt(2);
            x = ran.nextInt(bi.getWidth());
            y = ran.nextInt(bi.getHeight());
            if(rand == 1){
                bi.setRGB(x, y, aditivo.getRGB());
            }else{
                bi.setRGB(x, y, sustractivo.getRGB());
            }   
        }
        //System.out.println("");
        return bi;
    }
    
}
