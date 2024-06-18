/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objets;

/**
 *
 * @author jaime
 */
public class Puesto {
    private String puesto;
    private int sueldo;
    private int bonos;

    public Puesto(String puesto, int sueldo, int bonos) {
        this.puesto = puesto;
        this.sueldo = sueldo;
        this.bonos = bonos;
    }

    public String getPuesto() {
        return puesto;
    }

    public int getSueldo() {
        return sueldo;
    }

    public int getBonos() {
        return bonos;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public void setBonos(int bonos) {
        this.bonos = bonos;
    }
    
    
}
