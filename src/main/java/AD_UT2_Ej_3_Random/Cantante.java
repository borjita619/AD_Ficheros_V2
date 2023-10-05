package AD_UT2_Ej_3_Random;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author https://www.discoduroderoer.es/como-leer-y-escribir-un-fichero-con-randomaccessfile/
 * Usado en AD_UT2_Ej_3b_Random
 */
    

public class Cantante {

    private int id;
    private String nombre;
    private double altura;
    private boolean activo;

    public Cantante(){
        
    }
    
    public Cantante(int id, String nombre, double altura, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.altura = altura;
        this.activo = activo;
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

    public boolean isActivo() {
        return activo;
    }

    public void setactivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Cantante{" + "id=" + id + ", nombre=" + nombre + ", altura=" + altura + ", activo=" + activo + '}';
    }

}