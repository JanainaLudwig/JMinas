package darth.jminas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class JMinasMain extends JFrame implements ActionListener {
    private static final long serialVersionUID = 5263848303195260404L;
    
    public static boolean flagErrorImagenes = false;
    
    private JMenuBar mb;
    private JMenu mOpciones, mMas, mNivel;
    private JMenuItem moNuevo,moSalir,moEstadisticas,mAcerca,smn0,smn1,smn2,smn3;
    
    static PanelSuperior ps;
    static PanelCentral pc;
    static PanelInferior pi;
    
    private static Cronometro c;
    
    private static boolean playing = false;
    public static boolean Ganador = false;
    public static boolean Perdedor = false;
    
    public JMinasMain() {
        setTitle("JMinas");
        setSize(300, 396);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        createMenu();
        initComponents();
        addComponents();
        addKeyListener(pc);
        
        setIconImage(new ImageIcon(getClass().getResource(Variables.pathIcoMinas)).getImage());
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            checkErrors();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }
    
    private void checkErrors() throws FileNotFoundException {
        if(flagErrorImagenes) {
            PrintWriter pw = new PrintWriter(new File("JMinas_errorlog.txt"));
            pw.println("******************************************************************************");
            pw.println("**                            REVISANDO IMAGENES                            **");
            pw.println("** Si no encuentra algun archivo, revisa que exista o que este bien escrito **");
            pw.println("******************************************************************************\n");
            File f = new File(getClass().getClassLoader().getResource("").getFile() + "img");
            
            if(f.exists()) {
                File[] file = f.listFiles();
                boolean flag = false;
                int cont = 0;
                for (int i = 0; i < Variables.archivos.length; i++) {
                    flag = false;
                    for (int j = 0; j < file.length; j++) {
                        if(Variables.archivos[i].equals(file[j].getName())) {
                            flag = true;
                            break;
                        }
                    }
                    if(flag)
                        continue;
                    pw.println(++cont + ": No se encuentra el archivo \""+ Variables.archivos[i] +"\"");
                }
            }else
                pw.println("La carpeta de imagenes (\"img\") no se encuentra");
            
            pw.close();
            JOptionPane.showMessageDialog(null,
                    "No se pudieron cargar algunas imagenes,\n" +
                            "se creo el archivo JMinas_errorlog.txt con\n" +
                            "informacion al respecto",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void initComponents() {
        ps = new PanelSuperior();
        pc = new PanelCentral(this);
        pi = new PanelInferior();
    }
    
    private void addComponents() {
        add(ps, BorderLayout.NORTH);
        add(pc, BorderLayout.CENTER);
        add(pi, BorderLayout.SOUTH);
    }
    
    private void createMenu() {
        mb=new JMenuBar();
        setJMenuBar(mb);
        
        mOpciones = new JMenu("Opciones");
        mb.add(mOpciones);
        moNuevo = new JMenuItem("Nuevo");
        moNuevo.addActionListener(this);
        mOpciones.add(moNuevo);
        mOpciones.add(new JSeparator());
        mNivel = new JMenu("Nivel");
        {
            smn0 = new JMenuItem("Nivel 0");
            smn0.addActionListener(this);
            mNivel.add(smn0);
            smn1 = new JMenuItem("Principiante");
            smn1.addActionListener(this);
            mNivel.add(smn1);
            smn2 = new JMenuItem("Avanzado");
            smn2.addActionListener(this);
            mNivel.add(smn2);
            smn3 = new JMenuItem("Experto");
            smn3.addActionListener(this);
            mNivel.add(smn3);
        }
        mOpciones.add(mNivel);
        moEstadisticas = new JMenuItem("Estadisticas");
        mOpciones.add(moEstadisticas);
        mOpciones.add(new JSeparator());
        moSalir = new JMenuItem("Salir");
        moSalir.addActionListener(this);
        mOpciones.add(moSalir);
        
        mMas = new JMenu("Mas");
        mb.add(mMas);
        mAcerca = new JMenuItem("Acerca de");
        mAcerca.addActionListener(this);
        mMas.add(mAcerca);
    }
    
    public static void StartGame() {
        c = new Cronometro();
        c.start();
        playing = true;
        Ganador = false;
        Perdedor = false;
    }
    
    public static void RestartGame() {
        pc.restart();
        ps.restart();
        if(c != null)
            c.setActivo(false);
        playing = false;
        Ganador = false;
        Perdedor = false;
    }
    
    public static void LostGame() {
        c.setActivo(false);
        pc.Perdio();
        playing = false;
        Ganador = false;
        Perdedor = true;
        new Sonido(Variables.SonidoExplosion).start();
    }
    
    public static void WinGame() {
        StopChron();
        pc.Gano();
        Ganador = true;
        Perdedor = false;
        new Sonido(Variables.SonidoGanador).start();
    }
    
    public static void StopChron() {
        c.setActivo(false);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==moNuevo)
            RestartGame();
        else if(e.getSource()==moSalir)
            System.exit(0);
        else if(e.getSource()==smn0) {
            Variables.setNivel(0);
            setSize(300, 395);
            setLocationRelativeTo(null);
            RestartGame();
        }else if(e.getSource()==smn1) {
            Variables.setNivel(1);
            setSize(300, 395);
            setLocationRelativeTo(null);
            RestartGame();
        }else if(e.getSource()==smn2) {
            Variables.setNivel(2);
            setSize(468, 500);
            setLocationRelativeTo(null);
            RestartGame();
        }else if(e.getSource()==smn3) {
            Variables.setNivel(3);
            setSize(842, 548);
            setLocationRelativeTo(null);
            RestartGame();
        }else if(e.getSource()==mAcerca)
            try {
                Acerca();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
    }
    
    public static boolean isPlaying() {
        return playing;
    }
    
    private void Acerca() throws InterruptedException {
        final JFrame frame = new JFrame("Frame test");
        frame.setUndecorated(true);
        frame.setBackground(new Color(0,0,0,0));
        frame.setContentPane(new Component());
        frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        frame.pack();
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Thread t = new Thread(){
            public void run() {
                try {
                    Thread.sleep(3000);
                    frame.dispose();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
    
    private static class Component extends JComponent {
        private static final long serialVersionUID = 8668944310336850671L;
        public void paintComponent(Graphics graphics) {
            Graphics2D g = (Graphics2D) graphics;
            Color[] colors = new Color[]{
                new Color(0,0,0,0),
                new Color(0.3f,0.3f,0.3f,1f),
                new Color(0.3f,0.3f,0.3f,1f),
                new Color(0,0,0,0)};
            float[] stops = new float[]{0,0.2f,0.8f,1f};
            LinearGradientPaint paint = new LinearGradientPaint(
                    new Point(0,0),
                    new Point(500,0),
                    stops,colors);
            g.setPaint(paint);
            g.fillRect(0, 0, 500, 200);
            g.setPaint(Color.WHITE);
            g.drawString("Darth Leonard", 200, 90);
            g.drawString("leolinuxmx@gmail.com", 185, 110);
        }
    }
    
    static class Sonido extends Thread {
        String str;
        public Sonido(String str) {
            this.str = str;
        }
        
        public void run() {
            try {
                final Clip sonido = AudioSystem.getClip();
                URL pathBoom = getClass().getResource(str);
                sonido.open(AudioSystem.getAudioInputStream(pathBoom));
                sonido.start();
                sonido.loop(2);
                while(sonido.getMicrosecondPosition() <= sonido.getMicrosecondLength());
                sonido.stop();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException | NullPointerException e) {
                errorReporter("Error message: " + e.getMessage() + "\n" + e.getLocalizedMessage());
                if(Perdedor)
                    JOptionPane.showMessageDialog(null, "¡BOO000000M!", "Error con el archivo de audio", JOptionPane.ERROR_MESSAGE);
                else if(Ganador)
                    JOptionPane.showMessageDialog(null, "¡HAS GANADO!", "Error con el archivo de audio", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private static void errorReporter(String message) {
        try {
            File f = new File("JMinas_errorlog.txt");
            PrintWriter pw = new PrintWriter(f);
            String str = "Error en el archivo de audio:\n" + message;
            if(f.exists()) {
                pw.append(str);
            } else {
                pw.println(str);
            }
            pw.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        new JMinasMain();
    }
}
