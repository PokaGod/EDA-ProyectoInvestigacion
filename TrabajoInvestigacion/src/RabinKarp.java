/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mauricio
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.*;

public class RabinKarp {
    public final static int d = 256;
    static void busqueda(String pat, String txt,int q ){
    int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for txt
        int h = 1;
     
        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M-1; i++)
            h = (h*d)%q;
     
        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < M; i++)
        {
            p = (d*p + pat.charAt(i))%q;
            t = (d*t + txt.charAt(i))%q;
        }
     
        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++)
        {
     
            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters on by one
            if ( p == t )
            {
                /* Check for characters one by one */
                for (j = 0; j < M; j++)
                {
                    if (txt.charAt(i+j) != pat.charAt(j))
                        break;
                }
     
                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
                if (j == M)
                    System.out.println("Palabra: "+pat +" encontrada en la posicion: " + i);
            }
     
            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if ( i < N-M )
            {
                t = (d*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q;
     
                // We might get negative value of t, converting it
                // to positive
                if (t < 0)
                t = (t + q);
            }
        }
    }
    public static String leerTxt() throws IOException{
        try {
            FileReader fr = new FileReader("C:\\Users\\Mauricio\\Desktop\\UL\\2021-1\\EDA\\TrabajoInvestigacion\\RabinKarp2.txt");
            BufferedReader br = new BufferedReader(fr);
             String cad = "";
             cad = br.lines().collect(Collectors.joining());
             return cad;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RabinKarp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
     
    
    public static void main(String[] args) throws IOException
    {
        //Medir tiempo
        long inicio = System.nanoTime();
        //Obtener String mediante lectura de archivo
        String txt = leerTxt();
        //Busqueda de dos letras
        String pat = "a";
        String pat2 = "e";
           
          // A prime number
        int q = 101;
       
          // llamada a algoritmo Rabin-karp
        busqueda(pat, txt, q);
        busqueda(pat2, txt, q);
        //Caculos del tiempo de ejecución
        long fin = System.nanoTime();
        long tTotal = fin - inicio;
        System.out.println("Tiempo de ejecucion: " + tTotal+ " nanosegundos");
    }
}
    
    

