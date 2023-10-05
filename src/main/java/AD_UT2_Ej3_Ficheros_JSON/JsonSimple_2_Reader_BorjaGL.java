package AD_UT2_Ej3_Ficheros_JSON;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Adaptado de: https://www.digitalocean.com/community/tutorials/json-simple-example
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonSimple_2_Reader_BorjaGL {

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {

        // Lectura desde un fichero de una JSONObject ------------------------------------------------------------------
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("data.json");
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        // Acceso e impresión por teminal de los datos del JSONObject cargado ------------------------------------------
        String nombre = (String) jsonObject.get("nombre");
        System.out.println("Nombre = " + nombre);

        long edad = (Long) jsonObject.get("edad");
        System.out.println("Edad = " + edad);

        String dni = (String) jsonObject.get("dni");
        System.out.println("DNI = " + dni);

        JSONArray asignaturas = (JSONArray) jsonObject.get("asignaturas");

//        @SuppressWarnings("unchecked")
        // Recorrido array ciudades con iterador (alternativa 1)
//        Iterator<String> it = asignaturas.iterator();
//        while (it.hasNext()) {
//            System.out.println("Asignatura = " + it.next());
//        }
        
        // Recorrido array ciudades con for each(alternativa 2)
        for (Object asignatura : asignaturas) {
            System.out.println("Asignaturas = " + asignatura);
        }
        
        // Cierre del fichero ------------------------------------------------------------------------------------------
        reader.close();
    }

}
