package AD_UT2_EJ_2A_FileX;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FileX {

    public static void main(String[] args) {
        int carInt;
        char carChar;
        String cadena, nombreFich = "C:\\prueba acceso de datos\\prueba.txt";

        // Preguntar al usuario si desea añadir o sobreescribir el archivo
        boolean añadir = false; // Valor por defecto
        System.out.println("¿Desea añadir o sobreescribir el archivo? (añadir/sobreescribir): ");
        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine();
        if (opcion.equalsIgnoreCase("añadir")) {
            añadir = true;
        } else if (opcion.equalsIgnoreCase("sobreescribir")) {
            añadir = false;
        } else {
            System.out.println("Opción no válida. Se usará la opción por defecto (sobreescribir).");
        }

        // APERTURA DEL FICHERO PARA ESCRIBIR O AÑADIR
        try (FileWriter fichEscribir = new FileWriter(nombreFich, añadir)) {
            // Solicitar el nombre completo de una persona
            System.out.println("Introduzca el nombre completo de una persona:");
            String nombreCompleto = scanner.nextLine();

            // ESCRITURA DEL NOMBRE EN EL FICHERO
            fichEscribir.write(nombreCompleto);
            fichEscribir.write('\n');
            fichEscribir.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nLEYENDO CONTENIDO DEL ARCHIVO '" + nombreFich + "':\n");
        try (FileReader fichLeer = new FileReader(nombreFich)) {
            carInt = fichLeer.read();
            while (carInt != -1) {
                carChar = (char) carInt;
                System.out.print(carChar);
                carInt = fichLeer.read();
            }
            fichLeer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

