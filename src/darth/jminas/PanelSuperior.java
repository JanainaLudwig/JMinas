package darth.jminas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSuperior extends JPanel {
    private static final long serialVersionUID = 2473191468363778297L;

    private static JLabel lblTime, lblMinas, lblStart;
    private JPanel pCronometro, pMinas;
    private static Color countersTextColor = new Color(3,155,229);

    public PanelSuperior() {
        setBorder(BorderFactory.createRaisedBevelBorder());
        setLayout(new GridLayout(1,5,3,3));

        initComponents();
        addComponents();
    }

    private void initComponents() {
        pCronometro = new JPanel();
        pCronometro.setBackground(Color.black);
        pCronometro.setLayout(new BorderLayout());
        pMinas = new JPanel();
        pMinas.setBackground(Color.black);
        pMinas.setLayout(new BorderLayout());

        lblTime = new JLabel("00:00", JLabel.CENTER);
        lblTime.setBackground(new Color(55,71,79));
        lblTime.setForeground(countersTextColor);
        lblTime.setFont(new Font("Verdana", Font.BOLD, 14));
        pCronometro.add(lblTime, BorderLayout.CENTER);

        lblMinas = new JLabel("00", JLabel.CENTER);
        lblMinas.setBackground(new Color(55,71,79));
        lblMinas.setForeground(countersTextColor);
        lblMinas.setFont(new Font("Verdana", Font.BOLD, 14));
        pMinas.add(lblMinas, BorderLayout.CENTER);

        lblStart = new JLabel("", JLabel.CENTER);
        lblStart.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
                if(lblStart.getIcon() != null)
                    lblStart.setIcon(icon);
            }
            public void mouseEntered(MouseEvent e) {
                if(lblStart.getIcon() != null) {
                    icon = lblStart.getIcon();
                    lblStart.setIcon(new ImageIcon(getClass().getResource(Variables.pathRiendo)));
                }
            }

            public void mouseClicked(MouseEvent e) {
                JMinasMain.RestartGame();
            }
        });
    }

    private static Icon icon;

    private void addComponents() {
        try {
            URL url = this.getClass().getResource(Variables.pathIcoCronometro);
            ImageIcon icon = new ImageIcon(url);
            add(new JLabel(icon, JLabel.RIGHT));
        }catch(NullPointerException e) {
            add(new JLabel("tiempo ", JLabel.RIGHT));
        }

        add(pCronometro);
        add(lblStart);
        add(pMinas);

        try {
            URL url = this.getClass().getResource(Variables.pathIcoMinas);  
            ImageIcon icon = new ImageIcon(url);
            add(new JLabel(icon, JLabel.LEFT));
        } catch(NullPointerException e) {
            add(new JLabel(" minas", JLabel.LEFT));
        }
    }

    public void restart() {
        lblTime.setForeground(countersTextColor);
        lblTime.setText("00:00");
        lblMinas.setForeground(countersTextColor);
    }

    public static void UpdateTime(int min, int seg) {
        if(min == 99 && seg == 59){
            JMinasMain.StopChron();
            lblTime.setForeground(Color.red);
            lblTime.setText("--:--");
            return;
        }

        String strMin, strSeg;
        if(seg > 9) strSeg = ""+seg;
        else strSeg = "0"+seg;

        if(min<10) strMin = "0"+min;
        else strMin = ""+min;

        lblTime.setText(strMin+":"+strSeg);
    }

    public static void UpdateMinas(int num) {
        if(num < 0)
            lblMinas.setForeground(Color.red);
        else
            lblMinas.setForeground(countersTextColor);
        lblMinas.setText(""+num);
    }

    public static void UpdateIconStart(ImageIcon icon, String text) {
        if(icon != null)
            lblStart.setIcon(icon);
        else {
            lblStart.setIcon(null);
            lblStart.setText(text);
        }
    }
}
