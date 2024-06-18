/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objets;

/**
 *
 * @author draks
 */
public class Empleado {
    private String id;
    private String nombre;
    private int edad;
    private String sexo;
    private String estudios;
    private Contacto contacto;
    private String domicilio;
    private String noSS;
    private Puesto puesto;
    private Tienda tienda;

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setNoSS(String noSS) {
        this.noSS = noSS;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEstudios() {
        return estudios;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getNoSS() {
        return noSS;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public Empleado(String id, String nombre, int edad, String sexo, String estudios, Contacto contacto, String domicilio, String noSS, Puesto puesto, Tienda tienda) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.estudios = estudios;
        this.contacto = contacto;
        this.domicilio = domicilio;
        this.noSS = noSS;
        this.puesto = puesto;
        this.tienda = tienda;
    }

    public Empleado(String nombre) {
        this.nombre = nombre;
    }
}
