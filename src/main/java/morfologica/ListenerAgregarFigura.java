package morfologica;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ListenerAgregarFigura implements ActionListener{
    JFrameMorfo frame;

    public ListenerAgregarFigura(JFrameMorfo frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Image imagen = herramientas.HerramientasImagen.abrirImagen();
        BufferedImage nuevo = herramientas.HerramientasImagen.toBufferedImage(imagen);
        //BufferedImage nuevo = new BufferedImage(950, 750, BufferedImage.TYPE_INT_RGB);
        //this.frame.actualizarLienzo(nuevo);
        this.frame.x = 10;
        this.frame.y = 10;
        this.frame.agregarImagenALienzo(nuevo); // Coordenadas de posición
    }
}
