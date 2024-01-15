/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Binarizacion {

    public static Image binarizarImagen(Image imagen, int j) {
        imagen = OperacionesBasicas.escalaDeGrises(imagen);
        BufferedImage bi = HerramientasImagen.toBufferedImage(imagen);

        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int rgb = bi.getRGB(x, y);
                Color color = new Color(rgb);
                if (color.getRed() < j) {
                    // mandamos el rgb del pixel a negro
                    color = new Color(0, 0, 0);
                } else {
                    // mandamos el rgb del pixel a blanco
                    color = new Color(255, 255, 255);
                }
                bi.setRGB(x, y, color.getRGB());
            }
        }
        Image imagenBi = HerramientasImagen.toImage(bi);
        return bi;
    }

    private static int validar(int color) {
        if (color >= 255) {
            return 255;
        }
        if (color < 0) {
            return 0;
        }
        return color;
    }

    public static Image binarizarImagen(Image imagen, int j1, int j2) {
        imagen = OperacionesBasicas.escalaDeGrises(imagen);
        BufferedImage bi = HerramientasImagen.toBufferedImage(imagen);

        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int rgb = bi.getRGB(x, y);
                Color color = new Color(rgb);
                if (color.getRed() > j1 && color.getRed() < j2) {
                    // mandamos el rgb del pixel a negro
                    color = new Color(255, 255, 255);
                } else {
                    // mandamos el rgb del pixel a blanco
                    color = new Color(0, 0, 0);
                }
                bi.setRGB(x, y, color.getRGB());
            }
        }
        Image imagenBi = HerramientasImagen.toImage(bi);
        return bi;
    }
    
    public static Image binarizarImagen(Image imagen, double[]histograma) {
        imagen = OperacionesBasicas.escalaDeGrises(imagen);
        BufferedImage bi = HerramientasImagen.toBufferedImage(imagen);
        
        int j = UmbralAutomatico.metodoIterativo(histograma);

        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int rgb = bi.getRGB(x, y);
                Color color = new Color(rgb);
                if (color.getRed() < j) {
                    // mandamos el rgb del pixel a negro
                    color = new Color(0, 0, 0);
                } else {
                    // mandamos el rgb del pixel a blanco
                    color = new Color(255, 255, 255);
                }
                bi.setRGB(x, y, color.getRGB());
            }
        }
        Image imagenBi = HerramientasImagen.toImage(bi);
        return bi;
    }
    
    
    //public static 

}
