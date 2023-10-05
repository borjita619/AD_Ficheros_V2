/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLEncoderDecoder;

/**
 *
 * @author https://www.discoduroderoer.es/como-leer-y-escribir-un-fichero-con-randomaccessfile/
 * Usado en AD_UT2_Ej_3b_Random
 */
    

public class Coche {

    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private boolean descuento;
    private char etiqueta;

    public Coche(){
        
    }
    
    public Coche(int id, String marca, String modelo, double precio, boolean descuento, char etiqueta) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.descuento = descuento;
        this.etiqueta = etiqueta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDescuento() {
        return descuento;
    }

    public void setDescuento(boolean descuento) {
        this.descuento = descuento;
    }

    public char getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(char etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return "Coche{" + "id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio + ", descuento=" + descuento + ", etiqueta=" + etiqueta + '}';
    }

}