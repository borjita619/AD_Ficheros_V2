package AD_UT2_Ej_1_Sist_Ficheros;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Ad_UT2_Ej_1_Sist_Ficheros_Lucia {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //Definimos las variables
        String nombreUsuario = null;
        File fichero;
        File ficheroContenido;
        String[] listado;
        String dia, mes, year, hora, minuto, segundo;
        Calendar c;
        Date d;
        long ms;
        String probar, ruta = "C:\\Users\\rolda\\OneDrive\\Escritorio\\2ºDAM\\Acceso a Datos\\1ºTrimestre\\Unidad 2";

        System.out.println("_________________________________________________________________________________");
        System.out.println("BÚSQUEDA DE FICHEROS Y DIRECTORIOS");
        System.out.println("_________________________________________________________________________________");
        System.out.println("Ruta base contemplada: " + ruta);

        // configuración con parámetro de llamada: "java -jar AD_UT2_Ej_1_Sist_Ficheros.jar"
        if (args.length > 0) {
            System.out.println("Tomando para ejecución nombre de archivo pasado por parámetro en llamada en ruta " + ruta + " ...");
            ruta+= "\\" + args[0];
        } //Configuración manual carpeta o fichero ----------------------------
        else {
            System.out.println("Realizando prueba: ");
            System.out.println("\tcarpeta = c");
            System.out.println("\tfichero 'foto1.jpg' = cualquier caracter");
            System.out.println("Introduce nombre de Usuario: ");
            nombreUsuario = sc.nextLine();
            System.out.println("¿Qué deseas probar?: ");
            char letra = (char) System.in.read();



            if (letra=='c')
                probar = "carpeta"; // fichero o carpeta
            else
                probar = "fichero";



            // Valor sí la ruta corresponda a un directorio / carpeta
            if (probar.equals("fichero")) {
                System.out.println("Tomando para ejecución fichero de prueba...");
                ruta+= "\\foto1.jpg";
            } // Valor para que la ruta corresponda a un fichero / archivo
            else {
                System.out.println("Tomando para ejecución carpeta de prueba...");
                ruta+= "";
            }
        }

        // Creación de una instancia fichero a partir de la ruta
        fichero = new File(ruta);

        // Si existe, se obtendrá información
        if (fichero.exists()) {
            ms = fichero.lastModified();
            d = new Date(ms);
            c = new GregorianCalendar();
            // Conversión del formato fecha hora del sistema al Gregoriano (habitual)
            c.setTime(d);
            dia = Integer.toString(c.get(Calendar.DATE));
            mes = Integer.toString(c.get(Calendar.MONTH));
            year = Integer.toString(c.get(Calendar.YEAR));
            hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
            minuto = Integer.toString(c.get(Calendar.MINUTE));
            segundo = Integer.toString(c.get(Calendar.SECOND));

            // Si es un fichero -> presenta sus detalles
            if (fichero.isFile()) {
                System.out.print("(F):" + ruta );
                System.out.print("\t\tFecha modif: " + dia + "-" + mes + "-" + year + " " + hora + ":" + minuto + ":" + segundo);
                System.out.println("\t\tTamaño: " + fichero.length() + " bytes");
                System.out.println("\t\tNombre Usuario: " + nombreUsuario);


            } // Si es un directorio...
            else if (fichero.isDirectory()) {
                // Se presenta información de ruta y fecha/hora
                System.out.print("(D): " + ruta );
                System.out.println("\tFecha modif: " + dia + "-" + mes + "-" + year + " " + hora + ":" + minuto + ":" + segundo);
                System.out.println("\t\tNombre Usuario: " + nombreUsuario);

                // Se obtiene listado de su contenido y se recorre para presentar por terminal
                listado = fichero.list();
                if (listado != null) {
                    for (String s : listado) {
                        ficheroContenido = new File(fichero.getAbsolutePath(), s);
                        if (ficheroContenido.isDirectory()) {
                            System.out.println("\t(D)" + s);
                        } else {
                            System.out.println("\t(F)" + s);

                        }
                    }
                }
            }
        } else {
            System.out.println("\tNo existe: " +  ruta);
        }
    }
}