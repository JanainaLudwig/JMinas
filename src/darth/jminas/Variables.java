package darth.jminas;

import java.awt.Color;

public class Variables {
    private static int nivel = 1;
    public static int alto = 9;
    public static int ancho = 9;
    public static int numeroMinas = 18;

    public static final Color color1 = Color.blue, color2 = new Color(00,99,00), color3 = Color.red,
                              color4 = Color.magenta, color5 = Color.yellow, color6 = Color.cyan,
                              color7 = Color.orange, color8 = Color.black;

    public static final String[] archivos = {
        "cronometro.png",
        "mina.png",
        "bandera.png",
        "boom.png",
        "cool_32x32.png",
        "surprised_32x32.png",
        "question_32x32.png",
        "crying_32x32.png",
        "party_32x32.png",
        "laughtingoutloud_32x32.png"
    };

    public static final String pathIcoCronometro = "/darth/img/" + archivos[0];
    public static final String pathIcoMinas = "/darth/img/" + archivos[1];
    public static final String pathBandera = "/darth/img/" + archivos[2];
    public static final String pathExplosion = "/darth/img/" + archivos[3];
    public static final String pathNormal = "/darth/img/" + archivos[4];
    public static final String pathClick = "/darth/img/" + archivos[5];
    public static final String pathMarca = "/darth/img/" + archivos[6];
    public static final String pathLooser = "/darth/img/" + archivos[7];
    public static final String pathWinner = "/darth/img/" + archivos[8];
    public static final String pathRiendo = "/darth/img/" + archivos[9];
    public static final String SonidoExplosion = "/darth/audio/boom.wav";
    public static final String SonidoGanador = "/darth/audio/win.wav";

    public static final String txtNormal = " :) ";
    public static final String txtClick = " :O ";
    public static final String txtMarca = " :| ";
    public static final String txtWinner = " XD ";
    public static final String txtLooser = " :( ";
    public static final String txtRiendo = " ... ";

    public static void setNivel(int val) {
        nivel = val;
        switch(val) {
        case 0:
            alto = 9;
            ancho = 9;
            numeroMinas = 9;
            break;
        case 1:
            alto = 9;
            ancho = 9;
            numeroMinas = 18;
            break;
        case 2:
            alto = 16;
            ancho = 16;
            numeroMinas = 40;
            break;
        case 3:
            alto = 16;
            ancho = 30;
            numeroMinas = 99;
            break;
        }
    }

    public static int getNivel() {
        return nivel;
    }

    public static Color getColorCantidad(int op) {
        switch(op) {
            case 1: return new Color(48,63,159); case 2: return new Color(56,142,60); case 3: return new Color(211,47,47); case 4: return new Color(170,0,255);
            case 5: return new Color(249,168,37); case 6: return new Color(0,172,193); case 7: return new Color(255,111,0); case 8: return new Color(38,50,56);
            default: return Color.black;
        }
    }
}
