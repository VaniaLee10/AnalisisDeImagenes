package analisisdeimagenes;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel {

    public void paint(Graphics g) {
        Image img = createImageWithText();
        g.drawImage(img, 20, 20, this);
    }

    private Image createImageWithText() {
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();

        g.drawString("www.tutorialspoint.com", 20, 20);
        g.drawString("www.tutorialspoint.com", 20, 40);
        g.drawString("www.tutorialspoint.com", 20, 60);
        g.drawString("www.tutorialspoint.com", 20, 80);
        g.drawString("www.tutorialspoint.com", 20, 100);

        return bufferedImage;
    }

    private Image dibujarFigura() {
        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        //Graphics g = bufferedImage.getGraphics();
        Color p = new Color (0,0,255);
        //La clase color usa 8 bits por color 
        
        Color color4 = new Color(0,0,255);
        int aux = color4.getRGB();
        System.out.println(aux);
        Color color3 = new Color(255,0,0);
        int aux1 = color3.getRGB();
        System.out.println(aux1);
        Color color2 = new Color(0,255,0);
        int aux2 = color2.getRGB();
        System.out.println(aux2);
        Color color1 = new Color(0,0,0); 
        int aux3 = color1.getRGB();
        System.out.println(aux3);
        
        for(int x=10; x<=190; x++){
            for(int y=10; y<=190; y++){
                //bufferedImage.setRGB(x, y, color1.getRGB());
                if (x <= 50 || x >= 190 - 40 || y <= 50 || y >= 190 - 40) 
                    bufferedImage.setRGB(x, y, color4.getRGB());
                if (x <= 40 || x >= 190 - 30 || y <= 40 || y >= 190 - 30) 
                    bufferedImage.setRGB(x, y, color3.getRGB());
                if (x <= 30 || x >= 190 - 20 || y <= 30 || y >= 190 - 20) 
                    bufferedImage.setRGB(x, y, color2.getRGB());
                if (x <= 20 || x >= 190 - 10 || y <= 20 || y >= 190 - 10) 
                    bufferedImage.setRGB(x, y, color1.getRGB());
            }
        }
        
        return bufferedImage;
    }

    public static void main(String[] args) {        
        JFrame frame = new JFrame();
        frame.getContentPane().add(new Test());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}
