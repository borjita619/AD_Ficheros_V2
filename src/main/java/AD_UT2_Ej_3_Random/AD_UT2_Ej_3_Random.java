package AD_UT2_Ej_3_Random;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * https://www.discoduroderoer.es/como-leer-y-escribir-un-fichero-con-randomaccessfile/
 * + Juan José
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

// EjemploRandomAccessFile
public class AD_UT2_Ej_3_Random {

    public static void main(String[] args) {

        ArrayList<Cantante> cantantes = new ArrayList<>();

        //Creamos los PRODUCTOS y los añadimos al ArrayList creado anteriormente
        cantantes.add(new Cantante(1, "Mora", 1.75, true));
        cantantes.add(new Cantante(2, "Maria Isabel", 1.62, false));
        cantantes.add(new Cantante(3, "Nicki Nicole", 1.45, true));
        cantantes.add(new Cantante(4, "Melendi", 1.9, true));
        cantantes.add(new Cantante(5, "Dani Martín", 1.75, false));

        //Creamos el objeto RandomAccessFile con los parámetros de la ruta del fichero y con los permisos "rw" (lectura y escritura)
        try (RandomAccessFile raf = new RandomAccessFile("AD_UT2_Ejemplo3_Cantantes.dat", "rw")) //La ruta puede ser absoluta o relativa
        {

            // ESCRITURA
            for (Cantante c : cantantes) {

                raf.writeInt(c.getId());

                StringBuilder sb = new StringBuilder(c.getNombre());
                sb.setLength(10);
                raf.writeChars(sb.toString());

                raf.writeDouble(c.getAltura());
                raf.writeBoolean(c.isActivo());
            }

            // LECTURA DEL REGISTRO
            int numeroRegistroALeer = 4;
            int tamanoBytesRegistro = 33;// 33 bytes (4 (int) (10 * 2) (cadena) 8 (doble) 1 (booleano)).
            int posicionALeer = (numeroRegistroALeer - 1) * tamanoBytesRegistro;
            raf.seek(posicionALeer);

            System.out.println(raf.readInt());
            String nombre = "";
            for (int i = 0; i < 10; i++) {
                nombre += raf.readChar();
            }
            System.out.println(nombre);
            //Lee un Double como si fuera un Long y luego lo convierte en Double
            System.out.println(raf.readDouble());
            //Lee en el archivo si es un byte 0 para saber si es falso, o si es otro valor y es verdadero
            System.out.println(raf.readBoolean());

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}