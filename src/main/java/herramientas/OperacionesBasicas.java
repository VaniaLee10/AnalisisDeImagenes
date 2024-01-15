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
 * @author Eduardo
 */
public class OperacionesBasicas {
    
    public static Image escalaDeGrises (Image original){
        //En buffer
        //Pasar la imagen a buffer
        BufferedImage bi = HerramientasImagen.toBufferedImage(original);
        int color = 0;
        int y = 0;
        
        for(int x=0; x<bi.getWidth(); x++){
            for(y=0; y<bi.getHeight(); y++){
                //1. Obtener la codificación entera en rgb
                color = bi.getRGB(x, y);
                //2. Utilizando el int obtener de las instancias en color para conocer los valores RGB por separado
                Color pixelColor = new Color(color);
                //3. Sacar el nuevo valor en gris por medio de un promedio
                //Promediar los colores de rgb
                int promedio = ( pixelColor.getRed() + pixelColor.getGreen() + pixelColor.getBlue() ) / 3 ;
                //System.out.println("Promedio: "+promedio);
                //4. Actualizamos el nuevo color
                pixelColor = new Color(promedio, promedio, promedio);
                bi.setRGB(x, y, pixelColor.getRGB());
            }
        }
        
        Image img = herramientas.HerramientasImagen.toImage(bi);
        return img;
    }
    
    
    public static Image modificarIluminacion (Image original, int iluminacion){
        //Pasar la imagen a buffer
        BufferedImage bi = HerramientasImagen.toBufferedImage(original);
        
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                //1. Obtener la codificación entera en rgb
                int color = bi.getRGB(x, y);
                //2. Utilizando el int obtener de las instancias en color para conocer los valores RGB por separado
                Color pixelColor = new Color(color); //c2
                //3. 
                int redIluminado = validar(pixelColor.getRed()+iluminacion);
                int blueIluminado = validar(pixelColor.getBlue()+iluminacion);
                int greenIluminado = validar(pixelColor.getGreen()+iluminacion);
                pixelColor = new Color(redIluminado, greenIluminado, blueIluminado);
                bi.setRGB(x, y, pixelColor.getRGB());
            }
        }
        
        Image img = herramientas.HerramientasImagen.toImage(bi);
        return img;
    }

    public static int validar(int i) {
        if (i > 255) return 255;
        if (i < 0) return 0;
        return i;
    }
    
    public static Image calentarImagen (Image original, int calor){
        //Pasar la imagen a buffer
        BufferedImage bi = HerramientasImagen.toBufferedImage(original);
        
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                //1. Obtener la codificación entera en rgb
                int color = bi.getRGB(x, y);
                //2. Utilizando el int obtener de las instancias en color para conocer los valores RGB por separado
                Color pixelColor = new Color(color); //c2
                //3. 
                pixelColor = new Color(validar(pixelColor.getRed()+calor), pixelColor.getGreen(), validar(pixelColor.getBlue()-calor));
                bi.setRGB(x, y, pixelColor.getRGB());
            }
        }
        
        Image img = herramientas.HerramientasImagen.toImage(bi);
        return img;
    }
    
    public static Image enfriarImagen (Image original, int frio){
        //Pasar la imagen a buffer
        BufferedImage bi = HerramientasImagen.toBufferedImage(original);
        
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                //1. Obtener la codificación entera en rgb
                int color = bi.getRGB(x, y);
                //2. Utilizando el int obtener de las instancias en color para conocer los valores RGB por separado
                Color pixelColor = new Color(color); //c2
                //3. 
                pixelColor = new Color(validar(pixelColor.getRed()-frio), pixelColor.getGreen(), validar(pixelColor.getBlue()+frio));
                bi.setRGB(x, y, pixelColor.getRGB());
            }
        }
        
        Image img = herramientas.HerramientasImagen.toImage(bi);
        return img;
    }
    
    public static Image negativoImagen (Image original){
        //Pasar la imagen a buffer
        BufferedImage bi = HerramientasImagen.toBufferedImage(original);
        
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                Color col = new Color(bi.getRGB(x, y));
                col = new Color(255-col.getRed(),255-col.getGreen(), 255-col.getBlue());
                bi.setRGB(x, y, col.getRGB());
            }
        }
        
        Image img = herramientas.HerramientasImagen.toImage(bi);
        return img;
    }
    
}
