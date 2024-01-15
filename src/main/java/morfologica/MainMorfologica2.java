/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morfologica;

 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
/**
*
* @author Eduardo
*/
public class MainMorfologica2 extends JPanel{
 
    private final Image imagen;
 
    public MainMorfologica2(Image imagen) {
        this.imagen = imagen;
    }
 
    @Override
    public void paint(Graphics g) {
        Image img = pintar(this.imagen);
        g.drawImage(img, 0, 0, this);
    }

    private Image fondo() {
        int width = 1600;
        int height = 800;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        Color p = new Color (240,240,255);
        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                bufferedImage.setRGB(x, y, p.getRGB());
            }
        }
        return bufferedImage;
    }
    
    private Image agregarImagen(Image fondo, Image imagen, int x, int y){
        BufferedImage nueva = herramientas.HerramientasImagen.toBufferedImage(fondo);
        Graphics g = nueva.getGraphics();
        BufferedImage bimagen = herramientas.HerramientasImagen.toBufferedImage(imagen);
        g.drawImage(imagen, x, y, this);
        return nueva;
    }
 
    public Image pintar(Image imagen){
        Image fondoimg = fondo();
        Image completa = agregarImagen(fondoimg, imagen, 100, 100);
        return completa;
    }
    
    public static void main(String[] args) {
        Image imagen = herramientas.HerramientasImagen.abrirImagen();
        JFrame frame = new JFrame();
        frame.getContentPane().add(new MainMorfologica2(imagen));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1800, 900);
        frame.setVisible(true);
    }
    
}
