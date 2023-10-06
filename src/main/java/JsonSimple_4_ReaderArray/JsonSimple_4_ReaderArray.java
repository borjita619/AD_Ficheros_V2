/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
https://www.delftstack.com/es/howto/java/handling-json-arrays-in-java/ 
 */
package JsonSimple_4_ReaderArray;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonSimple_4_ReaderArray {

    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException {
        JSONParser parser = new JSONParser();

        // Intento de lectura de un array JSON de fichero --------------------------------------------------------------
        try {
            Reader reader = new FileReader("dataArrayMartin.json");

            // Parse con cast JSONArray al esperar un array de elementos
            JSONArray arrayJSON = (JSONArray) parser.parse(reader);

            // Muestra todos los datos
            System.out.println("Datos leídos en bruto:" + arrayJSON.toJSONString());
            
            // Recorrido del array de elementos leídos y presentación personalizada
            for (int i = 0; i < arrayJSON.size(); i++) {
                JSONObject obj = (JSONObject) arrayJSON.get(i);
                mostrar(obj);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + " :File Not Found");
        } catch (ParseException e) {
            System.out.println(e.getMessage() + " :Could not parse");
        } catch (IOException e) {
            System.out.println(e.getMessage() + " :IOException"); //Error entrada salida
        }
    }

    /**
     * Método que encapsula los detalles de presentación de cada objeto JSON
     * @param obj
     */
    private static void mostrar(JSONObject obj) {
        //Toma el valor de entrada JSON y extrae los valores y los convierte para usarse después
        String nombre = (String) obj.get("Nombre");
        String apellido = (String) obj.get("Apellido");
        int edad = Integer.parseInt(obj.get("Edad") + "");
        JSONArray pueblo = (JSONArray) obj.get("Pueblo");
        double altura = Double.parseDouble(obj.get("Altura") + "");

        System.out.println("Nombre: " + nombre + " Apellido:" + apellido);
        System.out.println("\tEdad: " + edad);
        System.out.println("\tAltura:" + altura + "cm");
        System.out.println("\tPueblo: " + pueblo);
    }
}
