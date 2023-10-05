package AD_UT2_Ej2B_BufferedX;

import java.io.IOException;
import java.io.PrintWriter;
public class AD_UT2_Ej2B_BufferedX_Laura {
    public static void main(String[] args) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("notas.txt");
            double nota = 7.2894;
            String alumno = "Sergio Tendero";
            printWriter.printf("%-30s %3.1f\n", alumno, nota);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter !=null) {
                printWriter.close();
            }
        }
    }
}