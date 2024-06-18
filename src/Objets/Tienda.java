/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objets;

/**
 *
 * @author jaime
 */
public class Tienda {
    private String nombre;
    private String domicilio;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public Tienda(String nombre, String domicilio) {
        this.nombre = nombre;
        this.domicilio = domicilio;
    }
    
}
