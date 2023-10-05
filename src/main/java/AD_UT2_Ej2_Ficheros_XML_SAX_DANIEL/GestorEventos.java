package AD_UT2_Ej2_Ficheros_XML_SAX_DANIEL;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.PrintStream;

public class GestorEventos extends DefaultHandler {
    final PrintStream ps;
    int numeroProductos;
    private boolean enProducto;
    String producto;
    double mayorPrecio;
    double precioActual;
    private boolean enNombre;
    boolean enPrecio;


    public GestorEventos(PrintStream ps) {
        this.ps = ps;
        this.numeroProductos = 0;
        this.enProducto = false;
        this.enNombre = false;
        this.enPrecio = false;
        this.producto = null;
        this.mayorPrecio = 0;
        this.precioActual = 0;

    }


    public void startDocument() {
        numeroProductos = 0;

    }


    @Override
    public void startElement(String uri, String nombreLocal, String qName, Attributes atributos) throws SAXException {
        if (nombreLocal.equals("productos")) {
            enProducto = true;
        } else if (nombreLocal.equals("nombre") && enProducto) {
            // Activar la bandera enProducto cuando se encuentra el elemento "nombre" dentro de un producto
            enNombre = true;
            // Inicializar la variable producto
            this.producto = "";
        }else if (nombreLocal.equals("precio") && enProducto) {
            enPrecio = true;
            precioActual = 0; // Inicializar el precio actual
        }
    }

    @Override
    public void characters(char[] cars, int inicio, int longitud) throws SAXException {
        String texto = new String(cars, inicio, longitud);
        if (texto.trim().length() == 0) {
            return;
        }
        if (this.enNombre) {
            // Concatenar el texto cuando estamos dentro del elemento "nombre"
            this.producto += texto;
        }else if (enPrecio) {
            // Obtener el precio actual y convertirlo a un entero (puedes ajustar el tipo de dato según tu XML)
            precioActual = Double.parseDouble(texto);
        }
    }

    @Override
    public void endElement(String uri, String nombreLocal, String qName) throws SAXException {
        if (nombreLocal.equals("nombre") && enProducto) {
            enNombre = false; // Desactivar la bandera enNombre cuando terminamos con el elemento "nombre"
        } else if (nombreLocal.equals("precio") && enProducto) {
            enPrecio = false;
            // Comprobar si el precio actual es mayor que el mayor precio registrado hasta ahora
            if (precioActual > mayorPrecio) {
                mayorPrecio = precioActual;
            }
        } else if (nombreLocal.equals("productos")) {
            System.out.println("");
            System.out.println("Nombre producto: " + producto);
            enProducto = false; // Restablecer enProducto a false cuando terminamos con un producto
            numeroProductos++;
        }
    }

    public void mostrarMayorPrecio() {
        System.out.println("El precio más alto es: " + mayorPrecio + " euros");
    }

    public void numeroProductos(){
        System.out.println("Numero de productos: " + numeroProductos);
    }
}
