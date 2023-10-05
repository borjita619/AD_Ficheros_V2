package AD_UT2_Ej3_Ficheros_JSON;

import java.io.FileWriter;

import java.io.IOException;

import java.io.Reader;

import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;


public class JsonSimple_3_WriterArrayNuevo_Denis {


    @SuppressWarnings("unchecked")

    public static void main(String[] args) {
        //el parser divide el array para poder extraer su informacion y sea mas facil leerla por bloques
        //JSONParser parser = new JSONParser();
        //Reader reader;

        // Creación de un array JSON en este caso para objetos completos JSON ------------------------------------------
        JSONArray arrayPrincipalJSON = new JSONArray();

        // Creación del objeto 1 JSON ----------------------------------------------------------------------------------
        JSONObject obj1 = new JSONObject();
        JSONArray hobbies1 = new JSONArray();

        obj1.put("Nombre", "Anderson James");
        obj1.put("Edad", 32);
        hobbies1.add("Escalada");
        hobbies1.add("Parchis");
        hobbies1.add("Waterpolo");
        obj1.put("Hobbies", hobbies1);

        // Creación del objeto 2 JSON ----------------------------------------------------------------------------------
        JSONObject obj2 = new JSONObject();
        JSONArray hobbies2 = new JSONArray();

        obj2.put("Nombre", "Manolo Lama");
        obj2.put("Edad", 52);
        hobbies2.add("Futbol Americano");
        obj2.put("Hobbies", hobbies2);

        // Almacenamiento de los objetos JSON en el array --------------------------------------------------------------
        arrayPrincipalJSON.add(obj1);
        arrayPrincipalJSON.add(obj2);

        // Escritura del objeto JSON en fichero con FileWriter, guardado en el target del proyecto ---------------------
        try {
            FileWriter file = new FileWriter("dataArray.json");
            file.write(arrayPrincipalJSON.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Presentación de los datos del JSON en el terminal -----------------------------------------------------------
        System.out.print(arrayPrincipalJSON.toJSONString());
    }
}
