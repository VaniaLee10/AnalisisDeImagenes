/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import gui.Graficador;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author working
 */
public class HerramientasImagen {
    
    public static Image abrirImagen (){
    
          try {
            // definir los filtros para lectura
            FileNameExtensionFilter filtro =
                    new FileNameExtensionFilter("Imagenes","jpg","jpeg","png","bmp");
            // crear un selector de archivos
            JFileChooser selector = new JFileChooser();
            // agregar el filtro al selector
            selector.addChoosableFileFilter(filtro);
            // especificar que solo se puedan abrir archivos
            selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
            
            //ejecutar el selector de imagenes
            
            int res = selector.showOpenDialog(null);
            
            if (res == 1 ){
                
                return null;
                
            }
            
            File archivo = selector.getSelectedFile();
            
            BufferedImage bi = ImageIO.read(archivo);
            
            //Image imagenDibujada = dibujarFigura();
            return toBufferedImage(bi);
        } catch (IOException ex) {
            Logger.getLogger(HerramientasImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }  
    
    public static Image toImage (BufferedImage bi){
        return bi.getScaledInstance(bi.getWidth(),bi.getHeight(), BufferedImage.TYPE_INT_RGB);
    }
    
    public static BufferedImage toBufferedImage (Image imagen){
         // imagen es un objeto de tipo BufferedImage
//        if (imagen instanceof BufferedImage){
//          return (BufferedImage)imagen;
//        }
        BufferedImage bi = 
                new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), BufferedImage.TYPE_INT_RGB);
        
        Graphics2D nueva = bi.createGraphics();
        nueva.drawImage(imagen, 0, 0,null);
        nueva.dispose();
        
        return bi;
    }
    public static Image copiarImagen(Image i){
        BufferedImage bi = toBufferedImage(i);
        return bi.getScaledInstance(bi.getWidth(),bi.getHeight(), BufferedImage.TYPE_INT_RGB);
    }
    
    private static Image dibujarFigura() {
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        //Graphics g = bufferedImage.getGraphics();
        Color p = new Color (0,0,255);
        //La clase color usa 8 bits por color 
        
        Color color4 = new Color(0,0,255);
        int aux = color4.getRGB();
        System.out.println(aux);
        Color color3 = new Color(255,0,0);
        int aux1 = color3.getRGB();
        System.out.println(aux1);
        Color color2 = new Color(0,255,0);
        int aux2 = color2.getRGB();
        System.out.println(aux2);
        Color color1 = new Color(0,0,0); 
        int aux3 = color1.getRGB();
        System.out.println(aux3);
        
        for(int x=10; x<=190; x++){
            for(int y=10; y<=190; y++){
                //bufferedImage.setRGB(x, y, color1.getRGB());
                if (x <= 50 || x >= 190 - 40 || y <= 50 || y >= 190 - 40) 
                    bufferedImage.setRGB(x, y, color4.getRGB());
                if (x <= 40 || x >= 190 - 30 || y <= 40 || y >= 190 - 30) 
                    bufferedImage.setRGB(x, y, color3.getRGB());
                if (x <= 30 || x >= 190 - 20 || y <= 30 || y >= 190 - 20) 
                    bufferedImage.setRGB(x, y, color2.getRGB());
                if (x <= 20 || x >= 190 - 10 || y <= 20 || y >= 190 - 10) 
                    bufferedImage.setRGB(x, y, color1.getRGB());
            }
        }
        
        return bufferedImage;
    }
    
    public static void calcularHistograma(Image imagen){
        //Contadores de cada tono de color
        int[] contR = new int[256];
        int[] contG = new int[256];
        int[] contB = new int[256];
        
        //Ciclos para poder recorrer los pixeles de la imagen
        BufferedImage bi = toBufferedImage(imagen);
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                Color pixel = new Color(bi.getRGB(x,y));
                contR[pixel.getRed()] = contR[pixel.getRed()]+1;
                //System.out.println(pixel.getRed());
                contG[pixel.getGreen()] = contG[pixel.getGreen()]+1;
                contB[pixel.getBlue()] = contB[pixel.getBlue()]+1;
            }
        }      
        
        //for(int i=0; i<contR.length; i++){
        //    System.out.println("Rojo "+i+": "+contR[i]);
        //}

        graficar(contR,contG,contB);
        
        //System.out.println("");
    }
    
    public static double[] calcularHistogramaReturn(Image imagen){
        //Contadores de cada tono de color
        int[] contR = new int[256];
        int[] contG = new int[256];
        int[] contB = new int[256];
        
        //Ciclos para poder recorrer los pixeles de la imagen
        BufferedImage bi = toBufferedImage(imagen);
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                Color pixel = new Color(bi.getRGB(x,y));
                contR[pixel.getRed()] = contR[pixel.getRed()]+1;
                //System.out.println(pixel.getRed());
                contG[pixel.getGreen()] = contG[pixel.getGreen()]+1;
                contB[pixel.getBlue()] = contB[pixel.getBlue()]+1;
            }
        }      
        
        //for(int i=0; i<contR.length; i++){
        //    System.out.println("Rojo "+i+": "+contR[i]);
        //}

        graficar(contR,contG,contB);
        double[] doubles = Arrays.stream(contR).asDoubleStream().toArray();
        return doubles;
        
        //System.out.println("");
    }
    
    public static double[] calcularHistogramaRojo(Image imagen){
        //Contadores de tono rojo
        double[] contR = new double[256];        
        //Ciclos para poder recorrer los pixeles de la imagen
        BufferedImage bi = toBufferedImage(imagen);
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                Color pixel = new Color(bi.getRGB(x,y));
                contR[pixel.getRed()] = contR[pixel.getRed()]+1;
            }
        }
        //for(int i=0; i<contR.length; i++){
        //    System.out.println("Rojo "+i+": "+contR[i]);
        //}
        return contR;
    }
    
    public static double[] calcularHistogramaVerde(Image imagen){
        //Contadores de tono rojo
        double[] contG = new double[256];        
        //Ciclos para poder recorrer los pixeles de la imagen
        BufferedImage bi = toBufferedImage(imagen);
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                Color pixel = new Color(bi.getRGB(x,y));
                contG[pixel.getGreen()] = contG[pixel.getGreen()]+1;
            }
        }
        //for(int i=0; i<contG.length; i++){
        //    System.out.println("Verde "+i+": "+contG[i]);
        //}
        return contG;
    }
    
    public static double[] calcularHistogramaAzul(Image imagen){
        //Contadores de tono rojo
        double[] contB = new double[256];        
        //Ciclos para poder recorrer los pixeles de la imagen
        BufferedImage bi = toBufferedImage(imagen);
        for(int x=0; x<bi.getWidth(); x++){
            for(int y=0; y<bi.getHeight(); y++){
                Color pixel = new Color(bi.getRGB(x,y));
                contB[pixel.getBlue()] = contB[pixel.getBlue()]+1;
            }
        }
        //for(int i=0; i<contB.length; i++){
        //    System.out.println("Azul "+i+": "+contB[i]);
        //}
        return contB;
    }
    
    
    private static void graficar(int[] contR, int[] contG, int[] contB){
        Graficador g = new Graficador("Tono", "Frecuencia", "Histograma de Frecuencias");
        g.agregarSerie("Rojo", contR);
        g.agregarSerie("Azul", contB);
        g.agregarSerie("Verde", contG);
        g.crearGrafica();
        g.muestraGrafica();
    }
    
}
