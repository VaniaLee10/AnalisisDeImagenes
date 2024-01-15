/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frecuencias;

import java.awt.image.BufferedImage;
import herramientas.OperacionesBasicas;

public class GestorGrises {

    NumeroComplejo[][] transformada;

    // constructor por defecto
    public GestorGrises() {
    }

    //1. Calcular el espectro
    public NumeroComplejo[][] obtenerDatos(BufferedImage imagenOriginal) {
        NumeroComplejo[][] aux = new NumeroComplejo[imagenOriginal.getWidth()][imagenOriginal.getHeight()];
        // obtenemos los datos por canal
        for (int y = 0; y < imagenOriginal.getHeight(); y++) {
            for (int x = 0; x < imagenOriginal.getWidth(); x++) {
                aux[x][y] = new NumeroComplejo(HerramientasColor.obtenerValorGris(imagenOriginal.getRGB(x, y)), 0);
            }
        }
        return aux;
    }

    public BufferedImage obtenerImagenFrecuencias(NumeroComplejo[][] datosIO, int w, int h, boolean reAjustarCuadrante) {
        /// obtenemos las dimensiones
        int anchoImagen = w;
        int altoImagen = h;
        BufferedImage aux = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_ARGB);
        FFTCalculo fft = new FFTCalculo();
        // construir el mapeo de representacion en frecuencias utilizando FFT
        this.transformada = fft.calculateFT(datosIO, false);
        // crear la imagen del espectro 
        for (int y = 0; y < aux.getHeight(); y++) {
            for (int x = 0; x < aux.getWidth(); x++) {
                // modificamos la posicion de los cuadrantes 
                int ejeX = reAjustarCuadrante ? (x + (anchoImagen / 2)) % anchoImagen : x;
                int ejeY = reAjustarCuadrante ? (y + (altoImagen / 2)) % altoImagen : y;
                // setear la info a la imagen 
                // el que se ecuentre en la imagen 
                int color1 = aux.getRGB(x, y);
                int color2 = obtenerColorRealDeFrecuenciaGrises(ejeX, ejeY, transformada);
                //int rgb = HerramientasColor.acumularColor(color1, color2);
                aux.setRGB(x, y, color2);
            }
        }
        return aux;
    }

    //Aplicamos inversa  y obtenemos  imagen espacial
    public BufferedImage obtenerImagenEspacial() {
        /// obtenemos las dimensiones
        int anchoImagen = this.transformada.length;
        int altoImagen = this.transformada.length;
        BufferedImage aux = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_ARGB);
        FFTCalculo fft = new FFTCalculo();
        // construir el mapeo de representacion en frecuencias utilizando FFT
        NumeroComplejo[][] transformadaInv = fft.calculateFT(this.transformada, true);
        // crear la imagen del espectro 
        for (int y = 0; y < aux.getHeight(); y++) {
            for (int x = 0; x < aux.getWidth(); x++) {
                int color = (int) Math.abs(transformadaInv[x][y].getParteReal());
                color = OperacionesBasicas.validar(color);
                color = HerramientasColor.obtenerRGBdeGris(color);
                int rgb = HerramientasColor.acumularColor(aux.getRGB(x, y), color);
                aux.setRGB(x, y, rgb);
            }
        }
        return aux;
    }

    private int obtenerColorRealDeFrecuenciaGrises(int ejeX, int ejeY, NumeroComplejo[][] transformada) {
        int color = (int) Math.abs(transformada[ejeX][ejeY].getParteReal());
        color = OperacionesBasicas.validar(color);
        color = HerramientasColor.obtenerRGBdeGris(color);
        return color;
    }

    //2. Aplicamos filtro
    public void aplicarFiltro (NumeroComplejo[][] filtro){
        //Haremos el proceso iterativo donde aplicamos filtro a la matriz
        NumeroComplejo e = new NumeroComplejo(0, 0);
        for (int i = 0; i < 255; i++) {
            for (int j = 0; j < 255; j++) {
                this.transformada[i][j] = new NumeroComplejo(0, 0);
                for (int k = 0; k < 255; k++) {
                    NumeroComplejo aux = this.transformada[i][j].multiplicar(this.transformada[i][k], filtro[k][j]);
                    this.transformada[i][j] = this.transformada[i][j].sumar(aux);
                }
            }
        }        
    }
    
    public void aplicarFiltroClase (){
        NumeroComplejo[][] aux = new NumeroComplejo[256][256];
        for (int j=0; j<=255; j++){
            for (int b=0; b<=255; b++){
                //aux[j][b] = new NumeroComplejo(transformada[j][b]); 
                aux[j][b] = new NumeroComplejo(0,0); 
            }
        }
        for (int x=0; x<=25; x++){
            for (int y=0; y<=255; y++){
                //aux[x][y] = new NumeroComplejo(0,0); 
                aux[x][y] = new NumeroComplejo(transformada[x][y]); 
            }
        }
        for (int x=230; x<=255; x++){
            for (int y=0; y<=255; y++){
                //aux[x][y] = new NumeroComplejo(0,0); 
                aux[x][y] = new NumeroComplejo(transformada[x][y]); 
            }
        }
        for (int j=0; j<=25; j++){
            for (int b=50; b<=255; b++){
                //aux[j][b] = new NumeroComplejo(transformada[j][b]); 
                aux[j][b] = new NumeroComplejo(0,0); 
            }
        }
        
        this.transformada = aux;
    }
    
}
