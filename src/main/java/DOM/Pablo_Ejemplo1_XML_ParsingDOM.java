// Parsing DOM y visualización de documento DOM generado
package DOM;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pablo_Ejemplo1_XML_ParsingDOM {

    private static final String INDENT_NIVEL = "  ";  // Para indentación (sangría izquierda)


    /**
     * Aplica un parsing DOM a un fichero de entrada
     * y la salida la muestra en pantalla y la escribe en un fichero de salida parsing_DOM.txt
     *
     * @param args Argumentos de linea de comando: 1) nombre de fichero de XML
     */
    public static void main(String[] args) {
        String nomFich = "futbolistas.xml"; // fichero de pruebas

        // Código para paso de parámetros cuando se ejecuta desde la terminal
//        if (args.length < 1) {
//          System.out.println("Indicar por favor nombre de fichero");
//          return;
//        } else {
//            nomFich = args[0];
//        }


        // Creación del objeto dbf y configuración
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringComments(true);
        dbf.setIgnoringElementContentWhitespace(true);

        try {
            // Creación del objeto Document domDoc (a partir de db<--dbf + fichero de entrada
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document domDoc = db.parse(new File(nomFich));
            PrintStream ps = new PrintStream("PabloFutbolistasparsing_dom." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".txt");
            // Salida a fichero: método recursivo con salida al fichero con PrintStream + fichero
             muestraNodo(domDoc, 0, ps);
            // salida a terminal: método recursivo con salida a terminal con PrintStream + System.out
            muestraNodo(domDoc, 0, new PrintStream(System.out));
                        
        } catch (FileNotFoundException | ParserConfigurationException
                | SAXException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void muestraNodo(Node nodo, int nivel, PrintStream ps) {

        // Si estamos en un nodo tipo texto que está vació, se ignora retornando inmediatamente sin más.
        if (nodo.getNodeType() == Node.TEXT_NODE) {
            String text = nodo.getNodeValue();
            if (text.trim().length() == 0) {
                return;
            }
        }

        // Se genera sangría izquierda para estructurar más a la derecha el contenido hijo
        for (int i = 0; i < nivel; i++) {
            ps.print(INDENT_NIVEL);
        }

        //Según tipo de nodo (incluido el anterior nodo de texto...
        switch (nodo.getNodeType()) {
            // Documento completo
            case Node.DOCUMENT_NODE:
                Document doc = (Document) nodo;
                ps.println("Documento DOM, versión: " + doc.getXmlVersion()
                        + ", codificación: " + doc.getXmlEncoding());
                break;

            //Elemento: Ej. Clientes, cliente, apellidos, DNI=44555666, CP...
            case Node.ELEMENT_NODE:
                ps.print("<" + nodo.getNodeName());
                NamedNodeMap listaAtr = nodo.getAttributes();  // Lista atributos
                for (int i = 0; i < listaAtr.getLength(); i++) {
                    Node atr = listaAtr.item(i);
                    ps.print(" @" + atr.getNodeName() + "[" + atr.getNodeValue() + "]");
                }
                ps.println(">");
                break;

            //Nodo de texto: Ejemplo: Nadal, 16237,...
            case Node.TEXT_NODE:
                ps.println(nodo.getNodeName() + "[" + nodo.getNodeValue() + "]");
                break;

            // Otro tipo de nodo
            default:
                ps.println("(nodo de tipo: " + nodo.getNodeType() + ")");
        }

        // Obtiene nodos hijos (si los hay)
        NodeList nodosHijos = nodo.getChildNodes();
        // Para cada uno de ellos, realiza la misma operación de forma recursiva: vuelve a llamarse 'muestraNodo()'
        for (int i = 0; i < nodosHijos.getLength(); i++) {
            // Llamada para cada hijo aumentando en 1 la sangría
            muestraNodo(nodosHijos.item(i), nivel + 1, ps);
        }
    }
}
