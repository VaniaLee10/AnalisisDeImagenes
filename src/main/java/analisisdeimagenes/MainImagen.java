/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisdeimagenes;

import gui.Graficador;
import herramientas.HerramientasImagen;
import java.awt.Image;
import gui.JFrameImg;
import herramientas.Convolucion;
import java.awt.image.BufferedImage;
import java.util.Scanner;
/**
 *
 * @author Eduardo
 */
public class MainImagen {

    public static void main(String[] args) {
        //HerramientasImagen hi = new HerramientasImagen();
        //hi.abrirImagen();

//        double arreglo[] = new double[]{3.4, 4.5, 8.6, 12.1, 32};
//        Graficador g = new Graficador("Eje X", "Eje Y", "Grafica 1");
//        g.agregarSerie("Serie 1", arreglo);
//        g.crearGrafica();
//        g.muestraGrafica();
        
        Image imagen = herramientas.HerramientasImagen.abrirImagen();
        imagen = imagen.getScaledInstance(550, 400, BufferedImage.TYPE_CUSTOM);
        JFrameImg frame = new JFrameImg(imagen);
//        herramientas.HerramientasImagen.calcularHistograma(imagen);
        
//        Image escalaGrises = herramientas.OperacionesBasicas.escalaDeGrises(imagen);
//        JFrameImg frameGrises = new JFrameImg(escalaGrises);
//        herramientas.HerramientasImagen.calcularHistograma(escalaGrises);
        
//        Image imageniluminada = herramientas.OperacionesBasicas.modificarIluminacion(imagen, 100);
//        JFrameImg frameiluminada = new JFrameImg(imageniluminada);
//        herramientas.HerramientasImagen.calcularHistograma(imageniluminada);
        
//        Image imagenCaliente = herramientas.OperacionesBasicas.calentarImagen(imagen, 20);
//        JFrameImg frameCaliente = new JFrameImg(imagenCaliente);
//        herramientas.HerramientasImagen.calcularHistograma(imagenCaliente);
        
//        Image imagenFria = herramientas.OperacionesBasicas.enfriarImagen(imagen, 20);
//        JFrameImg frameFria = new JFrameImg(imagenFria);
//        herramientas.HerramientasImagen.calcularHistograma(imagenFria);
        
//        Image binaria = herramientas.Binarizacion.binarizarImagen(imagen, 120);
//        JFrameImg frameBinaria = new JFrameImg(binaria);
//        herramientas.HerramientasImagen.calcularHistograma(binaria);
        
//        Image binaria2 = herramientas.Binarizacion.binarizarImagen(imagen, 120, 250);
//        JFrameImg frameBinaria2 = new JFrameImg(binaria2);
//        herramientas.HerramientasImagen.calcularHistograma(binaria2);
        
//        Image negativa = herramientas.OperacionesBasicas.negativoImagen(imagen);
//        JFrameImg frameNegativa = new JFrameImg(negativa);
//        herramientas.HerramientasImagen.calcularHistograma(negativa);

//        Image escalaGrises = herramientas.OperacionesBasicas.escalaDeGrises(imagen);
//        double[] histograma = herramientas.HerramientasImagen.calcularHistogramaReturn(escalaGrises);
//        Image binariaAutomatica = herramientas.Binarizacion.binarizarImagen(escalaGrises, histograma);
//        JFrameImg frameBinaria = new JFrameImg(binariaAutomatica);

//        Image escalaGrises = herramientas.OperacionesBasicas.escalaDeGrises(imagen);
//        JFrameImg frameGrises = new JFrameImg(escalaGrises);
//        double[] histograma = herramientas.HerramientasImagen.calcularHistogramaReturn(escalaGrises);
////        double[] histograma = herramientas.HerramientasImagen.calcularHistogramaReturn(imagen);
//        int r1 = herramientas.Expansion.obtenerMinimo(histograma);
//        int r2 = herramientas.Expansion.obtenerMaximo(histograma);
//        Image expansionLineal = herramientas.Expansion.expansionLineal(r1,r2, imagen);
//        JFrameImg frameExpansionLineal1 = new JFrameImg(expansionLineal);
//        herramientas.HerramientasImagen.calcularHistograma(expansionLineal);

//        double[] hrojo = herramientas.HerramientasImagen.calcularHistogramaRojo(imagen);
//        double[] hverde = herramientas.HerramientasImagen.calcularHistogramaVerde(imagen);
//        double[] hazul = herramientas.HerramientasImagen.calcularHistogramaAzul(imagen);
//        int rr1 = herramientas.Expansion.obtenerMinimo(hrojo);
//        int rr2 = herramientas.Expansion.obtenerMaximo(hrojo);
//        int rg1 = herramientas.Expansion.obtenerMinimo(hverde);
//        int rg2 = herramientas.Expansion.obtenerMaximo(hverde);
//        int rb1 = herramientas.Expansion.obtenerMinimo(hazul);
//        int rb2 = herramientas.Expansion.obtenerMaximo(hazul);
//        Image expansionLinealColor = herramientas.Expansion.expansionLineal(rr1, rr2, rg1, rg2, rb1, rb2, imagen);
//        JFrameImg frameExpansionLinealColor = new JFrameImg(expansionLinealColor);
//        herramientas.HerramientasImagen.calcularHistograma(expansionLinealColor);

//        Image oscura = herramientas.OperacionesBasicas.modificarIluminacion(imagen, -150);
//        JFrameImg frameGrisesOscura = new JFrameImg(oscura);
//        herramientas.HerramientasImagen.calcularHistograma(oscura);
        //int[] rs = new int[]{15,20,5,245,250,235}; 
//        herramientas.HerramientasImagen.calcularHistograma(imagen);
//        double[] hrojo = herramientas.HerramientasImagen.calcularHistogramaRojo(imagen);
//        double[] hverde = herramientas.HerramientasImagen.calcularHistogramaVerde(imagen);
//        double[] hazul = herramientas.HerramientasImagen.calcularHistogramaAzul(imagen);
//        int[] rs = herramientas.Expansion.obtenerMaximosMinimos(hrojo, hverde, hazul);
//        Image expansionColor = herramientas.Expansion.expansionLinealColores(rs, imagen);
//        JFrameImg frameExpansion = new JFrameImg(expansionColor);
//        herramientas.HerramientasImagen.calcularHistograma(expansionColor);

//        Scanner input = new Scanner(System.in);
//        System.out.println("Ingrese el minimo parametro del canal rojo: ");
//        int hr1 = input.nextInt(); 
//        System.out.println("Ingrese el minimo parametro del canal verde: ");
//        int hg1 = input.nextInt();  
//        System.out.println("Ingrese el minimo parametro del canal azul: ");
//        int hb1 = input.nextInt(); 
//        System.out.println("Ingrese el máximo parametro del canal rojo: ");
//        int hr2 = input.nextInt();  
//        System.out.println("Ingrese el máximo parametro del canal verde: ");
//        int hg2 = input.nextInt(); 
//        System.out.println("Ingrese el máximo parametro del canal azul: ");
//        int hb2 = input.nextInt();
//        int[] rs1 = new int[]{hr1, hg1, hb1, hr2, hg2, hb2};
//        input.close();
//        Image imagen = herramientas.HerramientasImagen.abrirImagen();
//        JFrameImg frame = new JFrameImg(imagen);
//        herramientas.HerramientasImagen.calcularHistograma(imagen);
//        Image expansionColor2 = herramientas.Expansion.expansionLinealColores(rs1, imagen);
//        JFrameImg frameExpansion2 = new JFrameImg(expansionColor2);
//        herramientas.HerramientasImagen.calcularHistograma(expansionColor2);

//        Image expansionLn = herramientas.Expansion.expansionLn(imagen);
//        JFrameImg frameExpansionLn = new JFrameImg(expansionLn);
//        herramientas.HerramientasImagen.calcularHistograma(expansionLn);

//        BufferedImage buffered = herramientas.HerramientasImagen.toBufferedImage(imagen);
//        Convolucion convo = new Convolucion(buffered);
//        double div = 10;
//        int[] mascara = {-3,0,3,-10,0,10,-3,0,3};
//        Image convolucionada = convo.convolucionar(mascara, div);
//        JFrameImg frameExpansionLn = new JFrameImg(convolucionada);
//        int[] mascara2 = {-3,-10,-3,0,0,0,3,10,3};
//        BufferedImage buffered2 = herramientas.HerramientasImagen.toBufferedImage(convolucionada);
//        Convolucion convo2 = new Convolucion(buffered2);
//        Image convolucionada2 = convo.convolucionar(mascara2, div);
//        JFrameImg frameExpansionLn2 = new JFrameImg(convolucionada2);

        Image ruido = herramientas.Ruido.agregarRuidoAditivo(imagen, 0.5);
        JFrameImg frameRuido= new JFrameImg(ruido);
        BufferedImage buffered = herramientas.HerramientasImagen.toBufferedImage(ruido);
        
        Image ruido2 = herramientas.Ruido.agregarRuidoSustractivo(imagen, 0.5);
        JFrameImg frameRuido2 = new JFrameImg(ruido2);
        BufferedImage buffered2 = herramientas.HerramientasImagen.toBufferedImage(ruido2);
        
        Image ruido3 = herramientas.Ruido.agregarRuidoSalYPimienta(imagen, 0.5);
        JFrameImg frameRuido3 = new JFrameImg(ruido3);
        BufferedImage buffered3 = herramientas.HerramientasImagen.toBufferedImage(ruido3);
        
//        Convolucion convo1 = new Convolucion(buffered);
//        int[] mascara2 = {1,1,1,1,0,1,1,1,1};
//        Image eliminacionRuido = convo1.convolucionar(mascara2, 8);
//        JFrameImg frameExpansionLnn = new JFrameImg(eliminacionRuido);
        
        double div = 25;
        int[] mascara = {1,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1};
        Convolucion convo = new Convolucion(buffered);
        Image convolucionada = convo.convolucionar5x5(mascara, div);
        JFrameImg frameExpansionLn = new JFrameImg(convolucionada);
        
        Convolucion convo2 = new Convolucion(buffered2);
        Image convolucionada2 = convo2.convolucionar5x5(mascara, div);
        JFrameImg frameExpansionLn2 = new JFrameImg(convolucionada2);
        
        Convolucion convo3 = new Convolucion(buffered3);
        Image convolucionada3 = convo3.convolucionar5x5(mascara, div);
        JFrameImg frameExpansionLn3 = new JFrameImg(convolucionada3);
        
    }
    
}
