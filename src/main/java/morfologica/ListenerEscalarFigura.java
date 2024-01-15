package morfologica;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class ListenerEscalarFigura implements ActionListener {
    JFrameMorfo frame;

    public ListenerEscalarFigura(JFrameMorfo frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtener el valor del JTextField
        String escalaTexto = this.frame.escala.getText();
        try {
            // Convertir el valor a double
            double escala = Double.parseDouble(escalaTexto);
            // Verificar que la escala sea válida y aplicarla
            if (escala > 0) {
                Image imagen = this.frame.imagenNueva;
                BufferedImage bimagen = herramientas.HerramientasImagen.toBufferedImage(imagen);
                this.frame.escalarImagen(bimagen, escala);
            } else {
                // Manejar caso de escala no válida
                JOptionPane.showMessageDialog(frame, "Por favor, introduzca una escala válida mayor que 0.", "Error de Escala", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // Manejar caso de entrada no numérica
            JOptionPane.showMessageDialog(frame, "Por favor, introduzca un valor numérico válido.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
