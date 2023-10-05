package AD_UT2_Ej_2D_ObjectStream_Asier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

//Vamos a crear un fichero de lectura y escritura donde se guarden los atributos de una persona
public class AD_UT2_Ej_2D_ObjectStream_Asier {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Creamos los atributos que mas adelante pediremos al usuario
        String nombre;
        int edad;
        int dni;
        char letraDni;
        String cadena, nombreFich = "D:\\DAM_prueba_fich\\AD_UT2Ej2d_prueba_Asier.txt";
        boolean añadir = true, sobreescribir = false;
        boolean modoApertura = sobreescribir;
        //Creamos le fichero
        System.out.println("CREANDO FICHERO: " + nombreFich + " en modo");
        if (modoApertura == añadir)
            System.out.println(" Añadir.");
        else
            System.out.println(" sobreescribir.");

        //Aqui solicitamos los datos del usuario
        try (ObjectOutputStream fichEscribir = new ObjectOutputStream(new FileOutputStream(nombreFich, modoApertura))) {

            System.out.print("Ingrese el nombre: ");
            nombre = scanner.nextLine();

            System.out.print("Ingrese la edad: ");
            edad = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese el DNI (sin letra): ");
            dni = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese la letra del DNI: ");
            letraDni = scanner.nextLine().charAt(0);

            // Escribimos los datos en el fichero
            fichEscribir.writeUTF(nombre);
            fichEscribir.writeInt(edad);
            fichEscribir.writeInt(dni);
            fichEscribir.writeChar(letraDni);

            System.out.println("\nGUARDANDO '" + nombre + " " + edad + " " + dni + letraDni + "'");

            fichEscribir.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nLEYENDO CONTENIDO DEL ARCHIVO '" + nombreFich + "':\n");
        try (ObjectInputStream fichLeer = new ObjectInputStream(new FileInputStream(nombreFich))) {
            nombre = fichLeer.readUTF();
            edad = fichLeer.readInt();
            dni = fichLeer.readInt();
            letraDni = fichLeer.readChar();

            System.out.println("\nLEIDO '" + nombre + " " + edad + " " + dni + letraDni + "'");

            fichLeer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
