package gui;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Eduardo
 */
public class JFrameImg extends JFrame{
    
    private JLabel lbImagen;

    public JFrameImg(Image imagen) {
        initComponentes(imagen);
    }

    private void initComponentes(Image imagen) {
        this.lbImagen = new JLabel();
        
        add(this.lbImagen);
        
        this.lbImagen.setIcon(new ImageIcon(imagen));
        setSize(imagen.getWidth(this),imagen.getHeight(this));
        
        setVisible(true);      
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
}
