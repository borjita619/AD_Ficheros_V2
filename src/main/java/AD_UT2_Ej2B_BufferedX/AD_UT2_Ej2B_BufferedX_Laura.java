package AD_UT2_Ej2B_BufferedX;

import java.io.BufferedReader; //se utiliza para leer texto de entrada.
import java.io.BufferedWriter; //se utiliza para escribir texto en un flujo de salida de caracteres.
import java.io.FileReader; //lee dos bytes a la vez
import java.io.FileWriter; //escribe dos bytes a la vez
import java.io.IOException;

public class AD_UT2_Ej2B_BufferedX_Laura {

    public static void main(String[] args) {
        //Variables
        String nombreFich = "C:\\2DAM\\AD_UT2_Ej2B_prueba1.txt"; //Nombre del archivo
        boolean añadir = true;
        boolean sobreescribir = false;
        boolean modoApertura = añadir; //indica si los datos los tiene que sobreescribir o añadir
        //Creación del fichero
        System.out.print("CREANDO FICHERO: '" + nombreFich + "' en modo ");
        if (añadir == añadir) {
            System.out.println(" añadir.");
        } else {
            System.out.println(" sobreescribir.");
        }

        String cadena;
        try {
            //Se crea el objeto fichBuf y este escribe datos en nombreFich
            BufferedWriter fichBuf = new BufferedWriter(new FileWriter(nombreFich, modoApertura));
            try {
                //Crea un numero aleatorio entre 0 y 1 y se multiplica por 1000
                double var10000 = Math.random();
                //se guarda en la variable cadena
                cadena = "Frase de muestra: " + (int)(var10000 * 1000.0);
                //imprime
                System.out.println("\nGUARDANDO '" + cadena + "'");
                fichBuf.write(cadena);//se escribe la cadena en el archivo
                fichBuf.newLine(); //Agrega un salto de línea
                fichBuf.flush(); //Se vacia el búfer y se envían los datos al archivo
                fichBuf.write("24");
                fichBuf.newLine(); //salto de linea
                fichBuf.write("1.84");
                fichBuf.newLine(); //salto de linea
                fichBuf.close(); //cierra el BufferedWriter
            } catch (Throwable var13) { //si da error al cerrar el buffered
                try {
                    fichBuf.close(); //se cierra
                } catch (Throwable var12) {
                    var13.addSuppressed(var12); //el error se agrega a la lista de excepciones
                }

                throw var13;
            }

            fichBuf.close(); //se asegura de que todos los datos se hayan escrito
        } catch (IOException var14) {
            System.out.println(var14.getMessage()); //si da error imprime un mensaje por pantalla
        }
        //empieza a leer el contenido de nombreFich
        System.out.println("\nLEYENDO CONTENIDO DEL ARCHIVO '" + nombreFich + "':\n");

        try {
            //Define el fichero y lee el texto del archivo
            BufferedReader fichBuf = new BufferedReader(new FileReader(nombreFich));

            try {
                cadena = fichBuf.readLine(); //ReadLine lee datos linea por linea

                while(true) { //lee todas las lineas del archivo
                    //cierra el archivo
                    if (cadena == null) { //si es null lo cierra
                        fichBuf.close();
                        break;
                    }

                    System.out.println(cadena);
                    cadena = fichBuf.readLine(); //la la siguente línea del archivo y almacena en cadena
                }
                //maneja las excepciones que puedan ocurrir en el try
            } catch (Throwable var15) {
                try {
                    fichBuf.close(); //si ocurre una excepcion cierra
                } catch (Throwable var11) {
                    var15.addSuppressed(var11); //se añade a la lista de excepciones
                }

                throw var15; //se lanza la excepcion original
            }

            fichBuf.close(); //cierra el bufferedReader
        } catch (IOException var16) {
            System.out.println(var16.getMessage()); //si ocurre un error imprime el error por consola
        }

        System.out.println(""); //linea en blanco
    }
}