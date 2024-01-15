package morfologica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JFrameMorfo extends JFrame {

    JPanel lienzo;
    JLabel etiqueta;
    JButton btnAgregarFigura;
    JButton btnTrasladar;
    JButton btnRotar;
    JButton btnEscalar;

    BufferedImage fondo;
    BufferedImage imagenNueva;
    int x, y;
    JTextField cordx;
    JTextField cordy;
    JTextField grados;
    JTextField escala;
    

    public JFrameMorfo(Image imagenOriginal) {
        //Agregar directamente al canvas
        inicializarCanvas();
    }

    private void inicializarCanvas() {
        setSize(1500, 800);
        setLayout(new BorderLayout());
        //En la parte central un panel
        this.lienzo = new JPanel();

        // Establecer la imagen de fondo
        //this.lienzo.setBackground(Color.WHITE);
        //Imagen
//        JLabel fondoLabel = new JLabel(new ImageIcon(fondo));
//        lienzo.add(fondoLabel);
        this.fondo = new BufferedImage(1400, 700, BufferedImage.TYPE_INT_RGB);
        this.etiqueta = new JLabel();
        this.etiqueta.setText("");
        this.etiqueta.setIcon(new ImageIcon(herramientas.HerramientasImagen.toImage(fondo)));
        this.lienzo.add(etiqueta);

        //Panel de controles
        JPanel controles = new JPanel();
        controles.setLayout(new GridLayout(3, 4));
        //Agregar Imagen
        this.btnAgregarFigura = new JButton("Agregar Figura");
        this.btnAgregarFigura.addActionListener(new ListenerAgregarFigura(this));
        //Trasladar Imagen
        this.btnTrasladar = new JButton("Trasladar");
        this.cordx = new JTextField("0");
        this.cordy = new JTextField("0");
        this.btnTrasladar.addActionListener(new ListenerTrasladarFigura(this));
        //Rotar Imagen
        this.btnRotar = new JButton("Rotar");
        this.grados = new JTextField("0");
        this.btnRotar.addActionListener(new ListenerRotarFigura(this));
        //Rotar Imagen
        this.btnEscalar = new JButton("Escalar");
        this.escala = new JTextField("1");
        this.btnEscalar.addActionListener(new ListenerEscalarFigura(this));
        //Panel de controles (botones y texto)
        controles.add(btnAgregarFigura);
        controles.add(btnTrasladar);
        controles.add(btnRotar);
        controles.add(btnEscalar);
        controles.add(new JLabel("Cordenada X"));
        controles.add(new JLabel("Cordenada Y:"));
        controles.add(new JLabel("Grados:")); 
        controles.add(new JLabel("Escala:"));
        controles.add(cordx);
        controles.add(cordy);
        controles.add(grados);
        controles.add(escala);

        add(lienzo, BorderLayout.CENTER);
        add(controles, BorderLayout.SOUTH);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actualizarLienzo(Image imagenNueva) {
//        this.lienzo.repaint();
//        this.etiqueta = new JLabel("Nueva");
//        this.lienzo.removeAll();
//        this.etiqueta.setIcon(new ImageIcon(imagenNueva));
//        this.lienzo.add(this.etiqueta);
        this.etiqueta.setIcon(new ImageIcon(imagenNueva));
        revalidate();
        repaint();
    }

    public void agregarImagenALienzo(Image imagen) {
//        JLabel nuevaImagen = new JLabel(new ImageIcon(imagen));
//        nuevaImagen.setBounds(x, y, imagen.getWidth(null), imagen.getHeight(null));
//        lienzo.add(nuevaImagen);
//        nuevaImagen.revalidate();
        this.cordx.setText(x+"");
        this.cordy.setText(y+"");
        this.imagenNueva = herramientas.HerramientasImagen.toBufferedImage(imagen);
        Graphics g = this.fondo.getGraphics();
        g.drawImage(this.imagenNueva, this.x, this.y, null);
        g.dispose();
        actualizarLienzo(this.fondo);
    }

    public void escalarImagen(Image imagen, double escala) {
        if (escala <= 0) {
            return;
        }
        // Escalar la imagen pequeña
        int nuevaAnchura = (int) (imagen.getWidth(null) * escala);
        int nuevaAltura = (int) (imagen.getHeight(null) * escala);
        imagen = imagen.getScaledInstance(nuevaAnchura, nuevaAltura, BufferedImage.TYPE_CUSTOM);
        this.fondo = new BufferedImage(1400, 700, BufferedImage.TYPE_INT_RGB);
        agregarImagenALienzo(imagen);
    }
    
    public void trasladarImagen(Image imagen, int x_new, int y_new) {
        this.fondo = new BufferedImage(1400, 700, BufferedImage.TYPE_INT_RGB);
        this.x = x_new;
        this.y = y_new;
        this.cordx.setText(this.x+"");
        this.cordy.setText(this.y+"");
        this.imagenNueva = herramientas.HerramientasImagen.toBufferedImage(imagen);
        Graphics g = this.fondo.getGraphics();
        g.drawImage(this.imagenNueva, this.x, this.y, null);
        g.dispose();
        actualizarLienzo(this.fondo);
    }
    
    public void rotarImagen(Image imagen, int grados) {
        // Convertir la imagen a BufferedImage
        BufferedImage imagenOriginal = herramientas.HerramientasImagen.toBufferedImage(imagen);
        // Crear una instancia de AffineTransform, se define los grados en radianes y se encuentra el centro de la imagen
        AffineTransform rotacion = AffineTransform.getRotateInstance(Math.toRadians(grados), 
                                                              imagenOriginal.getWidth()/2.0, imagenOriginal.getHeight()/2.0);
        // Crear una imagen rotada
        BufferedImage imagenRotada = new BufferedImage(imagenOriginal.getWidth(), imagenOriginal.getHeight(), BufferedImage.TYPE_INT_ARGB);
        // Obtener el contexto gráfico y aplicar la transformación
        Graphics2D g2d = imagenRotada.createGraphics();
        g2d.setTransform(rotacion);
        g2d.drawImage(imagenOriginal, 0, 0, null);
        g2d.dispose();
        // Llamar a la función que agrega la imagen al lienzo
        agregarImagenALienzo(imagenRotada);
    }

}
