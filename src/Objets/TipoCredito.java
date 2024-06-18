/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objets;

/**
 *
 * @author jaime
 */
public class TipoCredito {
    private String nombre;
    private float interes;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }

    public String getNombre() {
        return nombre;
    }

    public float getInteres() {
        return interes;
    }

    public TipoCredito(String nombre, float interes) {
        this.nombre = nombre;
        this.interes = interes;
    }
}
