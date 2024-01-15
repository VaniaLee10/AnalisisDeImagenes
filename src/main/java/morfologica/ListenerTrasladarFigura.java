package morfologica;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class ListenerTrasladarFigura implements ActionListener {
    JFrameMorfo frame;
    
    public ListenerTrasladarFigura(JFrameMorfo frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cord_x = this.frame.cordx.getText();
        String cord_y = this.frame.cordy.getText();        
        try {
            // Convertir el valor a int
            int x_new = Integer.parseInt(cord_x);
            int y_new = Integer.parseInt(cord_y);
            // Verificar que la escala sea válida y aplicarla
            if ((x_new > 0) && (y_new > 0) && 
                    (x_new < this.frame.lienzo.getHeight()) && (y_new < this.frame.lienzo.getWidth())) {
                Image imagen = this.frame.imagenNueva;
                BufferedImage bimagen = herramientas.HerramientasImagen.toBufferedImage(imagen);
                this.frame.trasladarImagen(bimagen, x_new, y_new);
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, introduzca coordenadas válida.", "Error de Coordenada", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, introduzca un valor numérico válido.", "Error de Coordenada", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
