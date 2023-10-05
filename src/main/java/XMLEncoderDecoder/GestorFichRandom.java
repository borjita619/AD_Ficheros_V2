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
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

// EjemploRandomAccessFile 
public class GestorFichRandom {

    ArrayList<Coche> listadoCoches = new ArrayList<>();
    String nomFichXML = "src/main/java/XMLEncoderDecoder/coches.xml";

    public GestorFichRandom() {
    }

    public void add(int id, String marca, String modelo, double precio, boolean descuento, char etiqueta) {
        listadoCoches.add(new Coche(id, marca, modelo, precio, descuento, etiqueta));
    }

    public void mostrarCoches(){
        System.out.println("Listado de cohes:");
        for (Coche c: listadoCoches){
            System.out.println("\t" + c.toString());
        }
    }

    public void cargarFicheroXML(String nomFich) {
        FileInputStream fis;
        XMLDecoder xmld;
        try {
            fis = new FileInputStream(nomFich);
            xmld = new XMLDecoder(fis);
            listadoCoches = (ArrayList) xmld.readObject();
            xmld.close();
            System.out.println("Cargados " + listadoCoches.size() + " coches de " + nomFich);
        } catch (Exception e) {
            System.err.println("\tERROR en la lectura de datos del archivo: " + nomFich);
        }

    }

    public void guardarAFicheroXML() {
        FileOutputStream fos;
        XMLEncoder xmle;

        try {
            fos = new FileOutputStream(nomFichXML);
            xmle = new XMLEncoder(new BufferedOutputStream(fos));
            xmle.writeObject(listadoCoches);
            xmle.close();
//            System.out.println("Se ha guardado en el archivo " + nomFichXML + " " + coches.size() + " registros.");
        } catch (Exception e) {
            System.err.println("\tERROR en la escritura de datos del archivo: " + nomFichXML);
        }
    }
}
