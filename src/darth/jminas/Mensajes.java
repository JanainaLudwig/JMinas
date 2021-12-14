package darth.jminas;

import java.util.ResourceBundle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Mensajes {
	private static ResourceBundle MSG;
	public static String Caminho = "/src/darth/jminas/idioma.txt";
	private static String aux;
	
	public static String Read(String Caminho){
        String conteudo = "";
        try {
            FileReader arq = new FileReader(System.getProperty("user.dir") + Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            try {
                conteudo = lerArq.readLine();
                arq.close();
                return conteudo;
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return "";
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return "";
        }
    }
    
    public static boolean Write(String Caminho,String Texto){
        try {
            FileWriter arq = new FileWriter(System.getProperty("user.dir") + Caminho);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println(Texto);
            gravarArq.close();
            return true;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
		
	public static String getMessage(String msg) {
		aux = Read(Caminho);
		if(aux.equals("en")) {
			MSG = ResourceBundle.getBundle("darth.jminas.en");
			System.out.println("Acessou En!");
		}else if (aux.equals("pt-br")){
			MSG = ResourceBundle.getBundle("darth.jminas.pt_br");
			System.out.println("Acessou Pt-Br!");
		}else {
			MSG = ResourceBundle.getBundle("darth.jminas.en");
			System.out.println(aux);
			}
		
		if(MSG.containsKey(msg)) {
			return MSG.getString(msg);
		}
		
		return "Não encontrado";
	}
}

