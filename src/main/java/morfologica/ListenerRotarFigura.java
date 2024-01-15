package morfologica;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class ListenerRotarFigura implements ActionListener {
    JFrameMorfo frame;

    public ListenerRotarFigura(JFrameMorfo frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtener el valor del JTextField
        String gradosRot = this.frame.grados.getText();
        try {
            // Convertir el valor a double
            int grados = Integer.parseInt(gradosRot);
            // Verificar que la escala sea válida y aplicarla
            if ((grados >= 0) && (grados <= 365)) {
                Image imagen = this.frame.imagenNueva;
                BufferedImage bimagen = herramientas.HerramientasImagen.toBufferedImage(imagen);
                this.frame.rotarImagen(bimagen, grados);
            } else {
                // Manejar caso de escala no válida
                JOptionPane.showMessageDialog(frame, "Por favor, introduzca un grado válido mayor que 0° y menor que 365°.", "Error de Escala", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // Manejar caso de entrada no numérica
            JOptionPane.showMessageDialog(frame, "Por favor, introduzca un valor numérico válido.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
