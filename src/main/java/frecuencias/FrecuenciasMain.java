package frecuencias;

import java.awt.Image;
import java.awt.image.BufferedImage;

import herramientas.OperacionesBasicas;
import gui.JFrameImg;
import herramientas.HerramientasImagen;

public class FrecuenciasMain {
    public static void main(String[] args) {
//        Image imagenOriginal = HerramientasImagen.abrirImagen();
//        imagenOriginal = imagenOriginal.getScaledInstance(256, 256, BufferedImage.TYPE_CUSTOM);
//        JFrameImg frame1 = new JFrameImg(imagenOriginal);
//        //Image grises = OperacionesBasicas.escalaDeGrises(imagenOriginal);
//        // mostramos la imagen original
//        //grises = grises.getScaledInstance(256, 256, BufferedImage.TYPE_CUSTOM);
//        Gestor gestor = new Gestor();
//        // obtenemos el buffer con el espectro de frecuencias
//        BufferedImage bImage = HerramientasImagen.toBufferedImage(imagenOriginal);
//        // convertimos a matriz de numeros complejos
//        NumeroComplejo[][] aux = gestor.obtenerDatosPorCanal(bImage, HerramientasColor.CanalColor.VERDE);
//        BufferedImage biFrecuencias = gestor.obtenerImagenFrecuencias(aux,bImage.getWidth(),bImage.getHeight(), true);
//        // mostramos la imagen original
//        JFrameImg frame2 = new JFrameImg(HerramientasImagen.toImage(biFrecuencias));
//        //Obtenemos la imagen resultante de aplicar la FTI
//        //Debe de ser el mismo gestor porque tiene la matriz de numeros complejos
//        BufferedImage resultante = gestor.obtenerImagenEspacial();
//        JFrameImg frame3 = new JFrameImg(HerramientasImagen.toImage(resultante));
//                
//        Image imagenOriginal1 = HerramientasImagen.abrirImagen();
//        imagenOriginal1 = imagenOriginal1.getScaledInstance(256, 256, BufferedImage.TYPE_CUSTOM);
//        JFrameImg frame3 = new JFrameImg(imagenOriginal1);
//        Gestor gestor1 = new Gestor();
//        BufferedImage bImage1 = HerramientasImagen.toBufferedImage(imagenOriginal1);
//        NumeroComplejo[][] aux1 = gestor1.obtenerDatosPorCanal(bImage1, HerramientasColor.CanalColor.VERDE);
//        BufferedImage biFrecuencias1 = gestor1.obtenerImagenFrecuencias(aux1,bImage1.getWidth(),bImage1.getHeight(), true);
//        JFrameImg frame4 = new JFrameImg(HerramientasImagen.toImage(biFrecuencias1));
//        
//        Image imagenOriginal2 = HerramientasImagen.abrirImagen();
//        JFrameImg frame5 = new JFrameImg(imagenOriginal2);
//        Gestor gestor2 = new Gestor();
//        BufferedImage bImage2 = HerramientasImagen.toBufferedImage(imagenOriginal2);
//        NumeroComplejo[][] aux2 = gestor2.obtenerDatosPorCanal(bImage2, HerramientasColor.CanalColor.VERDE);
//        BufferedImage biFrecuencias2 = gestor2.obtenerImagenFrecuencias(aux2,bImage2.getWidth(),bImage2.getHeight(), true);
//        JFrameImg frame6 = new JFrameImg(HerramientasImagen.toImage(biFrecuencias2));
//
//        Image imagenOriginal3 = HerramientasImagen.abrirImagen();
//        JFrameImg frame7 = new JFrameImg(imagenOriginal3);
//        Gestor gestor3 = new Gestor();
//        BufferedImage bImage3 = HerramientasImagen.toBufferedImage(imagenOriginal3);
//        NumeroComplejo[][] aux3 = gestor3.obtenerDatosPorCanal(bImage3, HerramientasColor.CanalColor.VERDE);
//        BufferedImage biFrecuencias3 = gestor3.obtenerImagenFrecuencias(aux3,bImage3.getWidth(),bImage3.getHeight(), true);
//        JFrameImg frame8 = new JFrameImg(HerramientasImagen.toImage(biFrecuencias3));


//        // obtenemos la imagen original
//        Image imagenOriginal = HerramientasImagen.abrirImagen();
//        // convertimos a grises
//        Image grises = OperacionesBasicas.escalaDeGrises(imagenOriginal);
//        JFrameImg frame1 = new JFrameImg(grises);
//        // Gestor para el calculo del espectro de las frecuencias
//        GestorGrises gestor = new GestorGrises();
//        // obtenemos el buffer para el calculo del espectro
//        BufferedImage bImage = HerramientasImagen.toBufferedImage(grises);
//        // convertimos a matriz de numeros complejos
//        NumeroComplejo[][] aux = gestor.obtenerDatos(bImage);
//        BufferedImage biFrecuencias = gestor.obtenerImagenFrecuencias(aux,bImage.getWidth(),bImage.getHeight(), true);
//        // mostramos la imagen de frecuencias
//        JFrameImg frame2 = new JFrameImg(HerramientasImagen.toImage(biFrecuencias));
//        // obtenemos la imagen resultante de aplicar la FFT inversa
//        BufferedImage resultante = gestor.obtenerImagenEspacial();
//        JFrameImg frame3 = new JFrameImg(HerramientasImagen.toImage(resultante));

        // obtenemos la imagen original
        Image imagenOriginal = HerramientasImagen.abrirImagen();
        // convertimos a grises
        Image grises = OperacionesBasicas.escalaDeGrises(imagenOriginal);
        JFrameImg frame1 = new JFrameImg(grises);
        BufferedImage bImage = HerramientasImagen.toBufferedImage(grises);
        //agregamos ruido
        Image grisesRuido = herramientas.Ruido.agregarRuidoAditivo(grises, 2);
        JFrameImg frame2 = new JFrameImg(grisesRuido);
        BufferedImage bImageRuido = HerramientasImagen.toBufferedImage(grisesRuido);
        
        GestorGrises gestor = new GestorGrises();        
        GestorGrises gestorRuido = new GestorGrises();   
        // Convertimos a matriz de numeros complejos Obtener espectro
        NumeroComplejo[][] aux = gestor.obtenerDatos(bImage);
        NumeroComplejo[][] auxRuido = gestorRuido.obtenerDatos(bImageRuido);
        // mostramos la imagen de frecuencias
        BufferedImage biFrecuencias = gestor.obtenerImagenFrecuencias(aux,bImage.getWidth(),bImage.getHeight(), true);
        JFrameImg frame3 = new JFrameImg(HerramientasImagen.toImage(biFrecuencias));
        BufferedImage biFrecuenciasRuido = gestorRuido.obtenerImagenFrecuencias(auxRuido,bImage.getWidth(),bImage.getHeight(), true);
        JFrameImg frame4 = new JFrameImg(HerramientasImagen.toImage(biFrecuenciasRuido));
        
        //Crear filtro
        /*NumeroComplejo[][] filtro = new NumeroComplejo[255][255];
        for (int i = 0; i < 255; i++) {
            for (int j = 0; j < 255; j++) {
                filtro[i][j] = new NumeroComplejo(1, 0);
            }
        }
        filtro[127][127] = new NumeroComplejo(0, 0);
        filtro[128][128] = new NumeroComplejo(0, 0);
        filtro[127][128] = new NumeroComplejo(0, 0);
        filtro[128][127] = new NumeroComplejo(0, 0);*/
        
        // Aplicar filtro
        gestorRuido.aplicarFiltroClase();
        //gestorRuido.aplicarFiltro(filtro);
        BufferedImage resultante = gestorRuido.obtenerImagenEspacial();
        JFrameImg frame5 = new JFrameImg(HerramientasImagen.toImage(resultante));
        auxRuido = gestorRuido.obtenerDatos(resultante);
        BufferedImage resultanteFrecuencias = gestorRuido.obtenerImagenFrecuencias(auxRuido, bImage.getWidth(),bImage.getHeight(), true);
        JFrameImg frame6 = new JFrameImg(HerramientasImagen.toImage(resultanteFrecuencias));
    }
}
