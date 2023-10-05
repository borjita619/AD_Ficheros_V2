// Parsing DOM y visualización de documento DOM generado
// Transformación a JSON extraída de: https://medium.com/el-acordeon-del-programador/convertir-xml-en-json-adeae122fb47
package Conversion_XML_A_JSON;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringWriter;

public class XMLaJSON {

    public static void main(String[] args) {
        String nomFich;

        nomFich = "src/main/java/Conversion_XML_A_JSON/coches.xml"; // fichero de pruebas
        //nomFich = "coches.xml"; // fichero de pruebas

        // PREPARACIÓN DE LA LECTURA XML
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringComments(true);
        dbf.setIgnoringElementContentWhitespace(true);

        try {
            // LECTURA DEL XML
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document domDoc = db.parse(new File(nomFich));
            System.out.println("\n[Documento " + nomFich + " leído]");

            // PREPARACIÓN PARA LA TRANSFORMACIÓN A JSON CONVIRTIENDO EL DOM A UNA CADENA DE TEXTO
            System.out.println("[Convirtiendo el DOM a cadena de texto]");
            StringWriter writer = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(domDoc), new StreamResult(writer));

            // CONVERSIÓN DE LA CADENA DE TEXTO XML A JSON
            System.out.println("[Convirtiendo la cadena de texto DOM a JSON]");
            String xmlString = writer.getBuffer().toString();
            JSONObject jsonObj = XML.toJSONObject(xmlString);
            String json = jsonObj.toString(4); // num = indentación

            // PRESENTACIÓN DEL JSON
            System.out.println("JSON correspondiente:" + json);

        } catch (FileNotFoundException | ParserConfigurationException
                | SAXException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
