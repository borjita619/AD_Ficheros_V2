package AD_UT2_Ej4_Fichero_RandIndexado;

import java.io.*;
import java.util.ArrayList;

public class AD_UT2_Ej_4_Fichero_RandIndexado_Ivan {

    public static void main(String[] args) throws IOException {

        ArrayList<Persona> personas = new ArrayList<>();
        ArrayList<String> nombresProductos = new ArrayList<>();

        String nomFichIndexado = "ejemplo_raf_indexado.dat";
        String nomFichIndices = "ejemplo_raf_indices.dat";
        int numeroRegistroALeer = 5;
        int tamañoBytesRegistro = 60;// 35 bytes (4 (int) (10 * 2) (cadena) 8 (doble) 8(double) (10 * 2) (cadena)).
        int posicionALeer;
        String nombreLeidoFich;
        String dniLeidoFich;

        // CREACIÓN DE LOS OBJETOS DE EJEMPLO A ALMACENAR EN EL ARCHIVO
        personas.add(new Persona(1, "Paco", 1.80, 74.4, "12345678A"));
        personas.add(new Persona(2, "Manolo", 1.60, 62.3, "12345679A"));
        personas.add(new Persona(3, "Antonio", 1.85, 90.5, "12345688A"));
        personas.add(new Persona(4, "Juan", 2.00, 100.2, "12345689A"));

        // ESCRITURA ------------------------------------------------------------------------
        // ESCRITURA DE LOS OBJETOS EN UN FICHERO RAF Y DE LOS ÍNDICES EN UN FICHERO DE TEXTO
        try (RandomAccessFile raf = new RandomAccessFile(nomFichIndexado, "rw")) {
            try (FileWriter fichIndex = new FileWriter(nomFichIndices, false)) {
                for (Persona p : personas) {
                    // Escritura objetos en fichero RAF
                    raf.writeInt(p.getId());

                    StringBuilder sb = new StringBuilder(p.getNombre());
                    sb.setLength(10);
                    raf.writeChars(sb.toString());

                    raf.writeDouble(p.getAltura());
                    raf.writeDouble(p.getPeso());
                    StringBuilder sb2 = new StringBuilder(p.getDni());
                    sb2.setLength(10);
                    raf.writeChars(sb2.toString());

                    // Escritura en el fichero de texto de índices
                    fichIndex.write(p.getNombre() + "\n");
                }
                fichIndex.close();


            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        // LECTURA ------------------------------------------------------------------------
        // LECTURA contenido array de índices
        System.out.println("\nLEYENDO CONTENIDO DEL ARCHIVO '" + nomFichIndices + "':");
        try (BufferedReader fichBuf = new BufferedReader(new FileReader(nomFichIndices))) {
            String cadena = fichBuf.readLine();
            while (cadena != null) {
                nombresProductos.add(cadena);
//                        System.out.println(cadena);
                cadena = fichBuf.readLine();
            }
            fichBuf.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //
        System.out.print("Array de índices:");
        for (String nombre : nombresProductos) {
            System.out.print(nombre + " ");
        }
        System.out.println("");


        int opcionElegida = leerEnteroEntreXeY("Pulse 1 para buscar una persona por su nombre o 2 para probar el ejemplo: ", 2, 1);

        if (opcionElegida == 2) {
            // LECTURA DE OBJETOS A PARTIR DEL CÁLCULO DE SU POSICIÓN A PARTIR DEL ORDEN QUE OCUPA SU ÍNDICE ALMACENADO
            // EN EL ARCHIVO CORRESPONDIENTE
            try (RandomAccessFile raf = new RandomAccessFile(nomFichIndexado, "rw")) {
                // Búsqueda del índice
                System.out.println("");
                String personaProbar = "Juan";
                //Se le suma 1 porque si no hay un error donde la posicion es 1 antes
                numeroRegistroALeer = nombresProductos.indexOf(personaProbar) + 1;

                    System.out.println("Posición de " + personaProbar + ": " + numeroRegistroALeer);

                    // Cálculo de la posición de ese índice
                    posicionALeer = (numeroRegistroALeer - 1) * tamañoBytesRegistro;
                    raf.seek(posicionALeer);

                    // Lectura de los datos del objeto correspondiente y presentación en terminal
                    System.out.println("Leyendo datos del registo: " + numeroRegistroALeer);
                    System.out.println(raf.readInt());
                    nombreLeidoFich = "";
                    for (int i = 0; i < 10; i++) {
                        nombreLeidoFich += raf.readChar();
                    }
                    System.out.println(nombreLeidoFich.trim());
                    System.out.println(raf.readDouble());
                    System.out.println(raf.readDouble());
                    dniLeidoFich = "";
                    for (int i = 0; i < 10; i++) {
                        dniLeidoFich += raf.readChar();
                    }
                    System.out.println(dniLeidoFich.trim());

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
        String nombreBuscar=leerCadena("Introduce un nombre para buscar: ");
            // LECTURA DE OBJETOS A PARTIR DEL CÁLCULO DE SU POSICIÓN A PARTIR DEL ORDEN QUE OCUPA SU ÍNDICE ALMACENADO
            // EN EL ARCHIVO CORRESPONDIENTE
            try (RandomAccessFile raf = new RandomAccessFile(nomFichIndexado, "rw")) {
                // Búsqueda del índice
                System.out.println("");
                String personaProbar = nombreBuscar;
                //Se le suma 1 porque si no hay un error donde la posicion es 1 antes
                numeroRegistroALeer = nombresProductos.indexOf(personaProbar) + 1;
                System.out.println("Posición de " + personaProbar + ": " + numeroRegistroALeer);

                // Cálculo de la posición de ese índice
                posicionALeer = (numeroRegistroALeer - 1) * tamañoBytesRegistro;
                raf.seek(posicionALeer);

                // Lectura de los datos del objeto correspondiente y presentación en terminal
                System.out.println("Leyendo datos del registo: " + numeroRegistroALeer);
                System.out.println(raf.readInt());
                nombreLeidoFich = "";
                for (int i = 0; i < 10; i++) {
                    nombreLeidoFich += raf.readChar();
                }
                System.out.println(nombreLeidoFich.trim());
                System.out.println(raf.readDouble());
                System.out.println(raf.readDouble());
                dniLeidoFich = "";
                for (int i = 0; i < 10; i++) {
                    dniLeidoFich += raf.readChar();
                }
                System.out.println(dniLeidoFich.trim());

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static String leerCadena(String mensaje) throws IOException {
        InputStreamReader flujo = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(flujo);
        String cadena;
        do {
            System.out.print(mensaje);
            cadena = teclado.readLine();
        } while (cadena.length() < 1);

        return cadena;
    }

    public static int leerEnteroEntreXeY(String mensaje, int maximo, int minimo) throws IOException {
        InputStreamReader flujo = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(flujo);
        String papelEnSucio;
        int dato = 0;
        boolean correcto;
        //Te vuelve a preguntar hasta que el entero este correcto
        do {
            do {
                correcto = true;
                try {
                    System.out.print(mensaje);
                    papelEnSucio = teclado.readLine();
                    dato = Integer.parseInt(papelEnSucio);
                } catch (NumberFormatException e) {
                    System.out.println("\tError en el metodo LeerDatoEntero: El formato del numero no es valido");
                    correcto = false;
                }
            } while (correcto == false);

        } while (dato < minimo || dato > maximo);

        return dato;
    }
}
