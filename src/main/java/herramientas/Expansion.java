/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 *
 * @author Eduardo
 */
public class Expansion {

    public static Image expansionLineal(int r1, int r2, Image imagen) {
        //Se pasa una imagen en blanco y negro
        BufferedImage aux = HerramientasImagen.toBufferedImage(imagen);
        for (int x = 0; x < aux.getWidth(); x++) {
            for (int y = 0; y < aux.getHeight(); y++) {
                // obtener el color
                Color pixel = new Color(aux.getRGB(x, y));
                int denominador = (255 / (r2 - r1));
                int r = validar((pixel.getRed() - r1) * denominador);
                int g = validar((pixel.getGreen() - r1) * denominador);
                int b = validar((pixel.getBlue() - r1) * denominador);
                // validamos 
                pixel = new Color(r, g, b);
                aux.setRGB(x, y, pixel.getRGB());
            }
        }
        return aux;
    }

    //arreglo de los arreglos de 6 elementos donde:
    //los 3 primeros son los r1 de los canales rojo
    //los 3 primeros son los r2 de los canales verde
    public static Image expansionLinealColores(int[] rs, Image imagen) {
        //Se pasa una imagen en blanco y negro
        BufferedImage aux = HerramientasImagen.toBufferedImage(imagen);
        for (int x = 0; x < aux.getWidth(); x++) {
            for (int y = 0; y < aux.getHeight(); y++) {
                // obtener el color
                Color pixel = new Color(aux.getRGB(x, y));
                int r = validar((pixel.getRed() - rs[0]) * (255 / (rs[3] - rs[0])));
                int g = validar((pixel.getGreen() - rs[1]) * (255 / (rs[4] - rs[1])));
                int b = validar((pixel.getBlue() - rs[2]) * (255 / (rs[5] - rs[2])));
                // validamos 
                pixel = new Color(r, g, b);
                aux.setRGB(x, y, pixel.getRGB());
            }
        }
        return aux;
    }

    public static Image expansionLineal(int rr1, int rr2, int rg1, int rg2, int rb1, int rb2, Image imagen) {
        //Se pasa una imagen en blanco y negro
        BufferedImage aux = HerramientasImagen.toBufferedImage(imagen);
        for (int x = 0; x < aux.getWidth(); x++) {
            for (int y = 0; y < aux.getHeight(); y++) {
                // obtener el color
                Color pixel = new Color(aux.getRGB(x, y));
                int r = validar((pixel.getRed() - rr1) * (255 / (rr2 - rr1)));
                int g = validar((pixel.getGreen() - rg1) * (255 / (rg2 - rg1)));
                int b = validar((pixel.getBlue() - rb1) * (255 / (rb2 - rb1)));
                // validamos 
                pixel = new Color(r, g, b);
                aux.setRGB(x, y, pixel.getRGB());
            }
        }
        return aux;
    }

    public static Image expansionLn(Image imagen) {
        //Se pasa una imagen en blanco y negro
        BufferedImage aux = HerramientasImagen.toBufferedImage(imagen);
        for (int x = 0; x < aux.getWidth(); x++) {
            for (int y = 0; y < aux.getHeight(); y++) {
                // obtener el color
                Color pixel = new Color(aux.getRGB(x, y));
                int r = validar((int) ((255 * Math.log(1 + pixel.getRed())) / (Math.log(1 + 255))));
                int g = validar((int) ((255 * Math.log(1 + pixel.getGreen())) / (Math.log(1 + 255))));
                int b = validar((int) ((255 * Math.log(1 + pixel.getBlue())) / (Math.log(1 + 255))));
                // validamos 
                pixel = new Color(r, g, b);
                aux.setRGB(x, y, pixel.getRGB());
            }
        }
        return aux;
    }
    
    public static int[] obtenerMaximosMinimos (double hr[], double hg[], double hb[]){
        int[] maxsmins = new int[] {0,0,0, 0,0,0};
        //Obtener minimo rojo
        for (int x = hr.length - 1; x >= 0; x--) {
            if (hr[x] != 0) {
                maxsmins[0] = x;
            }
        }
        //Obtener minimo verde
        for (int x = hr.length - 1; x >= 0; x--) {
            if (hg[x] != 0) {
                maxsmins[1] = x;
            }
        }
        //Obtener minimo azul
        for (int x = hr.length - 1; x >= 0; x--) {
            if (hb[x] != 0) {
                maxsmins[2] = x;
            }
        }        
        //Obtener maximo rojo
        for (int x = 0; x < hr.length; x++) {
            if (hr[x] != 0) {
                maxsmins[3] = x;
            }
        }
        //Obtener maximo verde
        for (int x = 0; x < hg.length; x++) {
            if (hg[x] != 0) {
                maxsmins[4] = x;
            }
        }
        //Obtener maximo azul
        for (int x = 0; x < hb.length; x++) {
            if (hb[x] != 0) {
                maxsmins[5] = x;
            }
        }
//        for (int i=0; i< maxsmins.length; i++){
//            System.out.println(maxsmins[i]);
//        }
        //int[] hInt = DoubleStream.of(h).mapToInt(d -> (int) Math.ceil(d)).toArray();
        return maxsmins;
    }

    public static int obtenerMinimo(double h[]) {
        //Obtener de izquierda a derecha el primer tono del histograma en el que el valor sea diferente de 0
        for (int x = 0; x < h.length; x++) {
            if (h[x] != 0) {
                return x;
            }
        }
        return -1;
    }

    public static int obtenerMaximo(double h[]) {
        //Obtener de derecha a izquierda el primer tono del histograma en el que el valor sea diferente de 0
        for (int x = h.length - 1; x >= 0; x--) {
            if (h[x] != 0) {
                return x;
            }
        }
        return -1;
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
