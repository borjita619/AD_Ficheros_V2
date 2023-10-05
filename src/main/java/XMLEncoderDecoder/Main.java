/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLEncoderDecoder;

/**
 *
 * https://www.discoduroderoer.es/como-leer-y-escribir-un-fichero-con-randomaccessfile/
 * + Carlos G.
 */


import java.util.Scanner;

// EjemploRandomAccessFile 
public class Main {

    public static void main(String[] args) {

        GestorFichRandom rIGestor = new GestorFichRandom();
        String fichXMLOrigen;
        String prodABuscar;
        Scanner sc = new Scanner(System.in);

        System.out.print("Indicar nombre de fichero XML desde el que cargar los datos (en caso de dejar vacío se tomarán valores preconfigurados): ");
        fichXMLOrigen = sc.nextLine();
        // si no se indica...
        if (fichXMLOrigen.isBlank()) {
            rIGestor.add(1, "Hyundai", "i20N", 30210.0, true, 'B');
            rIGestor.add(2, "Toyota", "Yaris GR", 38249.99, true, 'B');
            rIGestor.add(3, "Ford", "Focus ST", 37326.19, false, 'B');
            rIGestor.add(4, "Honda", "Civic Type R", 54850.0, false, 'B');
            rIGestor.add(5, "Cupra", "León", 32450.0, true, 'B');
            System.out.println("Cargados 5 registros por defecto.");
        } else {
            rIGestor.cargarFicheroXML(fichXMLOrigen);
        }

        rIGestor.mostrarCoches();

        System.out.println("Guardando en fichero XML...");
        rIGestor.guardarAFicheroXML();
    }
}
