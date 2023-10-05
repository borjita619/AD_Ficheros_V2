package AD_UT2_Ej4_Fichero_RandIndexado;

public class Persona {
    private int id;
    private String nombre;
    private double altura;
    private double peso;
    private String dni;

    public Persona() {

    }

    public Persona(int id, String nombre, double altura, double peso, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", altura=" + altura +
                ", peso=" + peso +
                ", dni='" + dni + '\'' +
                '}';
    }
}
