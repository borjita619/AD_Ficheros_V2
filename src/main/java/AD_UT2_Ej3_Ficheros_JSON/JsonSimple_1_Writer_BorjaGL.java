package AD_UT2_Ej3_Ficheros_JSON;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
https://www.digitalocean.com/community/tutorials/json-simple-example
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonSimple_1_Writer_BorjaGL {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        JSONObject obj1JSON = new JSONObject();
        String alumnoNuevoAñadir;
        String dniAlumnoAñadir;
        int edadNuevaAñadir;

        // Asignación de valores a variables primitivas y Array JSON ---------------------------------------------------
        alumnoNuevoAñadir = "Nicolás Pla";
        dniAlumnoAñadir = "44556677A";
        edadNuevaAñadir = 32;
        JSONArray asignaturas = new JSONArray();
        asignaturas.add("Lengua y Literatura");
        asignaturas.add("Matemáticas");
        asignaturas.add("Geografía");

        // Asignación de variables y Array al nuevo objeto JSON --------------------------------------------------------
        obj1JSON.put("nombre", alumnoNuevoAñadir);
        obj1JSON.put("edad", edadNuevaAñadir);
        obj1JSON.put("dni", dniAlumnoAñadir);
        obj1JSON.put("asignaturas", asignaturas);

        // Escritura del objeto JSON en fichero con FileWriter ---------------------------------------------------------
        try {
            FileWriter file = new FileWriter("data.json");
            file.write(obj1JSON.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Presentación de los datos del JSON en el terminal -----------------------------------------------------------
        System.out.print(obj1JSON.toJSONString());

    }

}
