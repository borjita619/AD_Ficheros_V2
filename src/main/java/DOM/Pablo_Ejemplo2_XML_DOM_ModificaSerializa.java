package DOM;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

public class Pablo_Ejemplo2_XML_DOM_ModificaSerializa {

    private static final String INDENT_NIVEL = "  ";  // Para indentación

    /**
     * Parsing DOM, modificación y serialización de documento modificado, previa
     * copia de seguridad de antiguo. Lee documento Futbolistas.txt. Copia de
     * seguridad añadiendo .bak al nombre Seraliza documento modificado en el
     * mismo fichero Futbolistas.txt, sobreescribiendo sus contenidos.
     *
     * @param args Argumentos de linea de comando
     */
    public static void main(String[] args) {
        String nomFich = "futbolistas.xml";
        String nomFichBackup = nomFich + ".bak";
        String idFutfolista = "2";  // ROJAS
        String nuevoEquipoFutbolista = "Al-Nassar";
        File fEntrada = new File(nomFich);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringComments(true);
        dbf.setIgnoringElementContentWhitespace(true);

        try {
            // Parsing de documento
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document domDoc = db.parse(fEntrada);

            // =====================================================================
            // Presentación del contenido en el terminal
            System.out.println("Contenidos originales");
            System.out.println("----------------------");
            muestraNodo(domDoc, 0, new PrintStream(System.out));
            System.out.println("Pulsa intro para continuar...");
            System.in.read();

            // =====================================================================
            // Modifica el nombre de Futbolista con un DNI dado
            System.out.println("\nModificando del Futbolista: " + idFutfolista + " el equipo por: " + nuevoEquipoFutbolista);

            // Primero se descartan casos de error +++++++++++++++++++++++++++++++++

            // Busca nodo <Futbolistas> por debajo del nodo inicial. Debe haber uno solo.
            NodeList nodosPrimerNivel = domDoc.getChildNodes();
            if (nodosPrimerNivel.getLength() != 1) {
                System.err.println("ERROR: Hay " + nodosPrimerNivel.getLength()
                        + " en el primer nivel, debe haber exactamente uno");
                return;
            }

            // Comprobar que el nodo en el primer nivel es de tipo ELEMENT_NODE y tiene por nombre Futbolistas
            Node nodoPrimerNivel = nodosPrimerNivel.item(0);
            if (nodoPrimerNivel.getNodeType() != Node.ELEMENT_NODE) {  // Normalmente nunca sucederá
                System.err.println("ERROR: Nodo de primer nivel no de tipo elemento, sino "
                        + nodoPrimerNivel.getNodeType()
                );
                return;
            }
            if (!nodoPrimerNivel.getNodeName().equals("futbolistas")) {
                System.err.println("ERROR: Nombre de nodo de primer nivel es "
                        + nodoPrimerNivel.getNodeName() + ", debería ser futbolistas"
                );
                return;
            }

            // Ahora busca nodos por debajo con nombre Futbolista y con atributo DNI con
            // valor buscado.
            NodeList nodosFutbolistas = nodoPrimerNivel.getChildNodes();
            for (int iCli = 0; iCli < nodosFutbolistas.getLength(); iCli++) {
                Node nodoFutbolista = nodosFutbolistas.item(iCli);
                if (nodoFutbolista.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                if (!nodoFutbolista.getNodeName().equals("futbolista")) {
                    System.err.println("ERROR: Nombre de nodo es "
                            + nodoFutbolista.getNodeName() + ", debería ser futbolista. Detalles del nodo: "
                    );
                    muestraNodo(nodoFutbolista, 0, new PrintStream(System.err));
                    return;
                }

                // Obtén valor de atributo DNI del elemento Futbolista. Se considera atributo obligatorio
                NamedNodeMap listaAtr = nodoFutbolista.getAttributes();  // Lista atributos
                String dni = null;
                for (int iAtr = 0; dni == null && iAtr < listaAtr.getLength(); iAtr++) {
                    Node atr = listaAtr.item(iAtr);
                    if (atr.getNodeName().equals("ID")) {
                        dni = atr.getNodeValue();
                    }
                }
                if (dni == null) {
                    System.err.println("ERROR: Atributo ID no existe para futbolista. Detalles del nodo: ");
                    muestraNodo(nodoFutbolista, 0, new PrintStream(System.err));
                    return;
                }

                // Si es el Futbolista que andamos buscando, cambiar el nombre +++++++++++++++++++++++++
                if (dni.equals(idFutfolista)) {  // Busca nodo hijo 'apellidos'
                    NodeList nodosHijosCli = nodoFutbolista.getChildNodes();
                    boolean encontradoApell = false;
                    for (int iNodoHijoCli = 0; !encontradoApell && iNodoHijoCli < nodosHijosCli.getLength(); iNodoHijoCli++) {
                        Node nodoHijoCli = nodosHijosCli.item(iNodoHijoCli);
                        if (nodoHijoCli.getNodeName().equals("equipo")) {
                            encontradoApell = true;
                            // Sustitución del apellido por el nuevo valor ++++++++++++++++++++++++++
                            nodoHijoCli.setTextContent(nuevoEquipoFutbolista);
                        }
                    }
                    if (!encontradoApell) {
                        System.err.println("ERROR: no hay nodo con nombre equipo bajo nodo futbolista.");
                        return;
                    }
                }
            }
            System.out.println("Pulsa intro para continuar...");
            System.in.read();


            // =====================================================================
            // Presentación del contenido modificado en el terminal
            System.out.println("Contenidos modificados");
            System.out.println("----------------------");
            muestraNodo(domDoc, 0, new PrintStream(System.out));


            // =====================================================================
            // Copia de seguridad de fichero antes de sobreescribir sus contenidos
            // (si existe el bak no hace nada)
            fEntrada.renameTo(new File(nomFichBackup));


            // =====================================================================
            // Escritura del DOM en memoria (modificado) en un fichero XML
            // Serializar utilizando clase Transformer
            DOMSource domSource = new DOMSource(domDoc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");

            FileWriter fw = new FileWriter(nomFich);
            StreamResult sr = new StreamResult(fw);

            transformer.transform(domSource, sr);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }



    public static void muestraNodo(Node nodo, int nivel, PrintStream ps) {
        if (nodo.getNodeType() == Node.TEXT_NODE) { // Ignora textos vacíos
            String text = nodo.getNodeValue();
            if (text.trim().length() == 0) {
                return;
            }
        }
        for (int i = 0; i < nivel; i++) {    // Indentación
            ps.print(INDENT_NIVEL);
        }
        switch (nodo.getNodeType()) {  // Escribe información de nodo según tipo
            case Node.DOCUMENT_NODE:  // Documento
                Document doc = (Document) nodo;
                ps.println("Documento DOM, versión: " + doc.getXmlVersion()
                        + ", codificación: " + doc.getXmlEncoding());
                break;
            case Node.ELEMENT_NODE:    // Elemento
                ps.print("<" + nodo.getNodeName());
                NamedNodeMap listaAtr = nodo.getAttributes();  // Lista atributos
                for (int i = 0; i < listaAtr.getLength(); i++) {
                    Node atr = listaAtr.item(i);
                    ps.print(" @" + atr.getNodeName() + "[" + atr.getNodeValue() + "]");
                }
                ps.println(">");
                break;
            case Node.TEXT_NODE:    // Texto
                ps.println(nodo.getNodeName() + "[" + nodo.getNodeValue() + "]");
                break;
            default:    // Otro tipo de nodo
                ps.println("(nodo de tipo: " + nodo.getNodeType() + ")");
        }
        NodeList nodosHijos = nodo.getChildNodes();    // Muestra nodos hijos
        for (int i = 0; i < nodosHijos.getLength(); i++) {
            muestraNodo(nodosHijos.item(i), nivel + 1, ps);
        }
    }
}
