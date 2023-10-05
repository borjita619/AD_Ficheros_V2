package AD_UT2_Ej2_Ficheros_XML_SAX_DANIEL;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AD_UT2_Ej2_Ficheros_XML_SAX_Daniel {
    public static void main(String[] args) {
        String nomFich = "supermercado.xml";

        try {
            // Se crea un parserSAX al que se le configura como manejador un objeto de la Clase GestorEventos
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            parserFactory.setNamespaceAware(true);
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader parserSAX = parser.getXMLReader();
            // --> Revisar el código de GestorEventos -------------------------------------------
            GestorEventos gestorEventos = new GestorEventos(System.out);
            parserSAX.setContentHandler(gestorEventos);

            // Se le pide al parseador que analice el archivo de entrada arriba indicado
            parserSAX.parse(nomFich);
            System.out.println("");
            gestorEventos.mostrarMayorPrecio();
            gestorEventos.numeroProductos();

        } catch (SAXException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
